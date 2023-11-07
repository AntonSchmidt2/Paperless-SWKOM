package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "documents_correspondent")
public class CorrespondentEntity {
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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AuthUserEntity owner;

    @OneToMany(mappedBy = "correspondent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentEntity> correspondentDocumentEntities;

    //update mappedBy parameter when Mailrule Entity finished
    @OneToMany(mappedBy = "assignCorrespondent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PaperlessMailMailruleEntity> assignCorrespondentPaperlessMailMailrules;

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

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(AuthUserEntity owner) {
        this.owner = owner;
    }

    public Set<DocumentEntity> getCorrespondentDocumentEntities() {
        return correspondentDocumentEntities;
    }

    public void setCorrespondentDocumentEntities(Set<DocumentEntity> correspondentDocumentEntities) {
        this.correspondentDocumentEntities = correspondentDocumentEntities;
    }

    public Set<PaperlessMailMailruleEntity> getAssignCorrespondentPaperlessMailMailrules() {
        return assignCorrespondentPaperlessMailMailrules;
    }

    public void setAssignCorrespondentPaperlessMailMailrules(Set<PaperlessMailMailruleEntity> assignCorrespondentPaperlessMailMailrules) {
        this.assignCorrespondentPaperlessMailMailrules = assignCorrespondentPaperlessMailMailrules;
    }
}
