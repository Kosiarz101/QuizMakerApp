package com.QuizMaker.QuizMakerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableWebSecurity
@EnableTransactionManagement
public class QuizMakerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMakerAppApplication.class, args);
	}

}
