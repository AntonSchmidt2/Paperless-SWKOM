package org.openapitools.serviceLayer.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class OcrService {
    private final ITesseract tesseract;

    public OcrService() {
        this.tesseract = new Tesseract();
        this.tesseract.setLanguage("eng");
    }

    public String extractText(MultipartFile file) throws IOException, TesseractException {
        File tempFile = convertToFile(file);

        String text = tesseract.doOCR(tempFile);
        tempFile.delete();

        return text;
    }
    public static File convertToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("temp", null);
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

}
