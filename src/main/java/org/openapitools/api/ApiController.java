package org.openapitools.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.openapitools.serviceLayer.dto.DocumentDTO;
import org.openapitools.serviceLayer.dto.responseOK.GetDocument200Response;
import org.openapitools.serviceLayer.dto.responseOK.GetDocuments200Response;
import org.openapitools.serviceLayer.services.DocumentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.openapitools.jackson.nullable.JsonNullable;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-10T06:36:40.060738Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080")
public class ApiController implements Api {

    private final NativeWebRequest request;

    @Autowired
    public ApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
