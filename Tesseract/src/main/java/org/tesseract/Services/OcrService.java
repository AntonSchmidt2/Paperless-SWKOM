package org.tesseract.Services;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tesseract.Entity.DocumentEntityOCR;
import org.tesseract.Repositories.DocumentRepositoryOCR;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OcrService {

    @Autowired
    private DocumentRepositoryOCR documentRepositoryOCR;
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
        System.out.println(message);

        // DOES NOT WORK
        // getting the file from minio returns some file stream - how to get to File/MultipartFile??
        //InputStream file = getFileFromMinio(message);
        //String text = extractText(file);
        //System.out.println("Extracted Text: " + text);

        // currently only saving the file name into the content column for test purposes
        // DOES NOT WORK
        // spring cant seem to find documentRepository bean
        saveMessageToDatabase(message);
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

    private String extractText(File file) throws TesseractException {
        return tesseract.doOCR(file);
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

    private void saveMessageToDatabase(String message) {
        // Check if a document with the same title already exists
        DocumentEntityOCR existingDocument = documentRepositoryOCR.findByTitle(message);

        if (existingDocument != null) {
            // If the document exists, update its content
            existingDocument.setContent(message);
            documentRepositoryOCR.save(existingDocument);
            System.out.println("Content updated for document with title '" + message + "'");
        } else {
            System.out.println("No document with title '" + message + "' found");
        }
    }
}
