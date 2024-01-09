package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.DocumentTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface DocumentTagsRepository extends JpaRepository<DocumentTagsEntity, Integer> {
}
