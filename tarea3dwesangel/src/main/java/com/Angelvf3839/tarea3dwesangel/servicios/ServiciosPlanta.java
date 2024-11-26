package com.Angelvf3839.tarea3dwesangel.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angelvf3839.tarea3dwesangel.modelo.Planta;
import com.Angelvf3839.tarea3dwesangel.repositorios.PlantaRepository;

@Service
public class ServiciosPlanta {

	@Autowired
	PlantaRepository plantarepo;
	
	@Autowired
	ServiciosEjemplar servejemplar;
	
	public boolean validarPlanta(Planta p) {
		return true;
	}
	
	public void insertarPlanta(Planta p) {
		plantarepo.saveAndFlush(p);

	}
}
