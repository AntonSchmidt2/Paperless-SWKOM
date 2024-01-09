package org.openapitools.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "documents_document_tags")
public class DocumentTagsEntity {
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

    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
