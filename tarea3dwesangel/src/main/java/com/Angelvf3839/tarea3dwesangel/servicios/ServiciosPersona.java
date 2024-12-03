package com.Angelvf3839.tarea3dwesangel.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angelvf3839.tarea3dwesangel.modelo.Persona;
import com.Angelvf3839.tarea3dwesangel.repositorios.PersonaRepository;

@Service
public class ServiciosPersona {
	@Autowired
	PersonaRepository personaRepo;
	
	
	/* Método para ver validar a una persona */
	
	public boolean validarPersona(Persona pers) {
		if (pers == null) {
			return false;
		}
		if (pers.getNombre() == null || pers.getNombre().isEmpty()) {
			return false;
		}
		if (pers.getNombre().length() < 3 || pers.getNombre().length() > 20) {
			return false;
		}
		if (pers.getEmail() == null || pers.getEmail().isEmpty()) {
			return false;
		}
		if (pers.getEmail().length() < 5 || !pers.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
				|| pers.getEmail().length() > 40) {
			return false;
		}
		if (pers.getNombre().length() < 3 || pers.getNombre().length() > 40)
			return false;

		return true;
	}
	
}