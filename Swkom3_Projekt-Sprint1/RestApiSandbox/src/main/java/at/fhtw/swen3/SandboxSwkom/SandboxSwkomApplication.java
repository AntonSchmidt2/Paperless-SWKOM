package at.fhtw.swen3.SandboxSwkom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

// start the project and go to url http://localhost:8082/Paperless/main to see all document entries

@SpringBootApplication
public class SandboxSwkomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandboxSwkomApplication.class, args);
	}
}
