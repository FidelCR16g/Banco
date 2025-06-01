import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Ticket que representa un comprobante de un movimiento bancario.
 * Permite generar un resumen del movimiento y guardarlo en un archivo de texto.
 */
public class Ticket {
    private String fechaGeneracionTicket;
    private Movimientos movimientos;

    /**
     * Constructor vacío.
     */
    public Ticket() {}

    /**
     * Constructor con parámetros.
     * @param fechaGeneracionTicket Fecha y hora de generación del ticket.
     * @param movimientos Movimiento bancario asociado al ticket.
     */
    public Ticket(String fechaGeneracionTicket, Movimientos movimientos) {
        this.fechaGeneracionTicket = fechaGeneracionTicket;
        this.movimientos = movimientos;
    }

    /**
     * Genera el contenido del ticket en formato texto.
     * @return Cadena con el contenido formateado del ticket.
     */
    public String generarTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**************************************");
        sb.append("\n*** COMPROBANTE ***");
        sb.append("\nTipo de operación: ").append(movimientos.getTipoOperacion());
        sb.append("\nFecha y hora: ").append(movimientos.getFechaHora());
        sb.append("\nMonto: $").append(String.format("%.2f", movimientos.getMonto()));

        switch (movimientos.getTipoOperacion()) {
            case TRANSFERIR:
                sb.append("\nCuenta origen: ")
                        .append(movimientos.getCuentaOrigen() != null ? movimientos.getCuentaOrigen().getNumeroCuenta() : "N/A");
                sb.append("\nCuenta destino: ")
                        .append(movimientos.getCuentaDestino() != null ? movimientos.getCuentaDestino().getNumeroCuenta() : "N/A");
                break;
            case DEPOSITAR:
                sb.append("\nCuenta destino: ")
                        .append(movimientos.getCuentaDestino() != null
                                ? movimientos.getCuentaDestino().getNumeroCuenta()
                                : movimientos.getCuentaOrigen() != null
                                ? movimientos.getCuentaOrigen().getNumeroCuenta()
                                : "N/A");
                break;
            case RETIRAR:
                sb.append("\nCuenta: ")
                        .append(movimientos.getCuentaOrigen() != null ? movimientos.getCuentaOrigen().getNumeroCuenta() : "N/A");
                break;
        }

        sb.append("\nConcepto: ").append(movimientos.getConcepto());
        sb.append("\n**************************************");

        return sb.toString();
    }

    /**
     * Guarda el ticket generado en un archivo de texto.
     * El nombre del archivo se forma con el tipo de operación y la fecha-hora.
     */
    public void guardarTicket() {
        String fechaArchivo = fechaGeneracionTicket.replace(":", "-").replace(" ", "_");
        String nombreArchivo = "ticket_" + movimientos.getTipoOperacion().toString().toLowerCase() + "_" + fechaArchivo + ".txt";

        // Ruta relativa a la carpeta del proyecto
        File carpetaTickets = new File("resources/tickets");
        if (!carpetaTickets.exists()) {
            carpetaTickets.mkdirs();
        }

        File archivo = new File(carpetaTickets, nombreArchivo);

        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(generarTicket());
            System.out.println("Ticket guardado en " + archivo.getPath());
        } catch (IOException e) {
            System.out.println("Error al guardar el ticket: " + e.getMessage());
        }
    }
}