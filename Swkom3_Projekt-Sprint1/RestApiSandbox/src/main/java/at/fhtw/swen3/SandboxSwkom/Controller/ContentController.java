package at.fhtw.swen3.SandboxSwkom.Controller;

import at.fhtw.swen3.SandboxSwkom.Model.Document;
import at.fhtw.swen3.SandboxSwkom.Repository.ContentCollectionRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

// Controller for document management, communicates with ContentCollectionRepo for CRUD stuff
@RestController
@RequestMapping("Paperless/main")
@CrossOrigin
public class ContentController {
    private final ContentCollectionRepo collectionRepo;

    //dependency injection of the collectionRepo instance
    public ContentController(ContentCollectionRepo collectionRepo) {
        this.collectionRepo = collectionRepo;
    }

    //find all of the entries in the repository
    @GetMapping("") // the path gets inherited from class mapping
    public List<Document> findAll(){
        return collectionRepo.getAll();
    }

    //find document by specific id - append /{id} to domain (example http://localhost:8082/Paperless/main/1)
    //throws 404 error in case document with given ID doesnt exist
    @GetMapping("/{id}")
    public Document findSpecific(@PathVariable Integer id){
        return collectionRepo.getSpecificID(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No document with given ID found"));
    }

    //create a new document
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createContent(@Valid @RequestBody Document newContent){
        collectionRepo.save(newContent);
    }

    //update an existing document by ID, throws 204 message if request goes through but ID not found
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@Valid @RequestBody Document updatedContent,@PathVariable Integer id){
        if(!collectionRepo.existsByID(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No document with given ID found");
        }

        collectionRepo.save(updatedContent);
    }

    //deletes existing document by ID, throws 204 message if request goes through but ID not found
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id){
        collectionRepo.deleteByID(id);
    }

}
