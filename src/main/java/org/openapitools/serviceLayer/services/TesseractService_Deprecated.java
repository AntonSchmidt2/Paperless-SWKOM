package org.openapitools.serviceLayer.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class TesseractService_Deprecated {
    private final ITesseract tesseract;

    public TesseractService_Deprecated() {
        this.tesseract = new Tesseract();
        // Set Tesseract configurations if needed
        // e.g., tesseract.setLanguage("eng");
    }

    public String extractTextFromMultipartFile(MultipartFile multipartFile) throws IOException, TesseractException {
        // Convert MultipartFile to File
        BufferedImage image = convertMultipartFileToBufferedImage(multipartFile);
        System.out.println("past casting to file");

        if (image == null) {
            System.out.println("BufferedImage is null");
            throw new IllegalArgumentException("BufferedImage is null");
        }
        // Perform OCR
        String extractedText = tesseract.doOCR(image);

        return extractedText;
    }

    public static BufferedImage convertMultipartFileToBufferedImage(MultipartFile multipartFile) throws IOException {
        // Check if the file is a PDF
        if ("application/pdf".equals(multipartFile.getContentType())) {
            return convertPdfToImage(multipartFile);
        }

        // For non-PDF files, proceed with the original logic
        byte[] fileContent = multipartFile.getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileContent);

        return ImageIO.read(byteArrayInputStream);
    }
    private static BufferedImage convertPdfToImage(MultipartFile pdfFile) throws IOException {
        try (PDDocument document = PDDocument.load(pdfFile.getInputStream())) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            return pdfRenderer.renderImage(0);
        }
    }
}
