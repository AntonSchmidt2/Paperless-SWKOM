package org.openapitools.entity;


import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.*;

@Getter
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentEntity {

    @javax.persistence.Id
    @Column
    @SequenceGenerator(
            name = "document_id_seq",
            sequenceName = "document_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "document_id_seq"
    )
    private Integer id;

    @Column(nullable = false, length = 128)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private OffsetDateTime created;

    @Column(nullable = false)
    private OffsetDateTime modified;

    @Column(nullable = false, length = 32)
    private String checksum;

    @Column(nullable = false)
    private OffsetDateTime added;

    @Column(nullable = false, length = 11)
    private String storageType;

    @Column(length = 1024)
    private String filename;

    @Column
    private Integer archiveSerialNumber;

    @Column(nullable = false, length = 256)
    private String mimeType;

    @Column(length = 32)
    private String archiveChecksum;

    @Column(length = 1024)
    private String archiveFilename;

    @Column(length = 1024)
    private String originalFilename;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "correspondent_id")
    private CorrespondentEntity correspondent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumenttypeEntity documentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_path_id")
    private StoragepathEntity storagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "document")
    private Set<NoteEntity> documentNoteEntities;

    @OneToMany(mappedBy = "document")
    private Set<DocumentTagsEntity> documentTagsEntities;
    */

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setCreated(final OffsetDateTime created) {
        this.created = created;
    }

    public void setModified(final OffsetDateTime modified) {
        this.modified = modified;
    }

    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }

    public void setAdded(final OffsetDateTime added) {
        this.added = added;
    }

    public void setStorageType(final String storageType) {
        this.storageType = storageType;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public void setArchiveSerialNumber(final Integer archiveSerialNumber) {
        this.archiveSerialNumber = archiveSerialNumber;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public void setArchiveChecksum(final String archiveChecksum) {
        this.archiveChecksum = archiveChecksum;
    }

    public void setArchiveFilename(final String archiveFilename) {
        this.archiveFilename = archiveFilename;
    }

    public void setOriginalFilename(final String originalFilename) {
        this.originalFilename = originalFilename;
    }


}
