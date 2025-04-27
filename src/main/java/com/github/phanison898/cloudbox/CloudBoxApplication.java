package com.github.phanison898.cloudbox;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class CloudBoxApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		// Set environment variables as system properties
		System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
		System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
		System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
		SpringApplication.run(CloudBoxApplication.class, args);
	}
}
