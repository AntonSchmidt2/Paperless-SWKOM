package org.openapitools.serviceLayer.mapper;

import org.mapstruct.*;
import org.openapitools.jackson.nullable.JsonNullable;
import org.openapitools.persistence.entities.*;
import org.openapitools.persistence.repositories.*;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public class DocumentMapper implements GenericMapper<DocumentDTO, DocumentEntity> {

    @Autowired
    private Documents_CorrespondentRepository correspondentRepository;
    @Autowired
    private Documents_DocumentTypeRepository documentTypeRepository;
    @Autowired
    private Documents_StoragepathRepository storagePathRepository;
    @Autowired
    private Auth_UserRepository userRepository;
    @Autowired
    private Documents_Document_TagsRepository documentTagsRepository;

    @Override
    public DocumentEntity DtotoEntity(DocumentDTO documentDTO) {
        DocumentEntity entity = new DocumentEntity();
        entity.setId(documentDTO.getId());
        entity.setTitle(documentDTO.getTitle().get());
        entity.setContent(documentDTO.getContent().get());
        entity.setCreated(documentDTO.getCreated());
        entity.setModified(documentDTO.getModified());
        entity.setAdded(documentDTO.getAdded());
        entity.setOriginalFilename(documentDTO.getOriginalFileName().get());

        // Map correspondent
        CorrespondentEntity correspondent = mapCorrespondentDTO(documentDTO.getCorrespondent());
        entity.setCorrespondent(correspondent);

        // Map documentType
        DocumentTypeEntity documentType = mapDocumentType(documentDTO.getDocumentType());
        entity.setDocumentType(documentType);

        // Map ArchiveSerialNumber
        Integer ArchiveSerialNumber = mapArchiveSerialNumber(JsonNullable.of(documentDTO.getArchiveSerialNumber().toString()));
        entity.setArchiveSerialNumber(ArchiveSerialNumber);

        // Map storagePath
        StoragePathEntity storagePath = mapStoragePath(documentDTO.getStoragePath());
        entity.setStoragePath(storagePath);

        // Map owner
        ///////////////// Not sure how to do this, DocumentDTO has no AuthUser stuff?
        //AuthUserEntity owner = mapOwner(documentDTO.getOwner());
        //entity.setOwner(owner);

        // Map tags
        Set<DocumentTagsEntity> tags = mapTagsDTO(documentDTO.getTags());
        entity.setDocumentTagsEntities(tags);

        return entity;
    }

    @Override
    public DocumentDTO EntitytoDto(DocumentEntity documentEntity) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(documentEntity.getId());
        dto.setTitle(JsonNullable.of(documentEntity.getTitle()));
        dto.setContent(JsonNullable.of(documentEntity.getContent()));
        dto.setCreated(documentEntity.getCreated());
        dto.setModified(documentEntity.getModified());
        dto.setAdded(documentEntity.getAdded());
        dto.setOriginalFileName(JsonNullable.of(documentEntity.getOriginalFilename()));

        // Map correspondent
        Integer correspondentId = mapCorrespondent(documentEntity.getCorrespondent());
        dto.setCorrespondent(JsonNullable.of(correspondentId));

        // Map created Date from Date/Time
        OffsetDateTime createdDate = mapCreatedDate(documentEntity.getCreated());
        dto.setCreatedDate(createdDate);

        // Map ArchiveSerialNumber
        Integer ArchiveSerialNumber = mapArchiveSerialNumber(JsonNullable.of(documentEntity.getArchiveSerialNumber().toString()));
        dto.setArchiveSerialNumber(JsonNullable.of(ArchiveSerialNumber.toString()));

        // Map documentType
        dto.setDocumentType(JsonNullable.of(documentEntity.getDocumentType().getId()));

        // Map storagePath
        dto.setStoragePath(JsonNullable.of(documentEntity.getStoragePath().getId()));

        // Map tags
        List<Integer> tagIds = mapTags(documentEntity.getDocumentTagsEntities());
        dto.setTags(JsonNullable.of(tagIds));

        // Map owner
        ///////////////// Not sure how to do this, DocumentDTO has no AuthUser stuff?
        //AuthUserEntity owner = mapOwner(documentEntity.getOwner());
        //entity.setOwner(owner);


        return dto;
    }

    // map created to createdDate (Date without the time)
    @Named("createdToCreatedDate")
    OffsetDateTime mapCreatedDate(OffsetDateTime value) {
        return value != null ? value.withOffsetSameInstant(ZoneOffset.UTC).toLocalDate().atStartOfDay().atOffset(ZoneOffset.UTC) : null;
    }

    private static Integer mapCorrespondent(CorrespondentEntity correspondent) {
        return (correspondent != null) ? correspondent.getId() : null;
    }

    @Named("correspondentDto")
    CorrespondentEntity mapCorrespondentDTO(JsonNullable<Integer> value) {
        return correspondentRepository.findById(value.get()).orElse(null);
    }

    @Named("documentTypeDto")
    DocumentTypeEntity mapDocumentType(JsonNullable<Integer> value) {
        return documentTypeRepository.findById(value.get()).orElse(null);
    }

    @Named("storagePathDto")
    StoragePathEntity mapStoragePath(JsonNullable<Integer> value) {
        return storagePathRepository.findById(value.get()).orElse(null);
    }

    @Named("ownerDto")
    AuthUserEntity mapOwner(JsonNullable<Integer> value) {
        return userRepository.findById(value.get()).orElse(null);
    }

    @Named("tagsDto")
    Set<DocumentTagsEntity> mapTagsDTO(JsonNullable<List<Integer>> value) {
        return new HashSet<DocumentTagsEntity>(documentTagsRepository.findAllById(value.get()));
    }


    private static List<Integer> mapTags(Set<DocumentTagsEntity> tags) {
        return tags.stream()
                .map(tag -> (int) tag.getId())
                .collect(Collectors.toList());
    }

    @Named("archiveSerialNumberDto")
    Integer mapArchiveSerialNumber(JsonNullable<String> value) {
        if (value == null || !value.isPresent() || value.get() == null) return null;
        return Integer.parseInt(value.get());
    }

    @Named("archiveSerialNumberDto")
    Integer mapArchiveSerialNumberDTO(JsonNullable<String> value) {
        if (value == null || !value.isPresent() || value.get() == null) return null;
        return Integer.parseInt(value.get());
    }
}