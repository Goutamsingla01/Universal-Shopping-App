package com.shoppingapp.universal_shopping_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
@SpringBootApplication
public class UniversalShoppingAppApplication {

	private final Environment environment;

	// Constructor injection to get the Environment object
	public UniversalShoppingAppApplication(Environment environment) {
		this.environment = environment;
	}
	public static void main(String[] args) {
		SpringApplication.run(UniversalShoppingAppApplication.class, args);
	}
	@PostConstruct
	public void init() {
		// Print active profiles
		System.out.println("Active Profiles: " + String.join(", ", environment.getActiveProfiles()));
		// Print database URL (make sure to use the right property key)
		System.out.println("Database URL: " + environment.getProperty("spring.datasource.url"));
	}
}
