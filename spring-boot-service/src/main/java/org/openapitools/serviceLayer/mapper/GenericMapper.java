package org.openapitools.serviceLayer.mapper;

public interface GenericMapper<Dto, Entity> {
    Entity DtotoEntity(Dto dto);
    Dto EntitytoDto(Entity entity);
}
