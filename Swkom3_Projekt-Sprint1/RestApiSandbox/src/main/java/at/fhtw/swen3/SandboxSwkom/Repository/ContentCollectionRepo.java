package at.fhtw.swen3.SandboxSwkom.Repository;

import at.fhtw.swen3.SandboxSwkom.Model.Document;
import at.fhtw.swen3.SandboxSwkom.Model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// a simple in memory "database" - list of Documents that can be populated and managed
@Repository
public class ContentCollectionRepo {
    private final List<Document> documentList = new ArrayList<>();
    public ContentCollectionRepo(){
    }

    // returns whole list
    public List<Document> getAll (){
        return documentList;
    }
    //returns document which matches argument ID
    public Optional<Document> getSpecificID(Integer ID){
       return documentList.stream().filter(document -> document.ID().equals(ID)).findFirst();
    }
    //checks if document with argument ID exists and returns boolean
    public boolean existsByID(Integer id) {
        return documentList.stream().filter(c -> c.ID().equals(id)).count() == 1;
    }
    //checks if document with given id already exists -
    // if yes deletes it and appends new document, else just appends new document
    public void save(Document newContent) {
        documentList.removeIf(content -> Objects.equals(content.ID(), newContent.ID()));
        documentList.add(newContent);
    }

    // appends initial entry to the list
    // @PostConstruct ensures execution before class is put into service
    @PostConstruct
    public void initEntry(){
        Document docu1 = new Document(1,"Hello", "World", Type.Documentation, LocalDateTime.now(), null);
        documentList.add(docu1);
    }

    // deletes document with argument ID
    public void deleteByID(Integer id) {
        documentList.removeIf(content -> Objects.equals(content.ID(), id));
    }
}
