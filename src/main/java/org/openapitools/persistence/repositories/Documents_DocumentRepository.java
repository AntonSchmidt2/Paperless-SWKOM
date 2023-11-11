package org.openapitools.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.openapitools.persistence.entities.DocumentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface Documents_DocumentRepository extends JpaRepository<DocumentEntity, Integer>{
}
