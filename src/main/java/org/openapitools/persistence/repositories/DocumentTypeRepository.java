package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Integer> {
}