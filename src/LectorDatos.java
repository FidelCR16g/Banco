import java.io.*;
import java.util.*;

public class LectorDatos {

    public static List<Cliente> cargarClientesDesdeArchivo(String ruta) {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            Cliente clienteActual = null;

            while ((linea = br.readLine()) != null) {
                if (linea.equals("CLIENTE")) {
                    String[] datos = br.readLine().split("\\|");
                    clienteActual = new Cliente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                    clientes.add(clienteActual);

                } else if (linea.equals("CUENTA")) {
                    String[] datos = br.readLine().split("\\|");
                    String tipo = datos[1];
                    Cuenta cuenta = null;

                    switch (tipo) {
                        case "INVERSION":
                            cuenta = new Inversion(datos[0], Double.parseDouble(datos[2]), Integer.parseInt(datos[3]),
                                    Double.parseDouble(datos[4]), Integer.parseInt(datos[5]), clienteActual);
                            break;
                        case "NOMINA":
                            cuenta = new Nomina(datos[0], Double.parseDouble(datos[2]), Integer.parseInt(datos[3]),
                                    datos[4], datos[5], Double.parseDouble(datos[6]), clienteActual
                            );
                            break;
                        case "CREDITO":
                            cuenta = new Credito(datos[0], Double.parseDouble(datos[2]),
                                    Integer.parseInt(datos[3]), Double.parseDouble(datos[4]), clienteActual);
                            break;
                    }

                    if (cuenta != null) {
                        clienteActual.asignarCuenta(cuenta);
                    }

                } else if (linea.equals("MOVIMIENTO")) {
                    String[] datos = br.readLine().split("\\|");

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
                        movimientos.transferir();
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
