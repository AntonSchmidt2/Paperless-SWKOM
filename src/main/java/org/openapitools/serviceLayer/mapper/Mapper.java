package org.openapitools.serviceLayer.mapper;

public interface Mapper<ENTITY, DTO>{
    ENTITY dtoToEntity(DTO dto);
    DTO entityToDTO(ENTITY entity);
}
