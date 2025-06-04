import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditoTest {
    private Credito cuentaCredito;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Xcaret Garcia Canseco", "INE789012", "5551234567",
                "xcaret@mail.com", "xcaretg", "xc1234");
        cuentaCredito = new Credito("4567", 0.0, 7890, 15000.0, cliente);
    }

    @Test
    void testRetirarExcedeLimite() {
        // Intentar retirar más del límite disponible
        cuentaCredito.retirar(16000); // Simular entrada de 16000 cuando se solicite
        assertEquals(0.0, cuentaCredito.getSaldo(), "El saldo no debe cambiar");
    }

    @Test
    void testDepositarReduceDeuda() {
        cuentaCredito.setSaldo(5000.0); // Establecer deuda de 5000
        cuentaCredito.depositar(3000); // Simular depósito de 3000
        assertEquals(2000.0, cuentaCredito.getSaldo(), "La deuda debe reducirse");
    }

    @Test
    void testCalcularCreditoDisponible() {
        cuentaCredito.setSaldo(3000.0);
        assertEquals(12000.0, cuentaCredito.getLimiteEstablecido() - cuentaCredito.getSaldo());
    }
}