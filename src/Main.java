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
    Objeto Scanner para leer entradas del usuario.
     */

    private static final Scanner entrada = new Scanner(System.in);
    private static final String ARCHIVO_DATOS = "resources/datos.txt";
    private static List<Cliente> clientes;

    /**
     * Metodo principal que inicia la ejecución del programa.
     * Carga los clientes desde un archivo, valida sesión de usuario y muestra el menú principal.
     *
     * @param args Argumentos de línea de comando (no usados).
     */
    public static void main(String[] args) {
        clientes = LectorDatos.cargarClientesDesdeArchivo(ARCHIVO_DATOS);

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
            guardarDatos();
        } else {
            System.out.println("Has alcanzado el número máximo de intentos.");
        }
    }

    private static void guardarDatos() {
        EscritorDatos.guardarClientesEnArchivo(ARCHIVO_DATOS, clientes);
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
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ DE INVERSIÓN ===");
            System.out.println("1. Consultar saldo actual");
            System.out.println("2. Mostrar detalles de inversión");
            System.out.println("3. Realizar depósito");
            System.out.println("4. Realizar retiro");
            System.out.println("5. Calcular y mostrar ganancia estimada");
            System.out.println("6. Mostrar todos los movimientos");
            System.out.println("7. Mostrar todos los datos de la cuenta");
            System.out.println("8. Regresar");
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
                    cuenta.mostrarInversion();
                    break;
                case 3:
                    if (cuenta.nipValido()) {
                        System.out.print("Ingresa el monto a depositar: ");
                        double monto = entrada.nextDouble();
                        cuenta.depositar(monto);
                        guardarDatos();
                    } else {
                        System.out.println("Acceso denegado. NIP incorrecto.");
                    }
                    break;
                case 4:
                    if (cuenta.nipValido()) {
                        System.out.print("Ingresa el monto a retirar: ");
                        double monto = entrada.nextDouble();
                        cuenta.retirar(monto);
                        guardarDatos();
                    } else {
                        System.out.println("Acceso denegado. NIP incorrecto.");
                    }
                    break;
                case 5:
                    cuenta.mostrarGanancia();
                    break;
                case 6:
                    Movimientos verMovimientos = new Movimientos();
                    verMovimientos.generarHistorial(cuenta);
                    break;
                case 7:
                    cuenta.mostrarCuenta();
                    break;
                case 8:
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 8);
    }

    /**
     * Muestra el menú de operaciones para una cuenta de nómina.
     * Permite consultar saldo, mostrar salario, retiros, depósitos, transferencias y movimientos.
     *
     * @param cuenta de tipo Nomina.
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
                    if (cuenta.nipValido()) {
                        System.out.print("Ingresa el monto a retirar: ");
                        double monto = entrada.nextDouble();
                        cuenta.retirar(monto);
                        guardarDatos();
                    } else {
                        System.out.println("Acceso denegado. NIP incorrecto.");
                    }
                    break;
                case 4:
                    if (cuenta.nipValido()) {
                        System.out.print("Ingresa el monto a depositar: ");
                        double monto = entrada.nextDouble();
                        cuenta.depositar(monto);
                        guardarDatos();
                    } else {
                        System.out.println("Acceso denegado. NIP incorrecto.");
                    }
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
                    guardarDatos();
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
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ CREDITO ===");
            System.out.println("1. Consultar saldo actual");
            System.out.println("2. Consultar límite de crédito");
            System.out.println("3. Consultar crédito disponible");
            System.out.println("4. Realizar compra/retiro");
            System.out.println("5. Realizar pago/depósito");
            System.out.println("6. Mostrar historial de movimientos");
            System.out.println("7. Mostrar datos completos de la cuenta");
            System.out.println("8. Regresar");
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
                    cuenta.mostrarLimite();
                    break;
                case 3:
                    System.out.println("Crédito disponible: $" + (cuenta.getLimiteEstablecido() - cuenta.getSaldo()));
                    break;
                case 4:
                    if (cuenta.nipValido()) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Ingresa el monto a retirar: ");
                        double monto = scanner.nextDouble();
                        cuenta.retirar(monto);
                        guardarDatos();
                    } else {
                        System.out.println("Acceso denegado. NIP incorrecto.");
                    }
                    break;
                case 5:
                    if (cuenta.nipValido()) {
                        System.out.print("Ingresa el monto a depositar: ");
                        double monto = entrada.nextDouble();
                        cuenta.depositar(monto);
                        guardarDatos();
                    } else {
                        System.out.println("Acceso denegado. NIP incorrecto.");
                    }
                    break;
                case 6:
                    Movimientos verMovimientos = new Movimientos();
                    verMovimientos.generarHistorial(cuenta);
                    break;
                case 7:
                    cuenta.mostrarCuenta();
                    break;
                case 8:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 8);
    }
}