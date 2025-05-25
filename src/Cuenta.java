public abstract class Cuenta {
    public enum TipoCuenta {
        INVERSION,
        NOMINA,
        CORRIENTE,
        CREDITO
    }

    protected String numeroCuenta;
    protected TipoCuenta tipoCuenta;
    protected double monto;

    public Cuenta(){}

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double monto) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.monto = monto;
    }

    public abstract void mostrarCuenta();

    public abstract void consultarSaldo();

    public double retirar(double monto) {

    }

    public double depositar(double monto) {

    }
}
