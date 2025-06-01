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

    public Movimientos(){}

    public Movimientos(TipoOperacion tipoOperacion, double monto, String fechaHora, Cuenta cuentaOrigen, Cuenta cuentaDestino, String concepto) {
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

    public void procesarTicket() {
        Ticket ticket = new Ticket(this.fechaHora, this);
        ticket.guardarTicket();
    }

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

            this.procesarTicket();
            System.out.println("Transferencia exitosa");
        }
    }

    public List<Movimientos> generarHistorial(Cuenta cuenta) {
        return cuenta.getMovimientos();
    }
}
