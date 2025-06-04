import java.util.Date;

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

    //Getters
    public String getEmpleadorDeposito(){
        return empleadorDeposito;
    }

    public String getLugarTrabajo(){
        return lugarTrabajo;
    }

    public double getSalario(){
        return salario;
    }

    //Setters
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
     * Permite retirar dinero de la cuenta después de validar el NIP.
     * Verifica que el monto sea positivo, no supere $9000 y que haya saldo suficiente.
     * Registra el movimiento si es exitoso.
     */

    @Override
    public void retirar(double monto) {
        String fechaHora = new Date().toString();

        if (monto < 0) {
            System.out.println("El monto a retirar debe ser una cantidad positiva");
        } else if (monto > 9000) {
            System.out.println("No puedes retirar una cantidad mayor a 9000");
        } else if (saldo < monto) {
            System.out.println("Saldo insuficiente. Tu saldo es: " + getSaldo());
        }else{
            saldo -= monto;
            Movimientos retiro = new Movimientos(
                    Movimientos.TipoOperacion.RETIRAR,
                    monto,
                    fechaHora,
                    this,
                    null,
                    "Retiro en efectivo"
            );

            this.getMovimientos().add(retiro);
            retiro.procesarTicket();
            System.out.println("Retiro exitoso");
        }
    }

    /**
     * Permite depositar dinero en la cuenta después de validar el NIP.
     * Verifica que el monto sea positivo. Registra el movimiento si es exitoso.
     */
    @Override
    public void depositar(double monto) {
        String fechaHora = new Date().toString();

        if (monto < 0) {
            System.out.println("El monto a depositar debe ser una cantidad positiva");
        } else {
            saldo += monto;
            Movimientos deposito = new Movimientos(
                    Movimientos.TipoOperacion.DEPOSITAR,
                    monto,
                    fechaHora,
                    null,
                    this,
                    "Depósito en efectivo"
            );

            this.getMovimientos().add(deposito);
            deposito.procesarTicket();
            System.out.println("Depósito exitoso");
        }
    }

    /**
     * Muestra información detallada de la cuenta de nómina,
     * incluyendo número de cuenta, saldo, empleador, lugar de trabajo y salario.
     */
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Nómina");
        System.out.println("Número de cuenta: " + getNumeroCuenta());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Empleador: " + getEmpleadorDeposito());
        System.out.println("Lugar de trabajo: " + getLugarTrabajo());
        System.out.println("Salario: " + getSalario());
    }

    /**
     * Muestra el tipo de cuenta: "Nomina".
     */
    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Nomina");
    }
}