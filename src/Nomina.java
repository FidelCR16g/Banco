public class Nomina extends Cuenta {
    private String empleadorDeposito;
    private String lugarTrabajo;
    private double salario;

    public Nomina() {}

    public Nomina(String numeroCuenta, double saldo, int nip, String empleadorDeposito, String lugarTrabajo, double salario, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.NOMINA, saldo, nip, cliente);
        this.empleadorDeposito = empleadorDeposito;
        this.lugarTrabajo = lugarTrabajo;
        this.salario = salario;
    }

    public String getEmpleadorDeposito(){
        return empleadorDeposito;
    }

    public String getLugarTrabajo(){
        return lugarTrabajo;
    }

    public double getSalario(){
        return salario;
    }

    public void setEmpleadorDeposito(String empleadorDeposito){
        this.empleadorDeposito = empleadorDeposito;
    }

    public void setLugarTrabajo(String lugarTrabajo){
        this.lugarTrabajo = lugarTrabajo;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public void mostrarSalario() {
        System.out.println("Tu salario es: " + getSalario());
    }

    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Nómina" + "\nNúmero de cuenta: " + getNumeroCuenta() + "\nSaldo: " + getSalario() + "\nEmpleador: " + getEmpleadorDeposito() + "\nLugar de trabajo: " + getLugarTrabajo() + "\nSalario: " + getSalario());
    }

    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Nomina");
    }
}
