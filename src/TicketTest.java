import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    private Nomina cuentaOrigen;
    private Nomina cuentaDestino;
    private Movimientos movimiento;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente("Xcaret Nub", "INE789", "5523456789", "xcaNub@mail.com", "xcaNub456", "contrasenia");
        cuentaOrigen = new Nomina("11111", 7000.0, 5678, "Pollo loco", "Sucursal", 9000.0, cliente);
        cuentaDestino = new Nomina("22222", 4000.0, 5678, "Pepsi", "Sucursal", 6000.0, cliente);
        movimiento = new Movimientos(Movimientos.TipoOperacion.TRANSFERIR, 1200.0, new Date().toString(), cuentaOrigen, cuentaDestino, "Pago de refrescos");
    }

    @Test
    public void testGenerarTicketContenido() {
        Ticket ticket = new Ticket(new Date().toString(), movimiento);
        String contenido = ticket.generarTicket();

        assertTrue(contenido.contains("COMPROBANTE"));
        assertTrue(contenido.contains("Tipo de operación: TRANSFERIR"));
        assertTrue(contenido.contains("Monto: $1200.00"));
        assertTrue(contenido.contains("Cuenta origen: 11111"));
        assertTrue(contenido.contains("Cuenta destino: 22222"));
        assertTrue(contenido.contains("Concepto: Pago de refrescos"));
    }

    @Test
    public void testGenerarTicketDeposito() {
        Movimientos deposito = new Movimientos(Movimientos.TipoOperacion.DEPOSITAR, 500.0, new Date().toString(), null, cuentaDestino, "Depósito nómina");
        Ticket ticket = new Ticket(new Date().toString(), deposito);

        String contenido = ticket.generarTicket();

        assertTrue(contenido.contains("Tipo de operación: DEPOSITAR"));
        assertTrue(contenido.contains("Cuenta destino: 22222"));
        assertTrue(contenido.contains("Concepto: Depósito nómina"));
    }

    @Test
    public void testGuardarTicketCreaArchivo() throws IOException {
        String fecha = new Date().toString();
        Ticket ticket = new Ticket(fecha, movimiento);

        ticket.guardarTicket();

        String fechaArchivo = fecha.replace(":", "-").replace(" ", "_");
        String nombreArchivo = "ticket_transferir_" + fechaArchivo + ".txt";

        File archivo = new File("resources/tickets/" + nombreArchivo);
        assertTrue(archivo.exists(), "El archivo del ticket no fue creado.");

        archivo.delete();
    }
}