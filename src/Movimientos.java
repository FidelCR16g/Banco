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
    private Ticket ticket;

    public Movimientos(){}

    public Movimientos(TipoOperacion tipoOperacion, double monto, String fechaHora, Cuenta cuentaOrigen, Cuenta cuentaDestino, String concepto, Ticket ticket) {
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.concepto = concepto;
        this.ticket = ticket;
    }

    public void generarTicket() {
        StringBuilder sb = new StringBuilder();
        sb.append("**************************************");
        sb.append("COMPROBANTE").append("\nOperación: ").append(tipoOperacion);
        sb.append("COMPROBANTE").append("\nFecha y hora: ").append(fechaHora);
        sb.append("COMPROBANTE").append("\nMonto: ").append(monto).append("\n");

        if (tipoOperacion == TipoOperacion.TRANSFERIR) {
            sb.append("Cuenta origen: ").append(cuentaOrigen.getNumeroCuenta()).append("\n");
            sb.append("Cuenta destino: ").append(cuentaDestino.getNumeroCuenta()).append("\n");
        }

        sb.append("Concepto: ").append(concepto).append("\n");
        sb.append("**************************************");

        ticket = new Ticket(sb.toString());
        ticket.generarTxt();
    }

    public void transferir() {
        if (cuentaOrigen != null && cuentaDestino != null) {
            if (cuentaOrigen.getCliente() != null && cuentaDestino.getCliente() != null) {
                if (cuentaOrigen.saldo >= monto) {
                    cuentaOrigen.saldo -= monto;
                    cuentaDestino.saldo += monto;

                    cuentaOrigen.getMovimientos().add(this);
                    cuentaDestino.getMovimientos().add(this);

                    generarTicket();
                } else {
                    System.out.println("El saldo es insuficiente para realizar la transferencia.");
                }
            } else {
                System.out.println("Cuentas no válidas.");
            }
        } else {
            System.out.println("No se puede transferir porque una de las cuentas es nula.");
        }
    }

    public List<Movimientos> generarHistorial(Cuenta cuenta) {
        return cuenta.getMovimientos();
    }
}
