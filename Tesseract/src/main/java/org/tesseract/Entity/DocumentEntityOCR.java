package org.tesseract.Entity;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "documents_document")
public class DocumentEntityOCR {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence"
    )
    private Integer id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "created", nullable = false)
    private OffsetDateTime created;

    @Column(name = "modified", nullable = false)
    private OffsetDateTime modified;

    @Column(name = "checksum", length = 32, nullable = false, unique = true)
    private String checksum;

    @Column(name = "added", nullable = false)
    private OffsetDateTime added;

    @Column(name = "storage_type", length = 11, nullable = false)
    private String storageType;

    @Column(name = "filename", length = 1024, unique = true)
    private String filename;

    @Column(name = "archive_serial_number", unique = true)
    private Integer archiveSerialNumber;

    @Column(name = "mime_type", length = 256, nullable = false)
    private String mimeType;

    @Column(name = "archive_checksum", length = 32)
    private String archiveChecksum;

    @Column(name = "archive_filename", length = 1024, unique = true)
    private String archiveFilename;

    @Column(name = "original_filename", length = 1024)
    private String originalFilename;

    //@OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "document_id")  // Add this line to specify the join column
    //private Set<DocumentTagsEntity> tags;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    public void setModified(OffsetDateTime modified) {
        this.modified = modified;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public OffsetDateTime getAdded() {
        return added;
    }

    public void setAdded(OffsetDateTime added) {
        this.added = added;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getArchiveSerialNumber() {
        return archiveSerialNumber;
    }

    public void setArchiveSerialNumber(Integer archiveSerialNumber) {
        this.archiveSerialNumber = archiveSerialNumber;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getArchiveChecksum() {
        return archiveChecksum;
    }

    public void setArchiveChecksum(String archiveChecksum) {
        this.archiveChecksum = archiveChecksum;
    }

    public String getArchiveFilename() {
        return archiveFilename;
    }

    public void setArchiveFilename(String archiveFilename) {
        this.archiveFilename = archiveFilename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }


    //public Set<DocumentTagsEntity> getTags() {
    //    return tags;
    //}

    //public void setTags(Set<DocumentTagsEntity> tags) {
        //this.tags = tags;
    //}
}
