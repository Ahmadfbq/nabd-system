package com.example.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class userServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(userServiceApplication.class, args);
	}

	@GetMapping
	public String	 helloWorld() {
		return "Hello World Spring Boot Wonderful team";
	}
}
