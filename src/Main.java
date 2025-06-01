import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que ejecuta el programa del sistema bancario.
 * Permite la autenticación de clientes, selección y gestión de diferentes tipos de cuentas bancarias.
 */
public class Main {

    /**
     * Objeto Scanner para leer entradas del usuario.
     */
    private static final Scanner entrada = new Scanner(System.in);

    /**
     * Método principal que inicia la ejecución del programa.
     * Carga los clientes desde un archivo, valida sesión de usuario y muestra el menú principal.
     *
     * @param args Argumentos de línea de comando (no usados).
     */
    public static void main(String[] args) {
        List<Cliente> clientes = LectorDatos.cargarClientesDesdeArchivo("datos.txt");

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
            mostrarMenuPrincipal(clienteActual, clientes);
            EscritorDatos.guardarClientesEnArchivo("datos.txt", clientes);
        } else {
            System.out.println("Has alcanzado el número máximo de intentos.");
        }
    }

    /**
     * Muestra el menú principal para que el cliente seleccione una cuenta y acceda a sus opciones.
     *
     * @param clienteActual Cliente que ha iniciado sesión exitosamente.
     * @param clientes Lista completa de clientes para operaciones como transferencias.
     */
    private static void mostrarMenuPrincipal(Cliente clienteActual, List<Cliente> clientes) {
        System.out.println("Bienvenido " + clienteActual.getNombreC() + "!");

        int opcion;

        do {
            System.out.println("Seleccione una cuenta:");

            clienteActual.mostrarCuentas();
            System.out.println((clienteActual.getCuentas().size() + 1) + ". Salir");
            System.out.print("Opción: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
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
                        menuNomina((Nomina) cuentaSeleccionada, clientes);
                        break;
                    case CREDITO:
                        menuCredito((Credito) cuentaSeleccionada);
                        break;
                    default:
                        System.out.println("Tipo de cuenta no reconocido.");
                }
            } else if (opcion == clienteActual.getCuentas().size() + 1) {
                System.out.println("Cerrando sesión y saliendo del sistema...");
                break;
            } else {
                System.out.println("Opción inválida.");
            }
        } while (true);
    }

    /**
     * Muestra el menú de operaciones disponibles para una cuenta de tipo Inversión.
     *
     * @param cuenta Cuenta de tipo Inversion a operar.
     */
    private static void menuInversion(Inversion cuenta) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ INVERSIÓN ---");
            System.out.println("1. Consultar saldo actual");
            System.out.println("2. Mostrar saldo inicial");
            System.out.println("3. Mostrar rendimiento mensual (%)");
            System.out.println("4. Mostrar meses invertidos");
            System.out.println("5. Calcular y mostrar ganancia estimada");
            System.out.println("6. Mostrar todos los datos de la cuenta");
            System.out.println("7. Regresar");
            System.out.print("Seleccione una opción: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                entrada.next();
            }

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    cuenta.consultarSaldo();
                    break;
                case 2:
                    System.out.printf("Saldo inicial: $%.2f\n", cuenta.getSaldoInicial());
                    break;
                case 3:
                    System.out.printf("Rendimiento mensual: %.2f%%\n", cuenta.getRendimientoMensual());
                    break;
                case 4:
                    System.out.println("Meses invertidos: " + cuenta.getMesesInvertidos());
                    break;
                case 5:
                    cuenta.mostrarGanancia();
                    break;
                case 6:
                    cuenta.mostrarCuenta();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    /**
     * Muestra el menú de operaciones para una cuenta de nómina.
     * Permite consultar saldo, mostrar salario, retiros, depósitos, transferencias y movimientos.
     *
     * @param cuenta Cuenta de tipo Nomina.
     * @param clientes Lista de clientes para buscar cuentas destino en transferencias.
     */
    private static void menuNomina(Nomina cuenta, List<Cliente> clientes) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ NÓMINA ---");
            System.out.println("1. Consultar saldo actual");
            System.out.println("2. Mostrar salario");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Depositar dinero");
            System.out.println("5. Realizar transferencia");
            System.out.println("6. Mostrar movimientos");
            System.out.println("7. Regresar");
            System.out.print("Seleccione una opción: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Ingrese un número válido.");
                entrada.next();
            }

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
                    cuenta.depositar();
                    break;
                case 5:
                    System.out.print("Ingrese el número de cuenta destino: ");
                    String numeroDestino = entrada.nextLine();

                    System.out.print("Ingrese el monto a transferir: ");
                    double monto = entrada.nextDouble();
                    entrada.nextLine();

                    Cuenta cuentaDestino = Movimientos.buscarCuenta(numeroDestino, clientes);

                    if (cuentaDestino != null) {
                        System.out.print("Ingrese el concepto de la transferencia: ");
                        String concepto = entrada.nextLine();
                        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        Movimientos movimiento = new Movimientos(
                                Movimientos.TipoOperacion.TRANSFERIR,
                                monto,
                                fechaHora,
                                cuenta,
                                cuentaDestino,
                                concepto
                        );

                        movimiento.transferir();
                    } else {
                        System.out.println("Cuenta destino no encontrada.");
                    }
                    break;
                case 6:
                    Movimientos verMovimientos = new Movimientos();
                    verMovimientos.generarHistorial(cuenta);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    /**
     * Muestra el menú de operaciones para una cuenta de crédito.
     * Permite consultar saldo, mostrar límite de crédito y mostrar datos completos.
     *
     * @param cuenta Cuenta de tipo Credito.
     */
    private static void menuCredito(Credito cuenta) {
        int opcion;
        do {
            System.out.println("--- MENÚ CRÉDITO ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Mostrar límite de crédito");
            System.out.println("3. Mostrar datos completos");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");
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
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }
}
