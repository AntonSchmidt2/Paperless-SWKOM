package org.openapitools.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "auth_user")
public class AuthUserEntity {
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

    @Column(nullable = false, length = 150)
    private String name;

    //@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<AuthUserGroups> groupAuthUserGroupses;

    //@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<AuthGroupPermissions> groupAuthGroupPermissionses;

}
