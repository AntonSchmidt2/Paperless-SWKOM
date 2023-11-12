package org.openapitools.persistence.repositories;

import org.openapitools.persistence.entities.StoragePathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoragepathRepository extends JpaRepository<StoragePathEntity, Integer>{
}