package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.PaperlessMailMailaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperlessMailaccountRepository extends JpaRepository<PaperlessMailMailaccount, Integer>{
}
