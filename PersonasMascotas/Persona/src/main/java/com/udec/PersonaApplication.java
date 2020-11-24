package com.udec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PersonaApplication.class, args);
	}

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		return application.sources(PersonaApplication.class);
	}
}
