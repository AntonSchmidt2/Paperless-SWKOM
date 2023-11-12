package org.openapitools.serviceLayer.services;

import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.persistence.repositories.DocumentRepository;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
//import org.openapitools.serviceLayer.mapper.GetDocument200ResponseMapper;

import java.time.OffsetDateTime;

public class DocumentServiceImpl {
    private final DocumentRepository documentRepository;
    //NOTE for some reason Autowired didnt recognize the Mapper as a Component
    // this is a quick fix, might be better to get it to work later
    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }


    public void uploadDocument(DocumentDTO documentDTO) {
        documentDTO.setCreated(OffsetDateTime.now());
        documentDTO.setAdded(OffsetDateTime.now());
        documentDTO.setModified(OffsetDateTime.now());
        documentDTO.content("");
        documentDTO.setAdded(OffsetDateTime.now());

        DocumentEntity documentToBeSaved = documentMapper.dtoToEntity(documentDTO);

        documentToBeSaved.setChecksum("checksum");
        documentToBeSaved.setStorageType("pdf");
        documentToBeSaved.setMimeType("pdf");

        documentRepository.save(documentToBeSaved);
    }
}
