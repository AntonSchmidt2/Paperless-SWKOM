package org.openapitools.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.persistence.entities.AuthUserEntity;
import org.openapitools.persistence.entities.DocumentTagsEntity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "documents_tag")
@Getter
@Setter
public class DocumentsTagEntity {

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

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 256)
    private String match;

    @Column(nullable = false)
    private Integer matchingAlgorithm;

    @Column(nullable = false)
    private Boolean isInsensitive;

    @Column(nullable = false)
    private Boolean isInboxTag;

    @Column(nullable = false, length = 7)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "tag")
    private Set<DocumentTagsEntity> tagDocumentTags;
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(final String match) {
        this.match = match;
    }

    public Integer getMatchingAlgorithm() {
        return matchingAlgorithm;
    }

    public void setMatchingAlgorithm(final Integer matchingAlgorithm) {
        this.matchingAlgorithm = matchingAlgorithm;
    }

    public Boolean getIsInsensitive() {
        return isInsensitive;
    }

    public void setIsInsensitive(final Boolean isInsensitive) {
        this.isInsensitive = isInsensitive;
    }

    public Boolean getIsInboxTag() {
        return isInboxTag;
    }

    public void setIsInboxTag(final Boolean isInboxTag) {
        this.isInboxTag = isInboxTag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(final AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<DocumentTagsEntity> getTagDocumentTags() {
        return tagDocumentTags;
    }

    public void setTagDocumentTags(
            final Set<DocumentTagsEntity> tagDocumentTags) {
        this.tagDocumentTags = tagDocumentTags;
    }


}