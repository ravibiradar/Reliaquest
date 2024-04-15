package com.example.rqchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.rqchallenge.employees","com.example.rqchallenge.service"})
public class RqchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RqchallengeApplication.class, args);
	}

}
