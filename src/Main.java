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
            System.out.println("Has alcanzado el numero maximo de intentos.");
        }
    }

    private static void mostrarMenuPrincipal(Cliente clienteActual) {
        System.out.println("Bienvenido " + clienteActual.getNombreC() + "!");

        int opcion;

        do {
            System.out.println("Seleccione una cuenta:");

            for (int i = 0; i < clienteActual.getCuentas().size(); i++) {
                Cuenta cuenta = clienteActual.getCuentas().get(i);
            }

            clienteActual.mostrarCuentas();
            System.out.println("4. Salir");
            System.out.print("Opcion: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un numero valido.");
                entrada.next();
            }

            opcion = entrada.nextInt();
            entrada.nextLine();

            if (opcion > 0 && opcion <= clienteActual.getCuentas().size()) {
                Cuenta cuentaSeleccionada = clienteActual.getCuentas().get(opcion - 1);

                switch (cuentaSeleccionada.tipoCuenta) {
                    case INVERSION:
                        menuInversion((Inversion) cuentaSeleccionada);
                        break;
                    case NOMINA:
                        menuNomina((Nomina) cuentaSeleccionada);
                        break;
                    case CREDITO:
                        menuCredito((Credito) cuentaSeleccionada);
                        break;
                    default:
                        System.out.println("Tipo de cuenta no reconocido.");
                }
            } else if (opcion == clienteActual.getCuentas().size() + 1) {
                System.out.println("Cerrando sesion y saliendo del sistema...");
                break;
            } else {
                System.out.println("Opcion invalida.");
            }
        } while (true);
    }

    private static void menuInversion(Inversion cuenta) {
        int opcion;
        do {
            System.out.println("--- MENU INVERSION ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Mostrar ganancia estimada");
            System.out.println("3. Mostrar datos completos de la cuenta");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    cuenta.consultarSaldo();
                    break;
                case 2:
                    cuenta.mostrarGanancia();
                    break;
                case 3:
                    cuenta.mostrarCuenta();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    private static void menuNomina(Nomina cuenta) {
        int opcion;
        do {
            System.out.println("--- MENU NOMINA ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Mostrar salario");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Mostrar datos completos");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    cuenta.consultarSaldo();
                    break;
                case 2:
                    cuenta.mostrarSalario();
                    break;
                case 3:
                    cuenta.retirar();
                    break;
                case 4:
                    cuenta.mostrarCuenta();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
    }

    private static void menuCredito(Credito cuenta) {
        int opcion;
        do {
            System.out.println("--- MENU CREDITO ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Mostrar limite de credito");
            System.out.println("3. Mostrar datos completos");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opcion: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    cuenta.consultarSaldo();
                    break;
                case 2:
                    cuenta.mostrarLimite();
                    break;
                case 3:
                    cuenta.mostrarCuenta();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }
}