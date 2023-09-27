package org.openapitools.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.openapitools.entites.DocumentEntity;
import org.openapitools.mapper.Mapper;
import org.openapitools.model.DocumentTest;
import org.openapitools.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.openapitools.model.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/documents")
public class DocumentService {

  @Autowired
  private DocumentRepo repo;

  private Mapper mapper = new Mapper();

  @GetMapping
  @ResponseBody
  public List<DocumentTest> getDocuments() {
    repo = new DocumentRepo();
    return repo.getAllDocuments()
        .stream()
        .map(mapper::toDto)
        .collect(toList());
  }
}
