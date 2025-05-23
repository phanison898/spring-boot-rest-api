package com.github.phanison898.cloudbox;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudBoxApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		// Load and validate environment variables
		String dbUrl = dotenv.get("DB_URL");
		String dbUsername = dotenv.get("DB_USERNAME");
		String dbPassword = dotenv.get("DB_PASSWORD");
		String jwtSecretKey = dotenv.get("JWT_SECRET_KEY");

		if (dbUrl == null || dbUsername == null || dbPassword == null || jwtSecretKey == null) {
			throw new IllegalStateException("Missing required environment variables: DB_URL, DB_USERNAME, or DB_PASSWORD or JWT_SECRET_KEY");
		}

		// Set environment variables as system properties
		System.setProperty("DB_URL", dbUrl);
		System.setProperty("DB_USERNAME", dbUsername);
		System.setProperty("DB_PASSWORD", dbPassword);
		System.setProperty("JWT_SECRET_KEY", jwtSecretKey);

		SpringApplication.run(CloudBoxApplication.class, args);
	}
}