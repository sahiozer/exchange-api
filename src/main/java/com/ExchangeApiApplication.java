package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gold_api", "com.service"}) // Add additional packages here
public class ExchangeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeApiApplication.class, args);
	}

}
