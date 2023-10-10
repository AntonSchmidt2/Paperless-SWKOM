package org.openapitools.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

// controller responsible for handling incoming HTTP requests.
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-20T14:37:30.836739600+02:00[Europe/Vienna]")
@Controller
@RequestMapping("${openapi.mockServer.base-path:}")
public class PaperlessApiController implements PaperlessApi {

    // Store a reference to the current web request
    private final NativeWebRequest request;

    // Constructor to inject the NativeWebRequest object
    @Autowired
    public PaperlessApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
