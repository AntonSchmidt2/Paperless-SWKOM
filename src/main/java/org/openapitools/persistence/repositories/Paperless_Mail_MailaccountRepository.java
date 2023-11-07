package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.PaperlessMailMailaccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Paperless_Mail_MailaccountRepository extends JpaRepository<PaperlessMailMailaccountEntity, Integer>{
}
