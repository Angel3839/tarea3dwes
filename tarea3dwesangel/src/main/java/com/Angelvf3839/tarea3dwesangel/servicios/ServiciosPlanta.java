package com.Angelvf3839.tarea3dwesangel.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angelvf3839.tarea3dwesangel.modelo.Planta;
import com.Angelvf3839.tarea3dwesangel.repositorios.EjemplarRepository;
import com.Angelvf3839.tarea3dwesangel.repositorios.PlantaRepository;

@Service
public class ServiciosPlanta {
	@Autowired
	PlantaRepository plantaRepo;
	@Autowired
	EjemplarRepository ejemplarRepo;
	
	public void insertarPlanta(Planta p) {
		plantaRepo.save(p);
	}
	
	/* Método para ver validar una planta */
	
	public boolean validarPlanta(Planta p) {
		if (p.getCodigo().isEmpty())
			return false;
		if (p.getCodigo().length() < 3 || p.getCodigo().length() > 50)
			return false;
		if (p.getNombreCientifico().isEmpty() || p.getNombreComun().isEmpty())
			return false;
		if (!p.getCodigo().matches("^[A-Za-z0-9]+$"))
			return false;
		if (p.getNombreCientifico().length() < 3 || p.getNombreCientifico().length() > 100)
			return false;

		if (p.getNombreComun().length() < 3 || p.getNombreComun().length() > 100)
			return false;
		if (!p.getNombreCientifico().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")
				|| !p.getNombreComun().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"))
			return false;
		return true;
	}

	/* Método para validar el código de una planta */
	
	public boolean validarCodigo(String codigo) {
		if (codigo == null || codigo.isEmpty()) {
			return false;
		}
		if (!codigo.matches("^[A-Za-z0-9]+$")) {
			return false;
		}
		if (codigo.length() < 3 || codigo.length() > 50)
			return false;
		return true;
	}
}