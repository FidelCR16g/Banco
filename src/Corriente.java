public class Corriente extends Cuenta {
    private double saldoActual;

    public Corriente() {}

    public Corriente(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, Cliente cliente, double saldoActual) {
        super(numeroCuenta, TipoCuenta.CORRIENTE, saldo, cliente);
        this.saldoActual = saldoActual;
    }

    public void mostrarSaldoActual() {

    }

    //Dudas con saldo y saldoActual
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta Corriente" + "\nNúmero de cuenta: " + numeroCuenta + "\nSaldo: " + saldo + "\nSaldo actual: " + saldoActual);
    }
}
