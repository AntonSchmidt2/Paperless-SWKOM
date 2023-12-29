package org.openapitools.serviceLayer.services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.persistence.repositories.DocumentRepository;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class DocumentServiceImpl {
    private final DocumentRepository documentRepository;
    private final MinioClient minioClient;
    private final DocumentMapper documentMapper;
    private final RabbitMQSender rabbitMQSender;

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    public DocumentServiceImpl(DocumentRepository documentRepository, MinioClient minioClient, DocumentMapper documentMapper, RabbitMQSender rabbitMQSender) {
        this.documentRepository = documentRepository;
        this.minioClient = minioClient;
        this.documentMapper = documentMapper;
        this.rabbitMQSender = rabbitMQSender;
    }

    @Transactional
    public void uploadDocument(DocumentDTO documentDTO) {
        logger.info("uploadDocument started");

        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());
        documentDTO.content("");
        documentDTO.setAdded(OffsetDateTime.now());

        DocumentEntity documentToBeSaved = documentMapper.dtoToEntity(documentDTO);
        documentToBeSaved.setStorageType("pdf");
        documentToBeSaved.setMimeType("pdf");

        logger.info("Saving document to the repository");
        documentRepository.save(documentToBeSaved);
        logger.info("Document saved to the repository");

        // Upload file to MinIO
        // MultipartFile multipartFile = documentDTO.getDocument();
        // String bucketName = "paperless-bucket"; // replace with your bucket name

        // if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
        //     minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        // }

        // try {
        //     minioClient.putObject(
        //             PutObjectArgs.builder()
        //                     .bucket(bucketName)
        //                     .object(multipartFile.getOriginalFilename())
        //                     .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
        //                     .contentType(multipartFile.getContentType())
        //                     .build());
        //     logger.info("Document uploaded to MinIO");
        // } catch (MinioException | IOException e) {
        //     logger.error("Error occurred while uploading file to MinIO", e);
        // } catch (NoSuchAlgorithmException | InvalidKeyException e) {
        //     logger.error("Error occurred", e);
        // }
    }
}