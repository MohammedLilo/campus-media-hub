package com.lilo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lilo.service.UserService;

@SpringBootApplication
public class ELearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningPlatformApplication.class, args);

	}

	@Autowired
	UserService userService;

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {
		};

	}

}
