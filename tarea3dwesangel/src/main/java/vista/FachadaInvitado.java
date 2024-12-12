package vista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.Angelvf3839.tarea3dwesangel.modelo.Planta;
import com.Angelvf3839.tarea3dwesangel.servicios.Controlador;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosCredenciales;
import com.Angelvf3839.tarea3dwesangel.servicios.ServiciosPlanta;

@Component
public class FachadaInvitado {

    @Autowired
    private ServiciosCredenciales servCred;

    @Autowired
    private ServiciosPlanta servPlanta;

    @Autowired
    @Lazy
    private FachadaAdmin fachadaAdmin;

    @Autowired
    @Lazy
    private FachadaPersonal fachadaPersonal;

    @Autowired
    @Lazy
    private Controlador controlador; 

    private Scanner in = new Scanner(System.in);

    public void menuInvitado() {
        int opcion = 0;
        do {
            System.out.println("------GESTIÓN DE LA FACHADA------");
            System.out.println();
            System.out.println("Seleccione una opción: ");
            System.out.println(" ");
            System.out.println("1. VER TODAS LAS PLANTAS");
            System.out.println("2. LOGIN");
            System.out.println("3. SALIR DEL PROGRAMA");
            try {
                opcion = in.nextInt();
                switch (opcion) {
                case 1:
                    verTodasPlantas();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número válido.");
                in.nextLine();
                opcion = 0;
            }
        } while (opcion != 3);
    }

    public void login() {
        in.nextLine();  
        System.out.print("Introducir nombre de usuario: ");
        String usuario = in.nextLine();
        System.out.print("Introducir contraseña: ");
        String contraseña = in.nextLine();

        try {
            boolean autenticar = servCred.autenticar(usuario, contraseña);
            if (autenticar) {
                System.out.println("Has iniciado sesión como " + usuario);
                
                
                
                
                
                controlador.setUsuarioAutenticado(usuario);

                if ("admin".equals(usuario) && "admin".equals(contraseña)) {
                    System.out.println("Eres el usuario administrador");
                    fachadaAdmin.menuAdmin();
                } else {
                    System.out.println("Eres un usuario del personal del vivero");
                    fachadaPersonal.menuPersonal();
                }
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } catch (Exception e) {
            System.out.println("No se ha podido iniciar sesión: " + e.getMessage());
        }
    }

    public void verTodasPlantas() {
        ArrayList<Planta> plantas = (ArrayList<Planta>) servPlanta.verTodas();
        if (plantas == null || plantas.isEmpty()) {
            System.out.println("Lo siento, no hay plantas para mostrar en la base de datos");
            return;
        }

        System.out.println("Todas las plantas:");
        for (Planta planta : plantas) {
            System.out.println(planta);
        }
    }

}