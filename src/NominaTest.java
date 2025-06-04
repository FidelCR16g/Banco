import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class NominaTest {
    private Nomina cuentaNomina;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana Georgina", "INE654321", "5557654321",
                "ana@mail.com", "anageo", "5678");
        cuentaNomina = new Nomina("22345", 18900.0, 2468,
                "Empresa ABC", "Sucursal Norte", 12000.0, cliente);
    }

    @Test
    void testRetiroExcedeLimiteDiario() {
        cuentaNomina.retirar(9001); // Simular retiro de 9001
        assertEquals(18900.0, cuentaNomina.getSaldo(), "No debe permitir retiros mayores a 9000");
    }

    @Test
    void testDepositoAumentaSaldo() {
        double saldoInicial = cuentaNomina.getSaldo();
        cuentaNomina.depositar(1000); // Simular depósito de 1000
        assertEquals(saldoInicial + 1000.0, cuentaNomina.getSaldo(), 0.01);
    }


    @Test
    void testTransferenciaEntreCuentas() {
        Nomina cuentaDestino = new Nomina("2658", 10900.0, 1609,
                "Empresa XYZ", "Oficina Central", 15000.0, cliente);

        double monto = 200.0;
        double saldoOrigenInicial = cuentaNomina.getSaldo();
        double saldoDestinoInicial = cuentaDestino.getSaldo();

        String fechaHora = new Date().toString();
        Movimientos transferencia = new Movimientos(
                Movimientos.TipoOperacion.TRANSFERIR,
                monto,
                fechaHora,
                cuentaNomina,
                cuentaDestino,
                "Pago servicio"
        );

        transferencia.transferir();

        assertEquals(saldoOrigenInicial - monto, cuentaNomina.getSaldo(), 0.01);
        assertEquals(saldoDestinoInicial + monto, cuentaDestino.getSaldo(), 0.01);
    }
}