import java.util.List;

public class Movimientos {
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
    private Cuenta cuenta;
    private Ticket ticket;

    public Movimientos(){}

    public Movimientos(TipoOperacion tipoOperacion, double monto, String fechaHora, Cuenta cuentaOrigen, Cuenta cuentaDestino, String concepto, Cuenta cuenta, Ticket ticket) {
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.concepto = concepto;
        this.cuenta = cuenta;
        this.ticket = ticket;
    }

    public void generarTicket() {

    }

    public void transferir() {

    }

    public List<Movimientos> generarHistorial(Cuenta cuenta) {
        return cuenta.getMovimientos();
    }
}
