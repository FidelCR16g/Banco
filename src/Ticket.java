import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String fechaGeneracionTicket;
    private Movimientos movimientos;

    public Ticket(){}

    public Ticket(String fechaGeneracionTicket, Movimientos movimientos) {
        this.fechaGeneracionTicket = fechaGeneracionTicket;
        this.movimientos = movimientos;
    }

    public String generarTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n**************************************");
        sb.append("\n*** COMPROBANTE ***");
        sb.append("\nTipo de operación: ").append(movimientos.getTipoOperacion());
        sb.append("\nFecha y hora: ").append(movimientos.getFechaHora());
        sb.append("\nMonto: $").append(movimientos.getMonto());

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

    public void guardarTicket() {
        String nombreArchivo = "ticket_" + movimientos.getTipoOperacion().toString().toLowerCase() + "_" + fechaGeneracionTicket.replace(":", "-") + ".txt";
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(generarTicket());
            System.out.println("Ticket guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guadar: " + e.getMessage());
        }
    }
}