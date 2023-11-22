package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Kommunikation zwischen DTO und Datenbank Objekt
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
}
