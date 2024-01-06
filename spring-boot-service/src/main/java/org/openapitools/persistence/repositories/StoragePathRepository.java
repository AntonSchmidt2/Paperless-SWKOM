package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.StoragePathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface StoragePathRepository extends JpaRepository<StoragePathEntity, Integer>{
}
