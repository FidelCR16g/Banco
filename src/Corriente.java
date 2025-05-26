public abstract class Corriente extends Cuenta {
    private double saldoActual;

    public Corriente() {}

    public Corriente(String numeroCuenta, double saldo, double saldoActual) {
        super(numeroCuenta, TipoCuenta.CORRIENTE, saldo);
        this.saldoActual = saldoActual;
    }

    public void mostrarSaldoActual() {

    }

    @Override
    public void mostrarCuenta() {

    }

    @Override
    public void consultarSaldo() {

    }
}
