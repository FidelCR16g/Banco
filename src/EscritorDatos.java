import java.io.*;
import java.util.List;

public class EscritorDatos {

    public static void guardarClientesEnArchivo(String ruta, List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Cliente cliente : clientes) {
                // Escribir datos del cliente
                bw.write("CLIENTE");
                bw.newLine();
                bw.write(String.join("|", cliente.getNombreC(),
                        cliente.getCorreoC(), cliente.getTelefonoC(), cliente.getUsuario(), cliente.getContrasenia()));
                bw.newLine();

                // Escribir cuentas del cliente
                for (Cuenta cuenta : cliente.getCuentas()) {
                    bw.write("CUENTA");
                    bw.newLine();

                    if (cuenta instanceof Inversion) {
                        Inversion inversion = (Inversion) cuenta;
                        bw.write(String.join("|", inversion.getNumeroCuenta(), "INVERSION",
                                String.valueOf(inversion.getSaldo()), String.valueOf(inversion.getNip()),
                                String.valueOf(inversion.getInteres()), String.valueOf(inversion.getPlazo())));
                        bw.newLine();

                    } else if (cuenta instanceof Nomina) {
                        Nomina nom = (Nomina) cuenta;
                        bw.write(String.join("|", nom.getNumeroCuenta(), "NOMINA",
                                String.valueOf(nom.getSaldo()), String.valueOf(nom.getNip()),
                                nom.getEmpresa(), nom.getFechaDeposito(), String.valueOf(nom.getMontoDeposito())));
                        bw.newLine();

                    } else if (cuenta instanceof Credito) {
                        Credito cre = (Credito) cuenta;
                        bw.write(String.join("|", cre.getNumeroCuenta(), "CREDITO",
                                String.valueOf(cre.getSaldo()), String.valueOf(cre.getNip()),
                                String.valueOf(cre.getLimiteCredito())));
                        bw.newLine();
                    }

                    // Escribir movimientos de la cuenta
                    for (Movimientos mov : cuenta.getMovimientos()) {
                        bw.write("MOVIMIENTO");
                        bw.newLine();
                        bw.write(String.join("|",
                                mov.getTipo().name(),
                                String.valueOf(mov.getMonto()),
                                mov.getFecha(),
                                mov.getCuentaOrigen() != null ? mov.getCuentaOrigen().getNumeroCuenta() : "",
                                mov.getCuentaDestino() != null ? mov.getCuentaDestino().getNumeroCuenta() : "",
                                mov.getConcepto()));
                        bw.newLine();
                    }
                }
            }
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}