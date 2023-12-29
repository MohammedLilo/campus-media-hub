package com.lilo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ELearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningPlatformApplication.class, args);

	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return runner -> {
		};

	}

}
