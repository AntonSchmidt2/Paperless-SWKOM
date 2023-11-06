package org.openapitools;

import org.junit.jupiter.api.Test;
import org.openapitools.entity.DocumentEntity;
import org.openapitools.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@DataJpaTest
public class DocumentEntityTest {

    @Autowired
    private DocumentRepository documentRepository;
    @Test
    public void testSaveDocumentEntity() {
        DocumentEntity document = new DocumentEntity();
        // Set properties of the document as needed
        document.setTitle("Test Document");
        document.setContent("Test Content");

        // Save the document to the database
        documentRepository.save(document);

        // Retrieve the saved document
        DocumentEntity savedDocument = documentRepository.findById(document.getId()).orElse(null);

        // Assert that the saved document is not null
        assertNotNull(savedDocument);
        // Add more assertions based on your use case
    }
}
