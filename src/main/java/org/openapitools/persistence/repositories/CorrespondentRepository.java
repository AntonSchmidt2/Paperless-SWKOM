package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.CorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondentRepository extends JpaRepository<CorrespondentEntity, Integer>{
}
