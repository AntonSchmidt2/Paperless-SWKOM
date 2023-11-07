package org.openapitools.persistence.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "paperless_mail_mailaccount")
public class PaperlessMailMailaccountEntity {
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

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "imap_server", nullable = false)
    private String imapServer;

    @Column(name = "imap_port")
    private Integer imapPort;

    @Column(name = "imap_security", nullable = false)
    private Integer imapSecurity;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "character_set", nullable = false)
    private String characterSet;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private AuthUserEntity owner;

    @Column(name = "is_token", nullable = false)
    private Boolean isToken;

    @OneToMany(mappedBy = "mailaccount", cascade = CascadeType.ALL)
    private Set<PaperlessMailMailruleEntity> mailRules;

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

    public String getImapServer() {
        return imapServer;
    }

    public void setImapServer(String imapServer) {
        this.imapServer = imapServer;
    }

    public Integer getImapPort() {
        return imapPort;
    }

    public void setImapPort(Integer imapPort) {
        this.imapPort = imapPort;
    }

    public Integer getImapSecurity() {
        return imapSecurity;
    }

    public void setImapSecurity(Integer imapSecurity) {
        this.imapSecurity = imapSecurity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }

    public AuthUserEntity getOwner() {
        return owner;
    }

    public void setOwner(AuthUserEntity owner) {
        this.owner = owner;
    }

    public Boolean getToken() {
        return isToken;
    }

    public void setToken(Boolean token) {
        isToken = token;
    }

    public Set<PaperlessMailMailruleEntity> getMailRules() {
        return mailRules;
    }

    public void setMailRules(Set<PaperlessMailMailruleEntity> mailRules) {
        this.mailRules = mailRules;
    }
}
