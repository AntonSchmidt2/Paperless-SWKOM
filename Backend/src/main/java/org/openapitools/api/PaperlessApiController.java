package org.openapitools.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-20T14:37:30.836739600+02:00[Europe/Vienna]")
@Controller
@RequestMapping("${openapi.mockServer.base-path:}")
public class PaperlessApiController implements PaperlessApi {

    private final NativeWebRequest request;

    @Autowired
    public PaperlessApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
