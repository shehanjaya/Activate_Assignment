package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Lunch Decider",version = "1.0", description = "Lunch Decider API"))
public class LunchDeciderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchDeciderApplication.class, args);
	}

}
