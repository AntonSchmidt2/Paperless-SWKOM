package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "paperless_mail_processedmail")
public class PaperlessMailProcessedmail {
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
    @Column(nullable = false, length = 256)
    private String folder;

    @Column(nullable = false, length = 256)
    private String uid;

    @Column(nullable = false, length = 256)
    private String subject;

    @Column(nullable = false)
    private OffsetDateTime received;

    @Column(nullable = false)
    private OffsetDateTime processed;

    @Column(nullable = false, length = 256)
    private String status;

    @Column(columnDefinition = "text")
    private String error;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;


    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    private PaperlessMailMailrule rule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public OffsetDateTime getReceived() {
        return received;
    }

    public void setReceived(OffsetDateTime received) {
        this.received = received;
    }

    public OffsetDateTime getProcessed() {
        return processed;
    }

    public void setProcessed(OffsetDateTime processed) {
        this.processed = processed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(AuthUserEntity owner) {
        this.owner = owner;
    }

    public PaperlessMailMailrule getRule() {
        return rule;
    }

    public void setRule(PaperlessMailMailrule rule) {
        this.rule = rule;
    }
}
