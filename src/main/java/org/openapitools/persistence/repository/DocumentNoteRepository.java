package org.openapitools.persistence.repository;

import org.openapitools.persistence.entities.DocumentNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentNoteRepository extends JpaRepository<DocumentNoteEntity, Integer>{
}