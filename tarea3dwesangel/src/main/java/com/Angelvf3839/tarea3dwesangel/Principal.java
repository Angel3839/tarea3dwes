package com.Angelvf3839.tarea3dwesangel;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.Angelvf3839.tarea3dwesangel.modelo.Planta;
import com.Angelvf3839.tarea3dwesangel.repositorios.PlantaRepository;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosEjemplar;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosPlanta;

import ch.qos.logback.classic.Logger;

public class Principal implements CommandLineRunner{

	
	@Autowired
	ServiciosPlanta servplant;
	
	@Autowired
	ServiciosEjemplar servejemplar;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("INI");
		
		Planta p = new Planta();
		servplant.validarPlanta(p);
		
		
		System.out.println("---------------------");
		
		
		System.out.println("FIN");
		
	}

}
