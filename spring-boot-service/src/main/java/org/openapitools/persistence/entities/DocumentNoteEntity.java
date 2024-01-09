package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "documents_note")
public class DocumentNoteEntity {
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

    @Column(name = "note", nullable = false)
    private String note;

    @Column(name = "created", nullable = false)
    private OffsetDateTime created;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private DocumentEntity document;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUserEntity user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public AuthUserEntity getUser() {
        return user;
    }

    public void setUser(AuthUserEntity user) {
        this.user = user;
    }
}
