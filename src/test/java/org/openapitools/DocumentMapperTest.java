package org.openapitools;

import org.mockito.InjectMocks;
import org.openapitools.persistence.repository.DocumentRepository;
import org.openapitools.serviceLayer.mapper.DocumentMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class DocumentMapperTest {
    @MockBean
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentMapper documentMapper = DocumentMapper.INSTANCE;

}
