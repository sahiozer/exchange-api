package com.gold_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gold_api", "com.service"}) // Add additional packages here
public class GoldApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldApiApplication.class, args);
	}

}
