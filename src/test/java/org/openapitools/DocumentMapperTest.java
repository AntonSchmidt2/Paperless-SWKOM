package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repository.*;
import org.openapitools.serviceLayer.dto.CorrespondentDTO;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.openapitools.serviceLayer.mapper.DocumentMapperImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DocumentMapperTest {
    @MockBean
    private CorrespondentRepository correspondentRepository;
    @MockBean
    private DocumentTypeRepository documentTypeRepository;
    @MockBean
    private StoragePathRepository storagePathRepository;
    @MockBean
    private AuthUserRepository authUserRepository;
    @MockBean
    private DocumentTagsRepository documentTagsRepository;

    @InjectMocks
    private final DocumentMapper documentMapper = new DocumentMapperImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDtoToEntity() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(1);
        documentDTO.setArchiveSerialNumber(JsonNullable.of("1"));
        documentDTO.setCorrespondent(JsonNullable.of(1));
        documentDTO.setDocumentType(JsonNullable.of(2));
        documentDTO.setStoragePath(JsonNullable.of(3));
        documentDTO.setTags(JsonNullable.of(Collections.singletonList(4)));

        // Mock repository responses
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


        Mockito.when(correspondentRepository.findById(1)).thenReturn(java.util.Optional.of(correspondent));
        Mockito.when(documentTypeRepository.findById(2)).thenReturn(java.util.Optional.of(documentType));
        Mockito.when(storagePathRepository.findById(3)).thenReturn(java.util.Optional.of(storagePath));
        Mockito.when(authUserRepository.findById(5)).thenReturn(java.util.Optional.of(authUser));
        Mockito.when(documentTagsRepository.findAllById(Collections.singletonList(4))).thenReturn(Collections.singletonList(doc));


        DocumentEntity document = documentMapper.dtoToEntity(documentDTO);

        // Assert that the entity is correctly mapped
        assertEquals(correspondent, document.getCorrespondent());
        assertEquals(documentType, document.getDocumentType());
        assertEquals(storagePath, document.getStoragePath());
    }


}
