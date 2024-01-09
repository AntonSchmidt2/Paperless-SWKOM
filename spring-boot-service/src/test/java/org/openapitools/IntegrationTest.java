package org.openapitools;

import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

//The IntegrationTest is testing the integration of the DocumentMapper class with the Spring context
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private DocumentMapper documentMapper;

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

        DocumentEntity document = documentMapper.dtoToEntity(documentDTO);

        // Assert that the entity is correctly mapped
        assertEquals(documentDTO.getId(), document.getId(), "Checking if document ID matches");
        assertEquals(Integer.parseInt(documentDTO.getArchiveSerialNumber().get()), document.getArchiveSerialNumber());
    }
}