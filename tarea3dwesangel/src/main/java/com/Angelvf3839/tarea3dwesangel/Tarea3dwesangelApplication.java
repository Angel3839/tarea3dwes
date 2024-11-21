package com.Angelvf3839.tarea3dwesangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tarea3dwesangelApplication {

	@Bean
	public Principal applicactionStartupRunner(){
		return new Principal();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Tarea3dwesangelApplication.class, args);
	}

}
