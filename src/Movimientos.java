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

    public Movimientos(){}

    public Movimientos(TipoOperacion tipoOperacion, double monto, String fechaHora, Cuenta cuentaOrigen, Cuenta cuentaDestino, String concepto, Cuenta cuenta) {
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.concepto = concepto;
        this.cuenta = cuenta;
    }

    public void generarTicket() {

    }

    public void transferir() {

    }

    public void generarHistorial() {

    }
}
