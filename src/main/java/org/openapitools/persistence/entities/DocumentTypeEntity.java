package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "documents_documenttype")
public class DocumentTypeEntity {
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

    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentEntity> documentTypeDocumentEntities;

    @OneToMany(mappedBy = "assignDocumentType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PaperlessMailMailruleEntity> assignDocumentTypePaperlessMailMailruleEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<DocumentEntity> getDocumentTypeDocumentEntities() {
        return documentTypeDocumentEntities;
    }

    public void setDocumentTypeDocumentEntities(Set<DocumentEntity> documentTypeDocumentEntities) {
        this.documentTypeDocumentEntities = documentTypeDocumentEntities;
    }

    public Set<PaperlessMailMailruleEntity> getAssignDocumentTypePaperlessMailMailruleEntities() {
        return assignDocumentTypePaperlessMailMailruleEntities;
    }

    public void setAssignDocumentTypePaperlessMailMailruleEntities(Set<PaperlessMailMailruleEntity> assignDocumentTypePaperlessMailMailruleEntities) {
        this.assignDocumentTypePaperlessMailMailruleEntities = assignDocumentTypePaperlessMailMailruleEntities;
    }
}
