package org.openapitools.persistence.repository;

import org.openapitools.persistence.entities.CorrespondentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CorrespondentRepository extends JpaRepository<CorrespondentEntity, Integer> {
}
