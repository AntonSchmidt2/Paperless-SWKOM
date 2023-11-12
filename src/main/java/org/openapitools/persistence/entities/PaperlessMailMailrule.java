package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "paperless_mail_mailrule")
public class PaperlessMailMailrule {
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

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "folder", nullable = false)
    private String folder;

    @Column(name = "filter_from")
    private String filterFrom;

    @Column(name = "filter_subject")
    private String filterSubject;

    @Column(name = "filter_body")
    private String filterBody;

    @Column(name = "maximum_age", nullable = false)
    private Integer maximumAge;

    @Column(name = "action", nullable = false)
    private Integer action;

    @Column(name = "action_parameter")
    private String actionParameter;

    @Column(name = "assign_title_from", nullable = false)
    private Integer assignTitleFrom;

    @Column(name = "assign_correspondent_from", nullable = false)
    private Integer assignCorrespondentFrom;

    @Column(name = "order", nullable = false)
    private Integer order;

    @Column(name = "attachment_type", nullable = false)
    private Integer attachmentType;

    @Column(name = "filter_attachment_filename")
    private String filterAttachmentFilename;

    @Column(name = "consumption_scope", nullable = false)
    private Integer consumptionScope;

    @Column(name = "filter_to")
    private String filterTo;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private PaperlessMailMailaccount account;

    @ManyToOne
    @JoinColumn(name = "assign_correspondent_id", nullable = false)
    private CorrespondentEntity assignCorrespondent;

    @ManyToOne
    @JoinColumn(name = "assign_document_type_id", nullable = false)
    private DocumentTypeEntity assignDocumentType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "mailRule")
    private Set<PaperlessMailProcessedmail> processedMails;

    @OneToMany(mappedBy = "mailRule")
    private Set<PaperlessMailMailruleAssignTags> assignTags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFilterFrom() {
        return filterFrom;
    }

    public void setFilterFrom(String filterFrom) {
        this.filterFrom = filterFrom;
    }

    public String getFilterSubject() {
        return filterSubject;
    }

    public void setFilterSubject(String filterSubject) {
        this.filterSubject = filterSubject;
    }

    public String getFilterBody() {
        return filterBody;
    }

    public void setFilterBody(String filterBody) {
        this.filterBody = filterBody;
    }

    public Integer getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(Integer maximumAge) {
        this.maximumAge = maximumAge;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getActionParameter() {
        return actionParameter;
    }

    public void setActionParameter(String actionParameter) {
        this.actionParameter = actionParameter;
    }

    public Integer getAssignTitleFrom() {
        return assignTitleFrom;
    }

    public void setAssignTitleFrom(Integer assignTitleFrom) {
        this.assignTitleFrom = assignTitleFrom;
    }

    public Integer getAssignCorrespondentFrom() {
        return assignCorrespondentFrom;
    }

    public void setAssignCorrespondentFrom(Integer assignCorrespondentFrom) {
        this.assignCorrespondentFrom = assignCorrespondentFrom;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getFilterAttachmentFilename() {
        return filterAttachmentFilename;
    }

    public void setFilterAttachmentFilename(String filterAttachmentFilename) {
        this.filterAttachmentFilename = filterAttachmentFilename;
    }

    public Integer getConsumptionScope() {
        return consumptionScope;
    }

    public void setConsumptionScope(Integer consumptionScope) {
        this.consumptionScope = consumptionScope;
    }

    public String getFilterTo() {
        return filterTo;
    }

    public void setFilterTo(String filterTo) {
        this.filterTo = filterTo;
    }

    public PaperlessMailMailaccount getAccount() {
        return account;
    }

    public void setAccount(PaperlessMailMailaccount account) {
        this.account = account;
    }

    public CorrespondentEntity getAssignCorrespondent() {
        return assignCorrespondent;
    }

    public void setAssignCorrespondent(CorrespondentEntity assignCorrespondent) {
        this.assignCorrespondent = assignCorrespondent;
    }

    public DocumentTypeEntity getAssignDocumentType() {
        return assignDocumentType;
    }

    public void setAssignDocumentType(DocumentTypeEntity assignDocumentType) {
        this.assignDocumentType = assignDocumentType;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<PaperlessMailProcessedmail> getProcessedMails() {
        return processedMails;
    }

    public void setProcessedMails(Set<PaperlessMailProcessedmail> processedMails) {
        this.processedMails = processedMails;
    }

    public Set<PaperlessMailMailruleAssignTags> getAssignTags() {
        return assignTags;
    }

    public void setAssignTags(Set<PaperlessMailMailruleAssignTags> assignTags) {
        this.assignTags = assignTags;
    }
}
