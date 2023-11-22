package org.openapitools.serviceLayer.services;

import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.persistence.repositories.DocumentRepository;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
@Service
public class DocumentServiceImpl {
    private final DocumentRepository documentRepository;
    //NOTE for some reason Autowired didnt recognize the Mapper as a Component
    // this is a quick fix, might be better to get it to work later
    @Autowired
    private final DocumentMapper documentMapper;
    private final RabbitMQSender rabbitMQSender;

    // Constructor based dependency injection
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper, RabbitMQSender rabbitMQSender) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.rabbitMQSender = rabbitMQSender;
    }

    // Method to upload a document setting dummy values for the fields that are not yet implemented
    public void uploadDocument(DocumentDTO documentDTO) {
        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());
        documentDTO.content("");
        documentDTO.setAdded(OffsetDateTime.now());

        // Convert the DTO to an entity
        DocumentEntity documentToBeSaved = documentMapper.dtoToEntity(documentDTO);

        // Set dummy values for the fields that are not yet implemented
        documentToBeSaved.setChecksum("checksum");
        documentToBeSaved.setStorageType("pdf");
        documentToBeSaved.setMimeType("pdf");

        documentRepository.save(documentToBeSaved);
    }
}
