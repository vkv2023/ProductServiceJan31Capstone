package com.example.ProductServiceJan31Capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductServiceJan31CapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceJan31CapstoneApplication.class, args);
	}

}
