import java.util.List;

/**
 * <p>Clase que representa un movimiento financiero dentro del sistema.</p>
 *
 * <p>Un movimiento puede ser un retiro, depósito o transferencia entre cuentas.
 * Contiene información como el tipo de operación, monto, fecha y hora,
 * cuentas involucradas y un concepto descriptivo.</p>
 */
public class Movimientos {

    /**
     * Enum que define los tipos de operación que se pueden realizar.
     */
    public enum TipoOperacion {
        RETIRAR,
        DEPOSITAR,
        TRANSFERIR
    }

    private TipoOperacion tipoOperacion;
    private double monto;
    private String fechaHora;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private String concepto;

    /**
     * Constructor por defecto.
     */
    public Movimientos() {}

    /**
     * Constructor que inicializa un movimiento con todos sus datos.
     *
     * @param tipoOperacion Tipo de operación (RETIRO, DEPÓSITO o TRANSFERENCIA).
     * @param monto Monto del movimiento.
     * @param fechaHora Fecha y hora en que se realiza el movimiento.
     * @param cuentaOrigen Cuenta desde donde se realiza la operación.
     * @param cuentaDestino Cuenta a la que se transfiere (si aplica).
     * @param concepto Concepto o descripción del movimiento.
     */
    public Movimientos(TipoOperacion tipoOperacion, double monto, String fechaHora,
                       Cuenta cuentaOrigen, Cuenta cuentaDestino, String concepto) {
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.concepto = concepto;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public double getMonto() {
        return monto;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public String getConcepto() {
        return concepto;
    }

    /**
     * Busca una cuenta en la lista de clientes a partir del número de cuenta.
     *
     * @param numeroCuenta Número de cuenta a buscar.
     * @param clientes Lista de clientes registrados.
     * @return La cuenta si se encuentra, null en caso contrario.
     */
    public static Cuenta buscarCuenta(String numeroCuenta, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            for (Cuenta cuenta : cliente.getCuentas()) {
                if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                    return cuenta;
                }
            }
        }
        return null;
    }

    /**
     * Procesa y guarda un ticket del movimiento realizado.
     */
    public void procesarTicket() {
        Ticket ticket = new Ticket(this.fechaHora, this);
        ticket.guardarTicket();
    }

    /**
     * Realiza una transferencia entre la cuenta de origen y la cuenta destino.
     * Verifica que haya saldo suficiente antes de ejecutar la operación.
     * Si la transferencia es exitosa, guarda el ticket y registra el movimiento en ambas cuentas.
     */
    public void transferir() {
        if (cuentaOrigen != null && cuentaDestino != null) {
            if (cuentaOrigen.getSaldo() < monto) {
                System.out.println("Saldo insuficiente para realizar la transferencia.");
                return;
            }
            cuentaOrigen.saldo -= monto;
            cuentaDestino.saldo += monto;

            cuentaOrigen.getMovimientos().add(this);
            cuentaDestino.getMovimientos().add(this);

            procesarTicket();
            System.out.println("Transferencia exitosa");
        }
    }

    /**
     * Genera e imprime el historial de movimientos de una cuenta.
     *
     * @param cuenta La cuenta cuyo historial se desea mostrar.
     */
    public void generarHistorial(Cuenta cuenta) {
        List<Movimientos> historial = cuenta.getMovimientos();

        if (historial.isEmpty()) {
            System.out.println("No hay movimientos registrados");
            return;
        }

        System.out.println("\nHistorial de movimientos:");
        for (Movimientos movimiento : historial) {
            String linea = "[" + movimiento.getFechaHora() + "] " +
                    movimiento.getTipoOperacion() + ": $" +
                    String.format("%.2f", movimiento.getMonto()) + " - " +
                    movimiento.getConcepto();
            System.out.println(linea);
        }
    }
}