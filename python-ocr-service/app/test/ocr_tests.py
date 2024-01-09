import unittest
from unittest.mock import patch, MagicMock
from app.ocr_worker import OCRWorker
import pika

class TestOCRWorker(unittest.TestCase):


    def setUp(self):
        # Patch the external services in the constructor
        self.minio_client = MagicMock()
        self.rabbitmq_conn = MagicMock()
        self.rabbitmq_channel = MagicMock()
        self.psycopg2_conn = MagicMock()
        self.psycopg2_cursor = MagicMock()

        self.patches = [
            patch('app.ocr_worker.Minio', return_value=self.minio_client),
            patch('app.ocr_worker.pika.BlockingConnection', return_value=self.rabbitmq_conn),
            patch('app.ocr_worker.psycopg2.connect', return_value=self.psycopg2_conn)
        ]
        for patcher in self.patches:
            patcher.start()
            self.addCleanup(patcher.stop)

        self.psycopg2_conn.cursor.return_value = self.psycopg2_cursor
        self.rabbitmq_conn.channel.return_value = self.rabbitmq_channel
        self.worker = OCRWorker()

    def test_wait_for_queue_failure_raises_exception(self):
        self.rabbitmq_channel.queue_declare.side_effect = pika.exceptions.AMQPError()

        with self.assertRaises(Exception) as context:
            self.worker.wait_for_queue(self.rabbitmq_channel, 'test_queue', retries=3, delay=0.01)

        self.assertEqual(str(context.exception), "Cannot declare queue 'test_queue' after multiple attempts")

    @patch('app.ocr_worker.convert_from_path')
    @patch('app.ocr_worker.pytesseract.image_to_string', return_value="sample text")
    def test_process_document(self, mock_image_to_string, mock_convert_from_path):
        # Mock the Minio client response
        self.minio_client.get_object.return_value.data = b'some data'

        # Set up a mock for the convert_from_path to return a list of image mocks
        mock_image = MagicMock()
        mock_convert_from_path.return_value = [mock_image]

        # Test process_document method
        content = self.worker.process_document('test_doc.pdf')

        #print("Debugging test_process_document:")
        #print(f"Mocked pytesseract.image_to_string return value: {mock_image_to_string.return_value}")
        #print(f"Content returned from process_document: '{content}'")
        #print(f"convert_from_path called with: {mock_convert_from_path.call_args}")
        #print(f"image_to_string called with: {mock_image_to_string.call_args}")

        self.assertEqual(content, mock_image_to_string.return_value)

    def test_write_to_db_calls_execute_with_correct_parameters(self):
        document_name = 'test_doc.pdf'
        content = 'sample content'
        self.worker.write_to_db(document_name, content)

        self.psycopg2_cursor.execute.assert_called_once_with(
            "UPDATE documents_document SET content = %s WHERE title = %s",
            (content, document_name)
        )
        self.psycopg2_conn.commit.assert_called_once()

    @patch('app.ocr_worker.OCRWorker.process_document', return_value="processed content")
    def test_callback_process_document_and_write_to_db(self, mock_process_document):
        # Set up the worker's connection attribute to the mock connection
        self.worker.conn = self.psycopg2_conn

        # Assuming the cursor and commit are to be called, let's ensure they are callable
        self.psycopg2_conn.cursor.return_value = self.psycopg2_cursor
        self.psycopg2_cursor.execute.return_value = None
        self.psycopg2_conn.commit.return_value = None

        body = b'test_doc.pdf'
        self.worker.callback(None, None, None, body)

        print("Debugging test_callback_process_document_and_write_to_db:")
        print(f"Process document called: {mock_process_document.called}")
        print(f"Process document return value: {mock_process_document.return_value}")
        print(f"Cursor execute call count: {self.psycopg2_cursor.execute.call_count}")
        if self.psycopg2_cursor.execute.call_count > 0:
            print(f"Cursor execute last call args: {self.psycopg2_cursor.execute.call_args}")
        else:
            print("Cursor execute was not called.")

        # Check that process_document was called correctly
        mock_process_document.assert_called_once_with('test_doc.pdf')
        # Now check that the cursor's execute method was called once with the correct SQL and parameters
        self.psycopg2_cursor.execute.assert_called_once_with(
            "UPDATE documents_document SET content = %s WHERE title = %s",
            ("processed content", 'test_doc.pdf')
        )

    def test_start_begins_consuming_from_queue(self):
        self.worker.start()
        self.rabbitmq_channel.basic_consume.assert_called_once()
        self.rabbitmq_channel.start_consuming.assert_called_once()

if __name__ == '__main__':
    unittest.main()
