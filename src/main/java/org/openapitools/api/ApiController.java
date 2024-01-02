package org.openapitools.api;

import org.openapitools.serviceLayer.services.OcrService;
import org.openapitools.serviceLayer.services.TesseractService_Deprecated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.services.DocumentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.openapitools.jackson.nullable.JsonNullable;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-10T06:36:40.060738Z[Etc/UTC]")
@Controller // This tells Spring that this class is a Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080") // Allow requests from localhost:8080
public class ApiController implements Api {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);
    private final NativeWebRequest request;
    private final DocumentServiceImpl documentService;

    private final TesseractService_Deprecated ocrService;

    // Autowired constructor based dependency injection
    @Autowired
    public ApiController(NativeWebRequest request, DocumentServiceImpl documentService, TesseractService_Deprecated ocrService, TesseractService_Deprecated ocrService1) {
        this.request = request;
        this.documentService = documentService;
        this.ocrService = ocrService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    //take received document & metadata and send to service layer (documentService)
    @Override
    public ResponseEntity<String> handleDocumentUploadRequest(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {
        try {
            // create DTO from received data
            String name = document.get(0).getOriginalFilename();
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setTitle(JsonNullable.of(title == null ? name : title));
            documentDTO.setOriginalFileName(JsonNullable.of(name));
            documentDTO.setCreated(created);
            documentDTO.setDocumentType(JsonNullable.of(documentType));
            documentDTO.setTags(JsonNullable.of(tags));
            documentDTO.setCorrespondent(JsonNullable.of(correspondent));
            documentDTO.setDocument(document.get(0));

            String text = ocrService.extractTextFromMultipartFile(document.get(0));
            System.out.println(text);

            // call injected service layer method
            documentService.uploadDocument(documentDTO);
            documentService.uploadDocument(documentDTO);

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"Document upload finished\"}");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
}
