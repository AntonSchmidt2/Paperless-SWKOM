package org.openapitools.repo;

import java.util.ArrayList;
import org.openapitools.entites.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentRepo {

  public ArrayList<DocumentEntity> getAllDocuments(){

    //connection to DB
    ArrayList<DocumentEntity>documents = new ArrayList<>();
    documents.add(new DocumentEntity("1","Anton"));
    documents.add(new DocumentEntity("2","Anton2"));
    documents.add(new DocumentEntity("3","Anton3"));
    documents.add(new DocumentEntity("4","Anton4"));
    documents.add(new DocumentEntity("5","Anton5"));

    return documents;
  }

}
