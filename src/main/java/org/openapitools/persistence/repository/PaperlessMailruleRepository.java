package org.openapitools.persistence.repository;

import org.openapitools.persistence.entities.PaperlessMailMailruleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailruleRepository extends JpaRepository<PaperlessMailMailruleEntity, Integer>{
}