package org.tesseract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TesseractApplication {
	private static final Logger logger = LoggerFactory.getLogger(TesseractApplication.class);

		public static void main(String[] args) {
			logger.info("OCR worker started");
			SpringApplication.run(TesseractApplication.class, args);
		}
}
