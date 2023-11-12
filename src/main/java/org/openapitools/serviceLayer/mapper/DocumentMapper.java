package org.openapitools.serviceLayer.mapper;

import org.mapstruct.*;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repository.*;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public abstract class DocumentMapper implements Mapper<DocumentEntity, DocumentDTO>{
    @Autowired
    private DocumentRepository documentRepository;
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

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentDto")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeDto")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathDto")
    @Mapping(target = "archiveSerialNumber", source = "archiveSerialNumber", qualifiedByName = "archiveSerialNumberDto")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagsDto")
    abstract public DocumentEntity dtoToEntity(DocumentDTO dto); // Abstract weil mapstruct alles generiert

    @Mapping(target = "correspondent", source = "correspondent", qualifiedByName = "correspondentEntity")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeEntity")
    @Mapping(target = "storagePath", source = "storagePath", qualifiedByName = "storagePathEntity")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagsEntity")
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

    @Named("authUserEntity")
    JsonNullable<Integer> map(AuthUserEntity authUser) {
        return authUser!=null ? JsonNullable.of(authUser.getId()) : JsonNullable.undefined();
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
    DocumentTypeEntity mapDocumentType(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return documentTypeRepository.findById(value.get()).orElse(null);
    }

    @Named("storagePathDto")
    StoragePathEntity mapStoragePath(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return storagePathRepository.findById(value.get()).orElse(null);
    }

    @Named("ownerDto")
    AuthUserEntity mapOwner(JsonNullable<Integer> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return authUserRepository.findById(value.get()).orElse(null);
    }

    @Named("tagsDto")
    Set<DocumentTagsEntity> mapDocTag(JsonNullable<List<Integer>> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;

        return new HashSet<DocumentTagsEntity>(documentTagsRepository.findAllById(value.get()));
    }

    // only exists in the entity, is generated here
    @Named("archiveSerialNumberDto")
    Integer mapArchiveSerialNumber(JsonNullable<String> value) {
        if(value==null || !value.isPresent() || value.get()==null) return null;
        return Integer.parseInt(value.get());
    }
}
