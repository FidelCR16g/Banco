public class Nomina extends Cuenta {
    private String empleadorDeposito;
    private String lugarTrabajo;
    private double salario;

    public Nomina() {}

    public Nomina(String numeroCuenta, double saldo, int nip, Cliente cliente, String empleadorDeposito, String lugarTrabajo, double salario) {
        super(numeroCuenta, TipoCuenta.NOMINA, saldo, nip, cliente);
        this.empleadorDeposito = empleadorDeposito;
        this.lugarTrabajo = lugarTrabajo;
        this.salario = salario;
    }

    public void mostrarSalario() {
        System.out.println("Tu salario es: " + salario);
    }

    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Nómina" + "\nNúmero de cuenta: " + numeroCuenta + "\nSaldo: " + saldo + "\nEmpleador: " + empleadorDeposito + "\nLugar de trabajo: " + lugarTrabajo + "\nSalario: " + salario);
    }

    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Nomina");
    }
}
