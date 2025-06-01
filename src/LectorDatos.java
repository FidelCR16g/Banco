import java.io.*;
import java.util.*;

public class LectorDatos {

    /**
     * Carga clientes, cuentas y movimientos desde un archivo de texto.
     *
     * @param ruta Ruta del archivo con los datos.
     * @return Lista de clientes con sus cuentas y movimientos.
     */
    public static List<Cliente> cargarClientesDesdeArchivo(String ruta) {
        List<Cliente> clientes = new ArrayList<>();
        List<String[]> movimientosTemporales = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            Cliente clienteActual = null;

            while ((linea = br.readLine()) != null) {
                switch (linea) {
                    case "CLIENTE":
                        String[] datosCliente = br.readLine().split("\\|");
                        if (datosCliente.length < 6) {
                            System.out.println("Datos de cliente incompletos: " + Arrays.toString(datosCliente));
                            continue;
                        }
                        clienteActual = new Cliente(datosCliente[0], datosCliente[1], datosCliente[2],
                                datosCliente[3], datosCliente[4], datosCliente[5]);
                        clientes.add(clienteActual);
                        break;

                    case "CUENTA":
                        String[] datosCuenta = br.readLine().split("\\|");
                        Cuenta cuenta = null;
                        String tipo = datosCuenta[1];

                        try {
                            switch (tipo) {
                                case "INVERSION":
                                    cuenta = new Inversion(datosCuenta[0], Double.parseDouble(datosCuenta[2]),
                                            Integer.parseInt(datosCuenta[3]),
                                            Double.parseDouble(datosCuenta[4]),
                                            Integer.parseInt(datosCuenta[5]),
                                            clienteActual);
                                    break;
                                case "NOMINA":
                                    cuenta = new Nomina(datosCuenta[0], Double.parseDouble(datosCuenta[2]),
                                            Integer.parseInt(datosCuenta[3]), datosCuenta[4],
                                            datosCuenta[5], Double.parseDouble(datosCuenta[6]),
                                            clienteActual);
                                    break;
                                case "CREDITO":
                                    cuenta = new Credito(datosCuenta[0], Double.parseDouble(datosCuenta[2]),
                                            Integer.parseInt(datosCuenta[3]),
                                            Double.parseDouble(datosCuenta[4]),
                                            clienteActual);
                                    break;
                                default:
                                    System.out.println("Tipo de cuenta no reconocido: " + tipo);
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error en formato numérico en cuenta: " + e.getMessage());
                        }

                        if (cuenta != null) {
                            clienteActual.asignarCuenta(cuenta);
                        }
                        break;

                    case "MOVIMIENTO":
                        String[] datosMovimiento = br.readLine().split("\\|");
                        movimientosTemporales.add(datosMovimiento);
                        break;

                    default:
                        break;
                }
            }

            // Procesar movimientos después para tener todas las cuentas cargadas
            for (String[] datos : movimientosTemporales) {
                try {
                    Movimientos.TipoOperacion tipoOp = Movimientos.TipoOperacion.valueOf(datos[0]);
                    double monto = Double.parseDouble(datos[1]);
                    String fecha = datos[2];
                    String numCuentaOrigen = datos[3];
                    String numCuentaDestino = datos[4];
                    String concepto = datos[5];

                    Cuenta cuentaOrigen = buscarCuenta(clientes, numCuentaOrigen);
                    Cuenta cuentaDestino = numCuentaDestino.isEmpty() ? null : buscarCuenta(clientes, numCuentaDestino);

                    Movimientos movimientos = new Movimientos(tipoOp, monto, fecha, cuentaOrigen, cuentaDestino, concepto);

                    if (tipoOp == Movimientos.TipoOperacion.TRANSFERIR) {
                        if (cuentaOrigen != null && cuentaDestino != null) {
                            if (cuentaOrigen.disminuirSaldo(monto)) {
                                cuentaDestino.aumentarSaldo(monto);
                                cuentaOrigen.getMovimientos().add(movimientos);
                                cuentaDestino.getMovimientos().add(movimientos);
                                movimientos.procesarTicket();
                            } else {
                                System.out.println("Saldo insuficiente para transferir de " + cuentaOrigen.getNumeroCuenta());
                            }
                        } else {
                            System.out.println("Cuenta origen o destino no encontrada para transferencia.");
                        }
                    } else {
                        if (cuentaOrigen != null) {
                            if (tipoOp == Movimientos.TipoOperacion.DEPOSITAR) {
                                cuentaOrigen.aumentarSaldo(monto);
                            } else if (tipoOp == Movimientos.TipoOperacion.RETIRAR) {
                                if (!cuentaOrigen.disminuirSaldo(monto)) {
                                    System.out.println("Saldo insuficiente para retirar.");
                                    continue;
                                }
                            }
                            cuentaOrigen.getMovimientos().add(movimientos);
                            movimientos.procesarTicket();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error procesando movimiento: " + Arrays.toString(datos) + " - " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }

        return clientes;
    }

    private static Cuenta buscarCuenta(List<Cliente> clientes, String numeroCuenta) {
        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return cuenta;
                }
            }
        }
        return null;
    }
}
