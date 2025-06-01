import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase para guardar la información de clientes, cuentas y movimientos
 * en un archivo de texto con un formato legible y estructurado.
 */
public class EscritorDatos {

    /**
     * Guarda la lista de clientes en un archivo de texto especificado,
     * incluyendo sus cuentas y movimientos asociados.
     *
     * @param ruta     La ruta completa del archivo donde se guardarán los datos.
     * @param clientes La lista de objetos Cliente a guardar.
     */
    public static void guardarClientesEnArchivo(String ruta, List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);
                // Escribir etiqueta de cliente
                bw.write("CLIENTE");
                bw.newLine();

                // Escribir datos del cliente separados por "|"
                bw.write(String.join("|",
                        cliente.getNombreC(),
                        cliente.getIne(),
                        cliente.getTelefonoC(),
                        cliente.getCorreoC(),
                        cliente.getUsuario(),
                        cliente.getContrasenia()));
                bw.newLine();

                // Escribir las cuentas del cliente
                for (Cuenta cuenta : cliente.getCuentas()) {
                    bw.write("CUENTA");
                    bw.newLine();

                    if (cuenta instanceof Inversion) {
                        Inversion inversion = (Inversion) cuenta;
                        bw.write(String.join("|",
                                inversion.getNumeroCuenta(),
                                "INVERSION",
                                String.format("%.2f", inversion.getSaldo()),        // saldo formateado a 2 decimales
                                String.valueOf(inversion.getNip()),
                                String.format("%.2f", inversion.getRendimientoMensual()),
                                String.valueOf(inversion.getMesesInvertidos())));
                        bw.newLine();

                    } else if (cuenta instanceof Nomina) {
                        Nomina nomina = (Nomina) cuenta;
                        bw.write(String.join("|",
                                nomina.getNumeroCuenta(),
                                "NOMINA",
                                String.format("%.2f", nomina.getSaldo()),
                                String.valueOf(nomina.getNip()),
                                nomina.getEmpleadorDeposito(),
                                nomina.getLugarTrabajo(),
                                String.format("%.2f", nomina.getSalario())));
                        bw.newLine();

                    } else if (cuenta instanceof Credito) {
                        Credito credito = (Credito) cuenta;
                        bw.write(String.join("|",
                                credito.getNumeroCuenta(),
                                "CREDITO",
                                String.format("%.2f", credito.getSaldo()),
                                String.valueOf(credito.getNip()),
                                String.format("%.2f", credito.getLimiteEstablecido())));
                        bw.newLine();
                    }

                    // Escribir movimientos de la cuenta
                    for (Movimientos mov : cuenta.getMovimientos()) {
                        bw.write("MOVIMIENTO");
                        bw.newLine();
                        bw.write(String.join("|",
                                mov.getTipoOperacion().name(),
                                String.format("%.2f", mov.getMonto()),
                                mov.getFechaHora(),
                                mov.getCuentaOrigen() != null ? mov.getCuentaOrigen().getNumeroCuenta() : "",
                                mov.getCuentaDestino() != null ? mov.getCuentaDestino().getNumeroCuenta() : "",
                                mov.getConcepto() != null ? mov.getConcepto() : ""));
                        bw.newLine();
                    }
                }

                // Salto de línea extra entre clientes para mayor legibilidad
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