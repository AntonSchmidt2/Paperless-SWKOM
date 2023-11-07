package org.openapitools.serviceLayer.mapper;

import org.mapstruct.*;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repository.CorrespondentRepository;
import org.openapitools.persistence.repository.DocumentRepository;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DocumentType;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Service
public abstract class DocumentMapper implements Mapper<DocumentEntity, DocumentDTO>{
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private CorrespondentRepository correspondentRepository;


    //@Mapping(target = "id", ignore = true)
    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentDto")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeDto")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathDto")
    //@Mapping(target = "title", source = "title", qualifiedByName = "titleEntityToTitle")
    //@Mapping(target = "content", source = "content", qualifiedByName = "contentEntityToContent")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagsDto")
    //@Mapping(target = "modified", source = "modified", qualifiedByName = "modifiedEntityToModified")
    //@Mapping(target = "created", source = "created", qualifiedByName = "createdEntityToCreated")
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberDto")
    abstract public DocumentEntity dtoToEntity(DocumentDTO dto);

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentEntity")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeEntity")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathEntity")
    //@Mapping(target = "title", source = "title", qualifiedByName = "titleEntityToTitle")
    //@Mapping(target = "content", source = "content", qualifiedByName = "contentEntityToContent")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagsEntity")
    //@Mapping(target = "modified", source = "modified", qualifiedByName = "modifiedEntityToModified")
    //@Mapping(target = "created", source = "created", qualifiedByName = "createdEntityToCreated")
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberEntity")
    abstract public DocumentDTO entityToDTO(DocumentEntity entity);

    @Named("correspondentEntity")
    JsonNullable<Integer> map(CorrespondentEntity correspondent) {
        return correspondent!=null ? JsonNullable.of(correspondent.getId()) : JsonNullable.undefined();
    }
    @Named("documentTypeEntity")
    JsonNullable<Integer> map(DocumentTypeEntity documentType) {
        return documentType!=null ? JsonNullable.of(documentType.getId()) : JsonNullable.undefined();
    }

    @Named("storagePathEntity")
    JsonNullable<Integer> map(StoragePathEntity storagePath) {
        return storagePath!=null ? JsonNullable.of(storagePath.getId()) : JsonNullable.undefined();
    }

    @Named("ownerEntity")
    JsonNullable<Integer> map(AuthUserEntity owner) {
        return owner!=null ? JsonNullable.of(owner.getId()) : JsonNullable.undefined();
    }

    @Named("tagsEntity")
    JsonNullable<List<Integer>> map(Set<DocumentTagsEntity> tags) {
        return tags!=null ? JsonNullable.of( tags.stream().map( tag->(int)tag.getId()).collect(Collectors.toList()) ) : JsonNullable.undefined();
    }

    // map created to createdDate (Date without the time)
    @Named("createdToCreatedDate") //referenced by @Mapping
    OffsetDateTime mapCreatedDate(OffsetDateTime value) {
        return value!=null ? value.withOffsetSameInstant(ZoneOffset.UTC).toLocalDate().atStartOfDay().atOffset(ZoneOffset.UTC) : null;
    }

    @Named("correspondentDto") //referenced by @Mapping
    CorrespondentEntity mapCorrespondent(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return correspondentRepository.findById(value.get()).orElse(null);
    }

    @Named("documentTypeDto")
    DocumentType mapDocumentType(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return documentTypeRepository.findById(value.get()).orElse(null);
    }

    @Named("storagePathDto")
    StoragePath mapStoragePath(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return storagePathRepository.findById(value.get()).orElse(null);
    }

    @Named("ownerDto")
    AuthUser mapOwner(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return userRepository.findById(value.get()).orElse(null);
    }

    @Named("tagsDto")
    Set<DocumentTags> mapDocTag(JsonNullable<List<Integer>> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return new HashSet<DocumentTags>(documentTagsRepository.findAllById(value.get()));
    }

    @Named("archiveSerialNumberDto")
    Integer mapArchiveSerialNumber(JsonNullable<String> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return Integer.parseInt(value.get());
    }
}
