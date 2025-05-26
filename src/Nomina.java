public abstract class Nomina extends Cuenta {
    private String empleadorDeposito;
    private String lugarTrabajo;
    private double salario;

    public Nomina() {}

    public Nomina(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, Cliente cliente, String empleadorDeposito, String lugarTrabajo, double salario) {
        super(numeroCuenta, TipoCuenta.NOMINA, saldo, cliente);
        this.empleadorDeposito = empleadorDeposito;
        this.lugarTrabajo = lugarTrabajo;
        this.salario = salario;
    }

    public void mostrarSalario() {

    }

    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Nómina" + "\nNúmero de cuenta: " + numeroCuenta + "\nSaldo: " + saldo + "\nEmpleador: " + empleadorDeposito + "\nLugar de trabajo: " + lugarTrabajo + "\nSalario: " + salario);
    }

    @Override
    public void consultarSaldo() {

    }
}
