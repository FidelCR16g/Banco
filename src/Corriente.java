public abstract class Corriente extends Cuenta {
    private double saldoActual;

    public Corriente() {}

    public Corriente(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, Cliente cliente, double saldoActual) {
        super(numeroCuenta, TipoCuenta.CORRIENTE, saldo, cliente);
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
