package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "documents_tag")
public class TagEntity {
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

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "match", length = 256, nullable = false)
    private String match;

    @Column(name = "matching_algorithm", nullable = false)
    private Integer matchingAlgorithm;

    @Column(name = "is_insensitive", nullable = false)
    private Boolean isInsensitive;

    @Column(name = "is_inbox_tag", nullable = false)
    private Boolean isInboxTag;

    @Column(name = "color", length = 7, nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentTagsEntity> documentTagsEntities;

    //@OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<PaperlessMailMailruleAssignTags> tagPaperlessMailMailruleAssignTagsEntities;

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

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public Integer getMatchingAlgorithm() {
        return matchingAlgorithm;
    }

    public void setMatchingAlgorithm(Integer matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public Boolean getInsensitive() {
        return isInsensitive;
    }

    public void setInsensitive(Boolean insensitive) {
        isInsensitive = insensitive;
    }

    public Boolean getInboxTag() {
        return isInboxTag;
    }

    public void setInboxTag(Boolean inboxTag) {
        isInboxTag = inboxTag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<DocumentTagsEntity> getDocumentTagsEntities() {
        return documentTagsEntities;
    }

    public void setDocumentTagsEntities(Set<DocumentTagsEntity> documentTagsEntities) {
        this.documentTagsEntities = documentTagsEntities;
    }
}
