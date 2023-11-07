package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Documents_TagRepository extends JpaRepository<TagEntity, Integer> {
}