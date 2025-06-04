import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    void testSesionValida_CredencialesCorrectas() {
        Cliente cliente = new Cliente("Juan Pérez", "INE123", "5551234567",
                "juan@mail.com", "juanito", "pass123");
        assertTrue(cliente.sesionValida("juanito", "pass123"));
    }

    @Test
    void testSesionValida_CredencialesIncorrectas() {
        Cliente cliente = new Cliente("Juan Pérez", "INE123", "5551234567",
                "juan@mail.com", "juanito", "pass123");

        assertFalse(cliente.sesionValida("juanito", "password_incorrecto"));
        assertFalse(cliente.sesionValida("usuario_incorrecto", "pass123"));
        assertFalse(cliente.sesionValida("", ""));
    }
}