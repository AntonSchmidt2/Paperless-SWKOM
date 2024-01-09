import pika
import psycopg2
import os
import io
import tempfile
from minio import Minio
from pdf2image import convert_from_path
import pytesseract
import time

class OCRWorker:
    def __init__(self):
        self.minio_client = Minio(
            "paperless-minio:9000",
            access_key="paperless",
            secret_key="paperless",
            secure=False
        )
        self.conn = None
        self.channel = self.setup_rabbitmq()

    def connect_to_db(self):
        self.conn = psycopg2.connect(
            host="paperless-postgres",
            port=5432,
            database="Paperless",
            user="postgres",
            password="postgres"
        )

    def setup_rabbitmq(self):
        credentials = pika.PlainCredentials('paperless', 'paperless')
        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host='rabbitmq', credentials=credentials)
        )
        channel = connection.channel()
        self.wait_for_queue(channel, 'OCR_DOCUMENT_IN')
        return channel

    def wait_for_queue(self, channel, queue_name, retries=500, delay=5):
        for _ in range(retries):
            try:
                channel.queue_declare(queue=queue_name)
                print(f"Queue '{queue_name}' is ready.")
                return
            except pika.exceptions.AMQPError as e:
                print(f"Queue declaration failed, retrying... Error: {e}")
                time.sleep(delay)
        raise Exception(f"Cannot declare queue '{queue_name}' after multiple attempts")

    def callback(self, ch, method, properties, body):
        document_name = body.decode()

        try:
            content = self.process_document(document_name)
            self.write_to_db(document_name, content)
        except Exception as e:
            print(f"An error occurred: {e}")

        print(f"Processed document: {document_name}")

    def process_document(self, document_name):
        response = self.minio_client.get_object('paperless-bucket', document_name)
        with io.BytesIO(response.data) as file_data, tempfile.NamedTemporaryFile(delete=False) as temp_file:
            temp_file.write(file_data.read())
            temp_file_path = temp_file.name

        images = convert_from_path(temp_file_path)
        extracted_texts = [pytesseract.image_to_string(image) for image in images]
        content = " ".join(extracted_texts)

        os.remove(temp_file_path)
        return content

    def write_to_db(self, document_name, content):
        if self.conn is None:
            self.connect_to_db()
        with self.conn.cursor() as cur:
            cur.execute(
                "UPDATE documents_document SET content = %s WHERE title = %s",
                (content, document_name)
            )
            self.conn.commit()

    def start(self):
        self.channel.basic_consume(queue='OCR_DOCUMENT_IN', on_message_callback=self.callback, auto_ack=True)
        print('OCR Worker has started. Waiting for documents...')
        self.channel.start_consuming()

if __name__ == "__main__":
    worker = OCRWorker()
    worker.start()
