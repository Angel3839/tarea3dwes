package com.Angelvf3839.tarea3dwesangel.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angelvf3839.tarea3dwesangel.repositorios.CredencialesRepository;

@Service
public class ServiciosCredenciales {
	@Autowired
	CredencialesRepository credencialesRepo;
	
	
	/* Método para validar una contraseña */
	
	public boolean validarContraseña(String contraseña) {
		if (contraseña.matches("^(?=.*[.,])[A-Za-z0-9.,]{8,}$")) {
			return true;
		}
		return false;
	}
}