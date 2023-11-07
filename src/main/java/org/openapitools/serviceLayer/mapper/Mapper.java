package org.openapitools.serviceLayer.mapper;

import org.openapitools.jackson.nullable.JsonNullable;

import java.time.OffsetDateTime;

public interface Mapper<ENTITY, DTO>{
    ENTITY dtoToEntity(DTO dto);
    DTO entityToDTO(ENTITY entity);
    default String map(JsonNullable<String> value) {

        if(value == null || !value.isPresent()) {
            return null;
        }

        return value.get();
    }

    default JsonNullable<String> map(String value) {
        return JsonNullable.of(value);
    }

    default OffsetDateTime mapOffsetDateTime(String value){
        return OffsetDateTime.parse(value);
    }

    default String mapOffsetDateTime(OffsetDateTime value){
        return value.toString();
    }

}
