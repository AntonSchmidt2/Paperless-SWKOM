package org.tesseract.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tesseract.Entity.DocumentEntityOCR;

@Repository
// Kommunikation zwischen DTO und Datenbank Objekt
public interface DocumentRepositoryOCR extends JpaRepository<DocumentEntityOCR, Integer> {
    DocumentEntityOCR findByTitle(String message);
}
