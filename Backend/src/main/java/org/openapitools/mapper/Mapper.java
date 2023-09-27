package org.openapitools.mapper;

import org.openapitools.entites.DocumentEntity;
import org.openapitools.model.DocumentTest;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public DocumentTest toDto(DocumentEntity document) {
      String name = document.getMyName();
      String id = document.getMyId() + " - Hallo";
      return new DocumentTest(id, name);
    }
}
