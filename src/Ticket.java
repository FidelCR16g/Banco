import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String fechaGeneracionTicket;

    public Ticket(){}

    public Ticket(String fechaGeneracionTicket) {
        this.fechaGeneracionTicket = fechaGeneracionTicket;
    }

    public void generarTxt() {

    }

    public void guardarTxt(String contenido, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write(contenido);
            System.out.println("Ticket guardado en " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al guadar: " + e.getMessage());
        }
    }
}