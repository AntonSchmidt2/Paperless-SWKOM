package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Documents_Document_TagsRepository extends JpaRepository<DocumentTagsEntity, Integer>{
}