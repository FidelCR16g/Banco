public abstract class Nomina extends Cuenta {
    private String empleadorDeposito;
    private String lugarTrabajo;
    private double salario;

    public Nomina() {}

    public Nomina(String numeroCuenta, double saldo, String empleadorDeposito, String lugarTrabajo, double salario) {
        super(numeroCuenta, TipoCuenta.NOMINA, saldo);
        this.empleadorDeposito = empleadorDeposito;
        this.lugarTrabajo = lugarTrabajo;
        this.salario = salario;
    }

    public void mostrarSalario() {

    }

    @Override
    public void mostrarCuenta() {

    }

    @Override
    public void consultarSaldo() {

    }
}
