import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class MovimientosTest {

    private Nomina cuentaOrigen;
    private Nomina cuentaDestino;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente("Juan Pérez", "INE123", "5551234567", "juan@mail.com", "juan123", "1234");
        cuentaOrigen = new Nomina("12345", 5000.0, 1234, "Pollos Asados", "Oficina Central", 10000.0, cliente);
        cuentaDestino = new Nomina("67890", 3000.0, 5678, "Verduleria Pancho", "Sucursal", 8000.0, cliente);
    }

    @Test
    public void testCrearMovimientoDeposito() {
        Movimientos movimiento = new Movimientos(
                Movimientos.TipoOperacion.DEPOSITAR,
                1500.0,
                new Date().toString(),
                null,
                cuentaDestino,
                "Depósito de prueba"
        );

        assertEquals(Movimientos.TipoOperacion.DEPOSITAR, movimiento.getTipoOperacion());
        assertEquals(1500.0, movimiento.getMonto());
        assertNull(movimiento.getCuentaOrigen());
        assertEquals(cuentaDestino, movimiento.getCuentaDestino());
        assertEquals("Depósito de prueba", movimiento.getConcepto());
    }

    @Test
    public void testCrearMovimientoRetiro() {
        Movimientos movimiento = new Movimientos(
                Movimientos.TipoOperacion.RETIRAR,
                700.0,
                new Date().toString(),
                cuentaOrigen,
                null,
                "Retiro en cajero"
        );

        assertEquals(Movimientos.TipoOperacion.RETIRAR, movimiento.getTipoOperacion());
        assertEquals(700.0, movimiento.getMonto());
        assertEquals(cuentaOrigen, movimiento.getCuentaOrigen());
        assertNull(movimiento.getCuentaDestino());
        assertEquals("Retiro en cajero", movimiento.getConcepto());
    }

    @Test
    public void testMovimientoTransferenciaSimulada() {
        Movimientos movimiento = new Movimientos(
                Movimientos.TipoOperacion.DEPOSITAR,
                1000.0,
                new Date().toString(),
                cuentaOrigen,
                cuentaDestino,
                "Transferencia interna"
        );

        assertEquals(cuentaOrigen, movimiento.getCuentaOrigen());
        assertEquals(cuentaDestino, movimiento.getCuentaDestino());
        assertEquals("Transferencia interna", movimiento.getConcepto());
        assertEquals(1000.0, movimiento.getMonto());
    }
}
