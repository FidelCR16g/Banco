/**
 * <p>Clase Inversion que hereda de Cuenta y representa una cuenta de inversión.</p>
 *
 * <p>Contiene atributos específicos como ganancia, rendimiento mensual, saldo inicial y meses invertidos.</p>
 *
 * <p>Incluye métodos para calcular y mostrar la ganancia estimada basada en el rendimiento mensual y
 * los meses invertidos. También sobreescribe el método mostrarCuenta() de la clase Cuenta para mostrar
 * información detallada de la cuenta de inversión.</p>
 */
public class Inversion extends Cuenta {
    private double ganancia;
    private double rendimientoMensual; // en porcentaje (2.5 significa 2.5%)
    private double saldoInicial;
    private int mesesInvertidos;

    /**
     * Constructor vacío.
     */
    public Inversion() {}

    /**
     * Constructor con parámetros que inicializa una cuenta de inversión.
     *
     * @param numeroCuenta Número de cuenta.
     * @param saldoInicial Saldo inicial invertido.
     * @param nip Número de identificación personal (NIP).
     * @param rendimientoMensual Rendimiento mensual esperado en porcentaje.
     * @param mesesInvertidos Número de meses que se invertirá el saldo.
     * @param cliente Cliente asociado a la cuenta.
     */
    public Inversion(String numeroCuenta, double saldoInicial, int nip, double rendimientoMensual, int mesesInvertidos, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.INVERSION, saldoInicial, nip, cliente);
        this.saldoInicial = saldoInicial;
        this.rendimientoMensual = rendimientoMensual;
        this.mesesInvertidos = mesesInvertidos;
    }

    public double getGanancia(){
        return ganancia;
    }

    public double getRendimientoMensual(){
        return rendimientoMensual;
    }

    public double getSaldoInicial(){
        return saldoInicial;
    }

    public int getMesesInvertidos(){
        return mesesInvertidos;
    }

    public void setGanancia(double ganancia){
        this.ganancia = ganancia;
    }

    public void setRendimientoMensual(double rendimientoMensual){
        this.rendimientoMensual = rendimientoMensual;
    }

    public void setSaldoInicial(double saldoInicial){
        this.saldoInicial = saldoInicial;
    }

    public void setMesesInvertidos(int mesesInvertidos){
        this.mesesInvertidos = mesesInvertidos;
    }

    /**
     * Calcula la ganancia estimada con base en el saldo inicial,
     * rendimiento mensual y meses invertidos usando interés compuesto.
     *
     * @return Ganancia calculada (saldo final menos saldo inicial).
     */
    public double calcularGanancia() {
        double saldoFinal = getSaldoInicial() * Math.pow(1 + (getRendimientoMensual() / 100), getMesesInvertidos());
        // La función Math.pow(a, b) eleva un número 'a' a la potencia 'b'
        return saldoFinal - saldoInicial;
    }

    /**
     * Muestra por consola el saldo inicial, meses invertidos,
     * saldo final estimado y la ganancia calculada.
     */
    public void mostrarGanancia() {
        double saldoFinal = saldoInicial * Math.pow(1 + (rendimientoMensual / 100), mesesInvertidos);
        ganancia = saldoFinal - saldoInicial;

        System.out.printf("Saldo inicial: $%.2f\n", getSaldoInicial());
        System.out.println("Meses invertidos: " + getMesesInvertidos());
        System.out.printf("Saldo final estimado: $%.2f\n", saldoFinal);
        System.out.printf("Ganancia: $%.2f\n", getGanancia());
    }

    /**
     * Muestra información detallada de la cuenta de inversión,
     * incluyendo tipo de cuenta, número, saldo, cliente y ganancia.
     */
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de tipo INVERSION");
        System.out.println("Número de cuenta: " + getNumeroCuenta());
        System.out.println("Saldo actual: $" + getSaldo());
        System.out.println("Cliente: " + (getCliente() != null ? getCliente().getNombreC() : "No hay un cliente."));
        mostrarGanancia();
    }

    /**
     * Muestra el tipo de cuenta: "Inversion".
     */
    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Inversion");
    }
}
