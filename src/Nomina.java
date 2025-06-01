/**
 * <p>Clase Nomina que hereda de Cuenta y representa una cuenta de nómina.</p>
 *
 * <p>Contiene atributos específicos como empleador, lugar de trabajo y salario.</p>
 *
 * <p>Incluye métodos para obtener y modificar estos atributos, mostrar el salario,
 * y sobreescribe el método mostrarCuenta() para mostrar información detallada de la cuenta.</p>
 */
public class Nomina extends Cuenta {
    private String empleadorDeposito;
    private String lugarTrabajo;
    private double salario;

    /**
     * Constructor vacío.
     */
    public Nomina() {}

    /**
     * Constructor con parámetros que inicializa una cuenta de nómina.
     *
     * @param numeroCuenta Número de cuenta.
     * @param saldo Saldo actual de la cuenta.
     * @param nip Número de identificación personal (NIP).
     * @param empleadorDeposito Nombre o razón social del empleador que realiza el depósito.
     * @param lugarTrabajo Lugar o empresa donde trabaja el titular.
     * @param salario Salario mensual depositado.
     * @param cliente Cliente asociado a la cuenta.
     */
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

    /**
     * Muestra por consola el salario mensual depositado en la cuenta.
     */
    public void mostrarSalario() {
        System.out.println("Tu salario es: " + getSalario());
    }

    /**
     * Muestra información detallada de la cuenta de nómina,
     * incluyendo número de cuenta, saldo, empleador, lugar de trabajo y salario.
     */
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Nómina" +
                "\nNúmero de cuenta: " + getNumeroCuenta() +
                "\nSaldo: " + getSaldo() +
                "\nEmpleador: " + getEmpleadorDeposito() +
                "\nLugar de trabajo: " + getLugarTrabajo() +
                "\nSalario: " + getSalario());
    }

    /**
     * Muestra el tipo de cuenta: "Nomina".
     */
    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Nomina");
    }
}

