package vista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.Angelvf3839.tarea3dwesangel.modelo.Credenciales;
import com.Angelvf3839.tarea3dwesangel.modelo.Ejemplar;
import com.Angelvf3839.tarea3dwesangel.modelo.Mensaje;
import com.Angelvf3839.tarea3dwesangel.modelo.Persona;
import com.Angelvf3839.tarea3dwesangel.modelo.Planta;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosCredenciales;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosEjemplar;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosMensaje;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosPersona;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosPlanta;
import com.Angelvf3839.tarea3dwesangel.servicios.Controlador;

@Component
public class FachadaAdmin {

    @Autowired
    private ServiciosPlanta servPlanta;

    @Autowired
    private ServiciosEjemplar servEjemplar;

    @Autowired
    private ServiciosMensaje servMensaje;

    @Autowired
    private ServiciosPersona servPersona;

    @Autowired
    private ServiciosCredenciales servCredenciales;

    @Autowired
    @Lazy
    private Controlador controlador;

    @Autowired
    @Lazy
    private FachadaInvitado fachadaInvitado;

    @Autowired
    @Lazy
    private FachadaPersonal fachadaPersonal;

    private Scanner in = new Scanner(System.in);

    public void menuAdmin() {
        int opcion = 0;
        do {
            System.out.println("------MENÚ DE ADMINISTRADOR------");
            System.out.println(" ");
            System.out.println("Selecciona una opción:");
            System.out.println("1. GESTIÓN DE PLANTAS");
            System.out.println("2. GESTIÓN DE EJEMPLARES");
            System.out.println("3. GESTIÓN DE MENSAJES");
            System.out.println("4. GESTIÓN DE PERSONAS");
            System.out.println("5. CERRAR SESIÓN.");
            System.out.println(" ");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción incorrecta");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        menuAdminPlantas();
                        break;
                    case 2:
                        menuAdminEjemplares();
                        break;
                    case 3:
                        menuAdminMensajes();
                        break;
                    case 4:
                        menuAdminPersonas();
                        break;
                    case 5:
                        controlador.cerrarSesion();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 5);
    }

    public void menuAdminPlantas() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Ver plantas");
            System.out.println("2. Crear nueva planta");
            System.out.println("3. Modificar datos de una planta");
            System.out.println("4. Volver al menú principal");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opción incorrecta");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        fachadaInvitado.verTodasPlantas();
                        break;
                    case 2:
                        nuevaPlanta();
                        break;
                    case 3:
                        menuAdminModificarPlantas();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un númer.");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 4);
    }

    public void menuAdminModificarPlantas() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Modificar nombre común");
            System.out.println("2. Modificar nombre científico");
            System.out.println("3. Volver al menú de plantas");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción incorrecta");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        modificarNombreComun();
                        break;
                    case 2:
                        modificarNombreCientifico();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);
    }

    public void menuAdminEjemplares() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Registrar nuevo ejemplar");
            System.out.println("2. Filtrar ejemplares por tipo de planta");
            System.out.println("3. Ver mensajes de un ejemplar");
            System.out.println("4. Borrar un ejemplar");
            System.out.println("5. Volver al menú principal");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción incorrecta");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        nuevoEjemplar();
                        break;
                    case 2:
                        fachadaPersonal.filtrarEjemplaresPorCodigoPlanta();
                        break;
                    case 3:
                        verMensajesEjemplar();
                        break;
                    case 4:
                    	fachadaPersonal.borrarEjemplar();
                    	break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 5);
    }
    public void menuAdminPersonas() {
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción:");
			System.out.println(" ");
			System.out.println("1. Registrar nueva persona");
			System.out.println("2. Ver todas las personas");
			System.out.println("3. Borrar una persona");
			System.out.println("4. Volver al menú principal");
			try {
				opcion = in.nextInt();
				if (opcion < 1 || opcion > 4) {
					System.out.println("Opción incorrecta");
					continue;
				}
				switch (opcion) {
				case 1:
					nuevaPersona();
					break;
				case 2:
					verTodasPersonas();
					break;
				case 3:
					borrarPersona();
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes ingresar un número");
				in.nextLine();
				opcion = 0;
			}
		} while (opcion != 4);
	}

    public void menuAdminMensajes() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Nuevo mensaje");
            System.out.println("2. Ver mensajes");
            System.out.println("3. Volver al menú principal");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción incorrecta");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        fachadaPersonal.nuevoMensaje();
                        break;
                    case 2:
                        menuAdminVerMensajes();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);
    }

    public void menuAdminVerMensajes() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Ver todos los mensajes");
            System.out.println("2. Ver mensajes por persona");
            System.out.println("3. Ver mensajes por rango de fechas");
            System.out.println("4. Ver mensajes por tipo de planta");
            System.out.println("5. Volver al menú de mensajes");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción incorrecta");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        verTodosMensajes();
                        break;
                    case 2:
                        fachadaPersonal.verMensajesPorPersona();
                        break;
                    case 3:
                        fachadaPersonal.verMensajePorFechas();
                        break;
                    case 4:
                        fachadaPersonal.verMensajePorTipoPlanta();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 5);
    }

    public void nuevaPlanta() {
        try {
            System.out.print("Introduce el código de la planta: ");
            String codigo = in.nextLine().trim().toUpperCase();

            if (!servPlanta.validarCodigo(codigo)) {
                System.out.println("El código no es válido.");
                return;
            }

            if (servPlanta.codigoExistente(codigo)) {
                System.out.println("El código ya está registrado para otra planta.");
                return;
            }

            System.out.print("Introduce el nombre común: ");
            String nombreComun = in.nextLine().trim();
            System.out.print("Introduce el nombre científico: ");
            String nombreCientifico = in.nextLine().trim();

            Planta nuevaPlanta = new Planta(codigo, nombreComun, nombreCientifico);

            if (!servPlanta.validarPlanta(nuevaPlanta)) {
                System.out.println("Los datos de la planta no son válidos.");
                return;
            }

            servPlanta.insertar(nuevaPlanta);
            System.out.println("Planta creada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear la planta: " + e.getMessage());
        }
    }

    public void nuevoEjemplar() {
        try {
            System.out.print("Introduce el código de la planta asociada al ejemplar: ");
            String codigoPlanta = in.nextLine().trim().toUpperCase();

            if (!servPlanta.codigoExistente(codigoPlanta)) {
                System.out.println("No existe una planta con ese código.");
                return;
            }

            Planta planta = servPlanta.buscarPorCodigo(codigoPlanta);
            Ejemplar nuevoEjemplar = new Ejemplar();
            nuevoEjemplar.setPlanta(planta);

            servEjemplar.insertar(nuevoEjemplar);

            String nombreFinal = planta.getCodigo() + "_" + nuevoEjemplar.getId();
            servEjemplar.cambiarNombre(nuevoEjemplar.getId(), nombreFinal);

            System.out.println("Ejemplar creado con éxito. ID: " + nuevoEjemplar.getId());
        } catch (Exception e) {
            System.out.println("Error al crear el ejemplar: " + e.getMessage());
        }
    }


    public void nuevaPersona() {
    	try {
            in.nextLine(); // Consumir el salto de línea residual del buffer

            // Solicitar y leer el nombre
            System.out.print("Introduce el nombre: ");
            String nombre = in.nextLine().trim();

            // Solicitar y leer el email
            System.out.print("Introduce el email: ");
            String email = in.nextLine().trim();
            if (servPersona.emailExistente(email)) {
                System.out.println("El email ya está registrado.");
                return;
            }

            // Solicitar y leer el nombre de usuario
            System.out.print("Introduce el nombre de usuario: ");
            String usuario = in.nextLine().trim();
            if (servCredenciales.usuarioExistente(usuario)) {
                System.out.println("El usuario ya está registrado.");
                return;
            }

            // Solicitar y leer la contraseña
            System.out.print("Introduce la contraseña: ");
            String password = in.nextLine().trim();
            if (!servCredenciales.validarContraseña(password)) {
                System.out.println("La contraseña no cumple con los requisitos de seguridad.");
                return;
            }

            // Crear la nueva persona y credenciales
            Persona nuevaPersona = new Persona(nombre, email);
            Credenciales credenciales = new Credenciales(usuario, password, nuevaPersona);
            nuevaPersona.setCredenciales(credenciales);

            // Validar y guardar la persona
            if (!servPersona.validarPersona(nuevaPersona)) {
                System.out.println("Los datos de la persona no son válidos.");
                return;
            }

            servPersona.insertar(nuevaPersona);
            System.out.println("Persona creada con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear la persona: " + e.getMessage());
        }
    }

    public void verTodasPersonas() {
        ArrayList<Persona> personas = (ArrayList<Persona>) servPersona.verTodos();
        if (personas == null || personas.isEmpty()) {
            System.out.println("No hay personas registradas en el sistema.");
            return;
        }

        System.out.println("Lista de todas las personas:");
        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }


    public void verTodosMensajes() {
        ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) servMensaje.verTodos();
        if (mensajes == null || mensajes.isEmpty()) {
            System.out.println("No hay mensajes registrados en el sistema.");
            return;
        }

        System.out.println("Lista de todos los mensajes:");
        for (Mensaje mensaje : mensajes) {
            System.out.println(mensaje);
        }
    }


	public void modificarNombreComun() {
	    try {
	        System.out.print("Introduce el código de la planta: ");
	        String codigo = in.nextLine().trim().toUpperCase();

	        if (!servPlanta.codigoExistente(codigo)) {
	            System.out.println("No existe una planta con ese código.");
	            return;
	        }

	        System.out.print("Introduce el nuevo nombre común: ");
	        String nuevoNombreComun = in.nextLine().trim();

	        if (servPlanta.actualizarNombreComun(codigo, nuevoNombreComun)) {
	            System.out.println("Nombre común actualizado con éxito.");
	        } else {
	            System.out.println("Error al actualizar el nombre común.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al modificar el nombre común: " + e.getMessage());
	    }
	}

	public void modificarNombreCientifico() {
	    try {
	        System.out.print("Introduce el código de la planta: ");
	        String codigo = in.nextLine().trim().toUpperCase();

	        if (!servPlanta.codigoExistente(codigo)) {
	            System.out.println("No existe una planta con ese código.");
	            return;
	        }

	        System.out.print("Introduce el nuevo nombre científico: ");
	        String nuevoNombreCientifico = in.nextLine().trim();

	        if (servPlanta.actualizarNombreCientifico(codigo, nuevoNombreCientifico)) {
	            System.out.println("Nombre científico actualizado con éxito.");
	        } else {
	            System.out.println("Error al actualizar el nombre científico.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al modificar el nombre científico: " + e.getMessage());
	    }
	}

	public void verMensajesEjemplar() {
		System.out.print("Introduce el id de un ejemplar para ver sus mensajes: ");
		try {
			long idEjemplar = in.nextLong();
			if (idEjemplar < 1 || idEjemplar > servEjemplar.contarEjemplares()) {
				System.out.println("Debes introducir un número entre el 1 y " + servEjemplar.contarEjemplares());
				return;
			}
			ArrayList<Mensaje> mensajes = servMensaje.verMensajesPorEjemplar(idEjemplar);
			if (mensajes.isEmpty()) {
				System.out.println("No se encontraron mensajes para el ejemplar");
			} else {
				System.out.println("Mensajes del ejemplar con ID: " + idEjemplar + ":");
				System.out.println();
				for (Mensaje m : mensajes) {
					System.out.println(m);
					System.out.println();
				}
			}
		} catch (Exception e) {
			System.out.println("Error al intentar obtener los mensajes del ejemplar: " + e.getMessage());
		}
	}
	
	public void borrarPersona() {
	    try {
	        System.out.print("Introduce el ID de la persona a borrar: ");
	        long idPersona = in.nextLong();

	        if (servPersona.eliminarPersona(idPersona)) {
	            System.out.println("Persona eliminada con éxito.");
	        } else {
	            System.out.println("No se encontró una persona con ese ID.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error al borrar la persona: " + e.getMessage());
	    }
	}

}