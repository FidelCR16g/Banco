import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorDatos {

    public static void guardarClientesEnArchivo(String ruta, List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                // Escribir datos del cliente
                bw.write("CLIENTE");
                bw.newLine();
                bw.write(String.join("|",
                        cliente.getNombreC(),
                        cliente.getIne(),
                        cliente.getTelefonoC(),
                        cliente.getCorreoC(),
                        cliente.getUsuario(),
                        cliente.getContrasenia()));
                bw.newLine();

                // Escribir cuentas del cliente
                for (Cuenta cuenta : cliente.getCuentas()) {
                    bw.write("CUENTA");
                    bw.newLine();

                    if (cuenta instanceof Inversion) {
                        Inversion inversion = (Inversion) cuenta;
                        bw.write(String.join("|",
                                inversion.getNumeroCuenta(),
                                "INVERSION",
                                String.valueOf(inversion.getSaldo()),
                                String.valueOf(inversion.getNip()),
                                String.valueOf(inversion.getRendimientoMensual()),
                                String.valueOf(inversion.getMesesInvertidos())));
                        bw.newLine();

                    } else if (cuenta instanceof Nomina) {
                        Nomina nomina = (Nomina) cuenta;
                        bw.write(String.join("|",
                                nomina.getNumeroCuenta(),
                                "NOMINA",
                                String.valueOf(nomina.getSaldo()),
                                String.valueOf(nomina.getNip()),
                                nomina.getEmpleadorDeposito(),
                                nomina.getLugarTrabajo(),
                                String.valueOf(nomina.getSalario())));
                        bw.newLine();

                    } else if (cuenta instanceof Credito) {
                        Credito credito = (Credito) cuenta;
                        bw.write(String.join("|",
                                credito.getNumeroCuenta(),
                                "CREDITO",
                                String.valueOf(credito.getSaldo()),
                                String.valueOf(credito.getNip()),
                                String.valueOf(credito.getLimiteEstablecido())));
                        bw.newLine();
                    }

                    // Escribir movimientos de la cuenta
                    for (Movimientos mov : cuenta.getMovimientos()) {
                        bw.write("MOVIMIENTO");
                        bw.newLine();
                        bw.write(String.join("|",
                                mov.getTipoOperacion().name(),
                                String.valueOf(mov.getMonto()),
                                mov.getFechaHora(),
                                mov.getCuentaOrigen() != null ? mov.getCuentaOrigen().getNumeroCuenta() : "",
                                mov.getCuentaDestino() != null ? mov.getCuentaDestino().getNumeroCuenta() : "",
                                mov.getConcepto() != null ? mov.getConcepto() : ""));
                        bw.newLine();
                    }
                }
                // Salto de línea extra entre clientes para mantener formato legible como en el txt original
                if (i < clientes.size() - 1) {
                    bw.newLine();
                }
            }
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }
}