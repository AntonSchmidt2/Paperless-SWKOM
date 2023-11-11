package org.openapitools.persistence.repository;

import org.openapitools.persistence.entities.PaperlessMailMailaccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailaccountRepository extends JpaRepository<PaperlessMailMailaccountEntity, Integer>{
}
