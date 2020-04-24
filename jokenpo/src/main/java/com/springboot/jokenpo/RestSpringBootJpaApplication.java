package com.springboot.jokenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.springboot.jokenpo"})
@EnableAutoConfiguration
public class RestSpringBootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSpringBootJpaApplication.class, args);
	}

}
