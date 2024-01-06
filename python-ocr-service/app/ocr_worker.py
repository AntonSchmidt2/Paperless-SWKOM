import pika
import psycopg2
from minio import Minio
from pdf2image import convert_from_path
import pytesseract
import os
import io
import tempfile


# Establish connection to MinIO
minio_client = Minio(
    "localhost:9000",
    access_key="paperless",
    secret_key="paperless",
    secure=False
)

# Establish connection to PostgreSQL
conn = psycopg2.connect(
    host="localhost",
    port=5432,
    database="Paperless",
    user="postgres",
    password="postgres"
)

# Establish connection to RabbitMQ
# Establish connection to RabbitMQ
credentials = pika.PlainCredentials('paperless', 'paperless')
connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost', credentials=credentials)
)
channel = connection.channel()

# Declare the queue
channel.queue_declare(queue='document_queue')

# callback for basic_consume
def callback(ch, method, properties, body):
    document_name = body.decode()

    try:
        # Get the document from MinIO
        response = minio_client.get_object('paperless-bucket', document_name)
        with io.BytesIO(response.data) as file_data:
            # Write the data to a temporary file
            with tempfile.NamedTemporaryFile(delete=False) as temp_file:
                temp_file.write(file_data.read())
                temp_file_path = temp_file.name

            # Convert PDF to images
            images = convert_from_path(temp_file_path)

            # Perform OCR
            extracted_texts = [pytesseract.image_to_string(image) for image in images]
            content = " ".join(extracted_texts)

            # Write content to Postgres
            with conn.cursor() as cur:
                cur.execute(
                    "UPDATE documents_document SET content = %s WHERE title = %s",
                    (content, document_name)
                )
                conn.commit()

            # Delete the temporary file
            os.remove(temp_file_path)

    except Exception as e:
        print(f"An error occurred: {e}")

    print(f"Processed document: {document_name}")

# consumer using the callback
channel.basic_consume(queue='OCR_DOCUMENT_IN', on_message_callback=callback, auto_ack=True)

print('OCR Worker has started. Waiting for documents...')
channel.start_consuming()
