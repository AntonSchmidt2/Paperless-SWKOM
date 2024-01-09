package org.openapitools.serviceLayer.services;

import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    void uploadDocument(DocumentDTO documentDTO, List<MultipartFile> document);
}
