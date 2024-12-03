package com.Angelvf3839.tarea3dwesangel.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angelvf3839.tarea3dwesangel.modelo.Ejemplar;
import com.Angelvf3839.tarea3dwesangel.repositorios.EjemplarRepository;
import com.Angelvf3839.tarea3dwesangel.repositorios.PlantaRepository;

@Service
public class ServiciosEjemplar {
	@Autowired
	PlantaRepository plantaRepo;
	@Autowired 
	EjemplarRepository ejemplarRepo;
	

	/* Método para insertar un Ejemplar */
	
	public void insertarEjemplar(Ejemplar ejemplar) {
        ejemplarRepo.save(ejemplar);
    }
	
	
}