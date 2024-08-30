package com.xib.assessment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(LoadTestData loadTestData) {
		return args -> {
			loadTestData.execute();
		};
	}
}
