package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Documents_NoteRepository extends JpaRepository<DocumentNoteEntity, Integer>{
}