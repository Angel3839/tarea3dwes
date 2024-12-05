package vista;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Angelvf3839.tarea3dwesangel.modelo.Ejemplar;
import com.Angelvf3839.tarea3dwesangel.modelo.Mensaje;
import com.Angelvf3839.tarea3dwesangel.modelo.Persona;
import com.Angelvf3839.tarea3dwesangel.servicios.Controlador;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosEjemplar;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosMensaje;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosPersona;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosPlanta;

@Component
public class FachadaPersonal {

    @Autowired
    private Controlador controlador;

    @Autowired
    private ServiciosEjemplar serviciosEjemplar;

    @Autowired
    private ServiciosMensaje serviciosMensaje;

    @Autowired
    private ServiciosPersona serviciosPersona;

    @Autowired
    private ServiciosPlanta serviciosPlanta;

    @Autowired
    private FachadaAdmin fachadaAdmin;

    @Autowired
    private FachadaInvitado fachadaInvitado;

    private Scanner in = new Scanner(System.in);

    public void menuPersonal() {
        int opcion = 0;
        do {
            System.out.println("------MENÚ DEL PERSONAL------");
            System.out.println(" ");
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. VER TODAS LAS PLANTAS.");
            System.out.println("2. Gestión de ejemplares.");
            System.out.println("3. Gestión de mensajes.");
            System.out.println("4. CERRAR SESIÓN.");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opción incorrecta.");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        fachadaInvitado.verTodasPlantas();
                        break;
                    case 2:
                        menuPersonalEjemplares();
                        break;
                    case 3:
                        menuPersonalMensajes();
                        break;
                    case 4:
                        controlador.cerrarSesion();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número.");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 4);
    }

    public void menuPersonalEjemplares() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Registrar nuevo ejemplar.");
            System.out.println("2. Filtrar ejemplares por tipo de planta.");
            System.out.println("3. Ver mensajes de un ejemplar.");
            System.out.println("4. Volver al menú principal.");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opción incorrecta.");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        fachadaAdmin.nuevoEjemplar();
                        break;
                    case 2:
                        filtrarEjemplaresPorCodigoPlanta();
                        break;
                    case 3:
                        fachadaAdmin.verMensajesEjemplar();
                        break;
                    case 4:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número.");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 4);
    }

    public void menuPersonalMensajes() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println(" ");
            System.out.println("1. Nuevo mensaje.");
            System.out.println("2. Ver todos los mensajes.");
            System.out.println("3. Ver mensajes por persona.");
            System.out.println("4. Ver mensajes por rango de fechas.");
            System.out.println("5. Ver mensajes por tipo de planta.");
            System.out.println("6. Volver al menú principal.");
            try {
                opcion = in.nextInt();
                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opción incorrecta.");
                    continue;
                }
                switch (opcion) {
                    case 1:
                        nuevoMensaje();
                        break;
                    case 2:
                        fachadaAdmin.verTodosMensajes();
                        break;
                    case 3:
                        verMensajesPersona();
                        break;
                    case 4:
                        verMensajeFechas();
                        break;
                    case 5:
                        verMensajeTipoPlanta();
                        break;
                    case 6:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número.");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 6);
    }

    public void verTodosEjemplares() {
        ArrayList<Ejemplar> ejemplares = (ArrayList<Ejemplar>) serviciosEjemplar.verTodos();
        if (ejemplares == null || ejemplares.isEmpty()) {
            System.out.println("Lo siento, no hay ejemplares para mostrar en la base de datos.");
            return;
        }
        System.out.println("Todos los ejemplares: ");
        for (Ejemplar e : ejemplares) {
            System.out.println(e);
        }
    }

    public void nuevoMensaje() {
        int idEjemplar = 0;
        boolean correcto = false;
        do {
            try {
                verTodosEjemplares();
                System.out.println();
                System.out.println("Introduce el id del ejemplar para ponerle un mensaje: ");
                idEjemplar = in.nextInt();
                in.nextLine();
                Ejemplar ejemplar = serviciosEjemplar.buscarPorId((long) idEjemplar);
                if (ejemplar == null) {
                    System.out.println("No existe un ejemplar con el ID proporcionado.");
                } else {
                    System.out.println("Introduce el mensaje: ");
                    String mensajeTexto = in.nextLine();
                    if (mensajeTexto.trim().isEmpty()) {
                        System.out.println("El mensaje no puede estar vacío.");
                    } else {
                        Persona persona = serviciosPersona.buscarPorNombre(controlador.getUsuarioAutenticado());
                        Mensaje nuevoMensaje = new Mensaje(LocalDateTime.now(), mensajeTexto, persona, ejemplar);
                        if (serviciosMensaje.insertar(nuevoMensaje) != null) {
                            System.out.println("Mensaje añadido.");
                            correcto = true;
                        } else {
                            System.out.println("No se ha podido añadir el mensaje.");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número válido.");
                in.nextLine();
            }
        } while (!correcto);
    }

    public void filtrarEjemplaresPorCodigoPlanta() {
        try {
            System.out.print("Introduce el código de la planta para ver los ejemplares: ");
            String codigo = in.nextLine().trim().toUpperCase();
            boolean existe = serviciosPlanta.codigoPlantaExiste(codigo);
            if (existe) {
                ArrayList<Ejemplar> ejemplares = serviciosEjemplar.filtrarPorCodigoPlanta(codigo);
                if (ejemplares.isEmpty()) {
                    System.out.println("No hay ejemplares para la planta con código: " + codigo);
                } else {
                    System.out.println("Ejemplares con el código " + codigo + ":");
                    for (Ejemplar e : ejemplares) {
                        System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre());
                    }
                }
            } else {
                System.out.println("No se encontró ninguna planta con el código especificado: " + codigo);
            }
        } catch (Exception e) {
            System.out.println("Error al intentar filtrar los ejemplares: " + e.getMessage());
        }
    }

    public void verMensajesPersona() {
        System.out.print("Introduce el id de una persona para ver sus mensajes: ");
        try {
            long idPersona = in.nextLong();
            ArrayList<Mensaje> mensajes = serviciosMensaje.verMensajesPorPersona(idPersona);
            if (mensajes.isEmpty()) {
                System.out.println("No se encontraron mensajes para la persona: " + idPersona);
            } else {
                System.out.println("Mensajes:");
                for (Mensaje m : mensajes) {
                    System.out.println(m);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Debes introducir un número válido.");
            in.nextLine();
        } catch (Exception e) {
            System.out.println("Se produjo un error al intentar obtener los mensajes: " + e.getMessage());
        }
    }

    public void verMensajeTipoPlanta() {
        System.out.print("Introduce el código de una planta: ");
        String codigo = in.nextLine().trim().toUpperCase();
        try {
            ArrayList<Mensaje> mensajes = serviciosMensaje.verMensajesPorCodigoPlanta(codigo);
            if (mensajes.isEmpty()) {
                System.out.println("No se encontraron mensajes para la planta con código: " + codigo);
            } else {
                System.out.println("Mensajes para la planta con el código " + codigo + ":");
                for (Mensaje m : mensajes) {
                    System.out.println(m);
                }
            }
        } catch (Exception e) {
            System.out.println("Se produjo un error al intentar obtener los mensajes: " + e.getMessage());
        }
    }

    public void verMensajeFechas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;

        do {
            try {
                System.out.print("Introduce la primera fecha y la hora con el formato: dd-MM-yyyy HH:mm ");
                String fechaInicioIntro = in.nextLine();
                fechaInicio = LocalDateTime.parse(fechaInicioIntro, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido.");
            }
        } while (fechaInicio == null);

        do {
            try {
                System.out.print("Introduce la segunda fecha y la hora con el formato: dd-MM-yyyy HH:mm ");
                String fechaFinIntro = in.nextLine();
                fechaFin = LocalDateTime.parse(fechaFinIntro, formatter);
                if (fechaFin.isBefore(fechaInicio)) {
                    System.out.println("La fecha de fin no puede ser anterior a la fecha de inicio.");
                    fechaFin = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido.");
            }
        } while (fechaFin == null);

        ArrayList<Mensaje> mensajes = serviciosMensaje.verMensajesPorRangoFechas(fechaInicio, fechaFin);
        if (mensajes.isEmpty()) {
            System.out.println("No se encontraron mensajes en el rango de fechas proporcionado.");
        } else {
            System.out.println("Mensajes encontrados:");
            for (Mensaje m : mensajes) {
                System.out.println(m);
            }
        }
    }
}