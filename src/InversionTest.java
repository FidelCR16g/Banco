import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InversionTest {
    private Inversion cuentaInversion;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Fidel Cruz Reyes", "INE123456", "2288538192",
                "ficelcr16j@mail.com", "FidelCR16g", "contrasenia");
        cuentaInversion = new Inversion("5698", 13000.0, 1609, 0.05, 12, cliente);
    }

    @Test
    void testCalcularGanancia() {
        double gananciaEsperada = 13000.0 * (Math.pow(1 + (0.05 / 100), 12)) - 13000.0;
        assertEquals(gananciaEsperada, cuentaInversion.calcularGanancia(), 0.01);
    }

    @Test
    void testRetirarAntesTiempo() {
        cuentaInversion.setMesesInvertidos(6);
        cuentaInversion.retirar(5000);
        assertTrue(cuentaInversion.getMovimientos().isEmpty(), "No debería permitir retiros anticipados");
    }

    @Test
    void testModificarParametrosInversion() {
        cuentaInversion.setRendimientoMensual(0.075);
        cuentaInversion.setMesesInvertidos(24);
        assertEquals(0.075, cuentaInversion.getRendimientoMensual());
        assertEquals(24, cuentaInversion.getMesesInvertidos());
    }
}