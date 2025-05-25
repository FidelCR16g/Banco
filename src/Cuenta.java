public abstract class Cuenta {
    public enum TipoCuenta {
        INVERSION,
        NOMINA,
        CORRIENTE,
        CREDITO
    }

    protected String numeroCuenta;
    protected TipoCuenta tipoCuenta;
    protected double saldo;

    public Cuenta(){}

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double monto) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    }

    public abstract void mostrarCuenta();

    public abstract void consultarSaldo();

    public double retirar(double monto) {
        if (saldo < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        saldo -= monto;
        return saldo;
    }

    public double depositar(double monto) {
        return saldo;
    }
}
