package com.Angelvf3839.tarea3dwesangel.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Angelvf3839.tarea3dwesangel.repositorios.MensajeRepository;

	@Service
	public class ServiciosMensaje {
		@Autowired
		MensajeRepository mensajeRepo;
		
		
		/* Método para ver validar un mensaje */
		
		public boolean validarMensaje(String mensaje) {
			if (mensaje == null || mensaje.trim().isEmpty()) {
				System.out.println("El mensaje está vacio.");
				return false;
			}
			if (mensaje.length() > 500) {
				System.out.println("El mensaje es muy largo");
				return false;
			}
			return true;
		}
}