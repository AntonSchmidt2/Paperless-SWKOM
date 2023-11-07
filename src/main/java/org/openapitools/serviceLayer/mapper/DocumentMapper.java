package org.openapitools.serviceLayer.mapper;

import org.mapstruct.*;
import org.openapitools.persistence.entities.DocumentEntity;
import org.openapitools.persistence.repository.DocumentRepository;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public abstract class DocumentMapper implements Mapper<DocumentEntity, DocumentDTO>{
    @Autowired
    private DocumentRepository documentRepository;
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentEntityToCorrespondent")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeEntityToDocumentType")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathEntityToStoragePath")
    @Mapping(target = "title", source = "title", qualifiedByName = "titleEntityToTitle")
    @Mapping(target = "content", source = "content", qualifiedByName = "contentEntityToContent")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "modified", source = "modified", qualifiedByName = "modifiedEntityToModified")
    @Mapping(target = "created", source = "created", qualifiedByName = "createdEntityToCreated")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberEntityToArchiveSerialNumber")
    abstract public DocumentEntity dtoToEntity(DocumentDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentEntityToCorrespondent")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeEntityToDocumentType")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathEntityToStoragePath")
    @Mapping(target = "title", source = "title", qualifiedByName = "titleEntityToTitle")
    @Mapping(target = "content", source = "content", qualifiedByName = "contentEntityToContent")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "modified", source = "modified", qualifiedByName = "modifiedEntityToModified")
    @Mapping(target = "created", source = "created", qualifiedByName = "createdEntityToCreated")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberEntityToArchiveSerialNumber")
    abstract public DocumentDTO entitytoDTO(DocumentEntity entity);


}
