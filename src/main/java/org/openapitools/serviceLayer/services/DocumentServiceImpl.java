package org.openapitools.serviceLayer.services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.persistence.repositories.DocumentRepository;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import io.minio.PutObjectArgs;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DocumentServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final DocumentRepository documentRepository;
    private final MinioClient minioClient;

    //NOTE for some reason Autowired didnt recognize the Mapper as a Component
    // this is a quick fix, might be better to get it to work later
    @Autowired
    private final DocumentMapper documentMapper;
    private final RabbitMQSender rabbitMQSender;

    // Constructor based dependency injection
    public DocumentServiceImpl(DocumentRepository documentRepository, MinioClient minioClient, DocumentMapper documentMapper, RabbitMQSender rabbitMQSender) {
        this.documentRepository = documentRepository;
        this.minioClient = minioClient;
        this.documentMapper = documentMapper;
        this.rabbitMQSender = rabbitMQSender;
    }

    // Method to upload a document setting dummy values for the fields that are not yet implemented
    @Transactional
    public void uploadDocument(DocumentDTO documentDTO) {
        logger.info("Starting document upload process for document: {}", documentDTO.getDocument().getOriginalFilename());
        try
        {
            // Save metadata to PostgreSQL
            documentDTO.setCreated(OffsetDateTime.now());
            documentDTO.setAdded(OffsetDateTime.now());
            documentDTO.setModified(OffsetDateTime.now());
            documentDTO.content("");
            documentDTO.setAdded(OffsetDateTime.now());

            DocumentEntity documentToBeSaved = documentMapper.dtoToEntity(documentDTO);
            documentToBeSaved.setStorageType("pdf");
            documentToBeSaved.setMimeType("pdf");

            documentRepository.save(documentToBeSaved);

            // Upload file to MinIO
            // multipart file is currently being saved to DTO. Should we handle it separately?
            MultipartFile multipartFile = documentDTO.getDocument();
            String bucketName = "paperless-bucket"; // replace with your bucket name

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                logger.info("Bucket does not exist. Creating a new one: {}", bucketName);
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(multipartFile.getOriginalFilename())
                            .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                            .contentType(multipartFile.getContentType())
                            .build());

        }
        catch (MinioException | IOException e) {
            throw new RuntimeException("Error occurred while uploading file to MinIO", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }



}
