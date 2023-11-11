package org.openapitools.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "paperless_mail_mailrule_assign_tags")
public class PaperlessMailMailruleAssignTagsEntity {
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
    @JoinColumn(name = "mailrule_id", nullable = false)
    private PaperlessMailMailruleEntity mailrule;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaperlessMailMailruleEntity getMailrule() {
        return mailrule;
    }

    public void setMailrule(PaperlessMailMailruleEntity mailrule) {
        this.mailrule = mailrule;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
