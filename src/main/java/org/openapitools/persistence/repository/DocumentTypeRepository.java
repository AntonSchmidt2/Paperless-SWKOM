package org.openapitools.persistence.repository;

import org.openapitools.persistence.entities.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Integer> {
}
