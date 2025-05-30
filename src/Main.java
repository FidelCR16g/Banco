import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        List<Cliente> clientes = LectorDatos.cargarClientesDesdeArchivo("C:\\Users\\fave6\\OneDrive\\Documentos\\Fidel Escuela\\Codigos POO\\Banco\\src\\datos.txt");

        int intentosRestantes = 4;
        boolean sesionExitosa = false;
        Cliente clienteActual = null;

        while (intentosRestantes > 0 && !sesionExitosa) {
            System.out.print("Usuario: ");
            String usuario = entrada.nextLine();

            System.out.print("Contraseña: ");
            String contrasenia = entrada.nextLine();

            for (Cliente cliente : clientes) {
                if (cliente.sesionValida(usuario, contrasenia)) {
                    clienteActual = cliente;
                    sesionExitosa = true;
                    break;
                }
            }

            if (!sesionExitosa) {
                intentosRestantes--;
                System.out.println("Datos incorrectos. Intentos restantes: " + intentosRestantes + "\n");
            }
        }

        if (sesionExitosa) {
            mostrarMenuPrincipal(clienteActual);
        } else {
            System.out.println("Has alcanzado el número máximo de intentos.");
        }
    }

    private static void mostrarMenuPrincipal(Cliente cliente) {
        System.out.println("Bienvenido " + cliente.getNombreC() + "!");

        String eleccion;

        do {
            System.out.println("--- Menú de Cuentas Activas ---");
            cliente.mostrarCuentas();
            System.out.println("4. Salir");
            System.out.print("Seleccione su cuenta: ");
            eleccion = entrada.next();

            switch (eleccion) {
                case "1":
                case "Inversion":
                    menuInversion(cliente);
                    break;
                case "2":
                case "Nomina":
                    menuNomina(cliente);
                    break;
                case "3":
                case "Credito":
                    menuCredito(cliente);
                    break;
                case "4":
                case "Salir":
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (!eleccion.equalsIgnoreCase("4") && !eleccion.equalsIgnoreCase("Salir"));
    }

    private static void menuInversion(Cliente cliente) {
        System.out.println("--- Menú Inversión ---");

    }

    private static void menuNomina(Cliente cliente) {
        System.out.println("--- Menú Nómina ---");
    }

    private static void menuCredito(Cliente cliente) {
        System.out.println("--- Menú Crédito ---");
    }
}
