package org.openapitools.Services;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OcrService {
    private final ITesseract tesseract;
    private final RabbitTemplate rabbitTemplate;
    private final MinioClient minioClient;
    private final AmqpAdmin amqpAdmin;

    public OcrService(RabbitTemplate rabbitTemplate, AmqpAdmin amqpAdmin) {
        this.rabbitTemplate = rabbitTemplate;
        this.amqpAdmin = amqpAdmin;
        //minIO client has to refer to docker address
        this.minioClient = MinioClient.builder()
                .endpoint("http://host.docker.internal:9000")
                .credentials("paperless", "paperless")
                .build();
        this.tesseract = new Tesseract();
        this.tesseract.setLanguage("eng");
        waitForQueueCreation("OCR_DOCUMENT_IN");
    }

    @RabbitListener(queues = "OCR_DOCUMENT_IN") // Replace with the actual queue name
    public void processMessage(String message) {
        try {
            InputStream file = getFileFromMinio(message);
            String text = extractText((MultipartFile) file);
            System.out.println("Extracted Text: " + text);

            // Send the extracted text back to RabbitMQ (publish to a new queue or the same queue)
            //rabbitTemplate.convertAndSend("extractedText-queue", text);

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
        } catch (MinioException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream getFileFromMinio(String fileName) throws IOException, MinioException {
        try {
            GetObjectResponse object = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("paperless-bucket")
                            .object(fileName)
                            .build()
            );
            return object;
        } catch (Exception e) {
            throw new IOException("Error fetching file from Minio", e);
        }
    }

    public String extractText(MultipartFile file) throws IOException, TesseractException {
        File tempFile = convertToFile(file);

        String text = tesseract.doOCR(tempFile);
        tempFile.delete();

        return text;
    }
    public static File convertToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("temp", null);
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

    private void waitForQueueCreation(String queueName) {
        // Add a loop to wait for the queue to be declared
        while (amqpAdmin.getQueueProperties(queueName) == null) {
            try {
                Thread.sleep(5000);  // Sleep for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
