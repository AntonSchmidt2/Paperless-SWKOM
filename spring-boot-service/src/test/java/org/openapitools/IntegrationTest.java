package org.openapitools;

import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repositories.*;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class IntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("integration-tests-db")
            .withUsername("username")
            .withPassword("password");

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private CorrespondentRepository correspondentRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private StoragePathRepository storagePathRepository;
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private DocumentTagsRepository documentTagsRepository;

    @Test
    public void testDtoToEntity() {
        // Create dummy DTO
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(1);
        documentDTO.setArchiveSerialNumber(JsonNullable.of("1"));
        documentDTO.setCorrespondent(JsonNullable.of(1));
        documentDTO.setDocumentType(JsonNullable.of(2));
        documentDTO.setStoragePath(JsonNullable.of(3));
        documentDTO.setTags(JsonNullable.of(Collections.singletonList(4)));

        // Create dummy entities
        CorrespondentEntity correspondent = new CorrespondentEntity();
        correspondent.setId(1);
        DocumentTypeEntity documentType = new DocumentTypeEntity();
        documentType.setId(2);
        StoragePathEntity storagePath = new StoragePathEntity();
        storagePath.setId(3);
        AuthUserEntity authUser = new AuthUserEntity();
        authUser.setId(5);
        DocumentTagsEntity doc = new DocumentTagsEntity();
        doc.setId(4);

        // Save entities to the database
        correspondentRepository.save(correspondent);
        documentTypeRepository.save(documentType);
        storagePathRepository.save(storagePath);
        authUserRepository.save(authUser);
        documentTagsRepository.save(doc);

        DocumentEntity document = documentMapper.dtoToEntity(documentDTO);

        // Assert that the entity is correctly mapped
        assertEquals(correspondent, document.getCorrespondent());
        assertEquals(documentType, document.getDocumentType());
        assertEquals(storagePath, document.getStoragePath());
    }
}