package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.PaperlessMailMailruleAssignTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailruleAssignTagsRepository extends JpaRepository<PaperlessMailMailruleAssignTags, Integer>{
}
