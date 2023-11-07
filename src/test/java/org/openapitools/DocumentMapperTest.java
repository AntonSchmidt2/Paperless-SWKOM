package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.CorrespondentEntity;
import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.persistence.repository.CorrespondentRepository;
import org.openapitools.persistence.repository.DocumentRepository;
import org.openapitools.serviceLayer.dto.CorrespondentDTO;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DocumentMapperTest {
    @MockBean
    private DocumentRepository documentRepository;
    @MockBean
    private CorrespondentRepository correspondentRepository;
    @InjectMocks
    private DocumentMapper documentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testDtoToEntity() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(10);
        documentDTO.setArchiveSerialNumber(JsonNullable.of("1"));
        documentDTO.setCorrespondent(JsonNullable.of(1));
        documentDTO.setDocumentType(JsonNullable.of(2));
        documentDTO.setStoragePath(JsonNullable.of(3));
        documentDTO.setTitle(JsonNullable.of("title"));
        documentDTO.setTags(JsonNullable.of(Collections.singletonList(4)));

        CorrespondentEntity correspondent = new CorrespondentEntity();
        correspondent.setId(10);

        Mockito.when(correspondentRepository.findById(10)).thenReturn(java.util.Optional.of(correspondent));

        DocumentEntity document = documentMapper.dtoToEntity(documentDTO);

        assertEquals(correspondent, document.getCorrespondent());
    }


}
