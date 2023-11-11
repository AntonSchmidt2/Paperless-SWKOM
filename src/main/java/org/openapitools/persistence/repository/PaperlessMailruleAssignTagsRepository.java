package org.openapitools.persistence.repository;

import org.openapitools.persistence.entities.PaperlessMailMailruleAssignTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailruleAssignTagsRepository extends JpaRepository<PaperlessMailMailruleAssignTagsEntity, Integer>{
}
