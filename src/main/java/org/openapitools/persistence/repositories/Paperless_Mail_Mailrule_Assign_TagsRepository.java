package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.PaperlessMailMailruleAssignTagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Paperless_Mail_Mailrule_Assign_TagsRepository extends JpaRepository<PaperlessMailMailruleAssignTagsEntity, Integer>{
}
