package org.openapitools.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "paperless_mail_processedmail")
public class PaperlessMailProcessedmailEntity {
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

}
