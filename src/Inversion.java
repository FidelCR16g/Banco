public abstract class Inversion extends Cuenta {
    private double ganancia;
    private double rendimientoMensual;

    public Inversion() {}

    public Inversion(String numeroCuenta, double saldo, double ganancia, double rendimientoMensual) {
        super(numeroCuenta, TipoCuenta.INVERSION, saldo);
        this.ganancia = ganancia;
        this.rendimientoMensual = rendimientoMensual;
    }

    public void mostrarGanancia() {

    }

    @Override
    public void mostrarCuenta() {

    }

    @Override
    public void consultarSaldo() {

    }
}
