package com.awesomepizza.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AwesomepizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwesomepizzaApplication.class, args);
	}

}
