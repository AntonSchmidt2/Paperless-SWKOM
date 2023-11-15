package org.openapitools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repositories.*;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DocumentMapperTest {
    // @MockBean annotation creates a mock implementation for the class
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

    // @InjectMocks annotation creates an instance of the class and injects the mocks that are created with the @MockBean annotation
    @InjectMocks
    private final DocumentMapper documentMapper = new DocumentMapper() {
        @Override
        public DocumentEntity dtoToEntity(DocumentDTO dto) {
            return null;
        }

        @Override
        public DocumentDTO entityToDTO(DocumentEntity entity) {
            return null;
        }
    };

    // initializes mocks before each test method
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

        // Mock the repository calls
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
