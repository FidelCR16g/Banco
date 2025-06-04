import java.util.Date;

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

    // Getters
    public double getGanancia() {
        return ganancia;
    }

    public double getRendimientoMensual() {
        return rendimientoMensual;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public int getMesesInvertidos() {
        return mesesInvertidos;
    }

    //Setters
    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public void setRendimientoMensual(double rendimientoMensual) {
        this.rendimientoMensual = rendimientoMensual;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public void setMesesInvertidos(int mesesInvertidos) {
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
        this.ganancia = saldoFinal - saldoInicial;
        return ganancia;
    }

    public void mostrarInversion(){
        System.out.println("--- Detalles de Inversión ---");
        System.out.println("Saldo inicial: $" + getSaldoInicial());
        System.out.println("Rendimiento mensual: %" + getRendimientoMensual());
        System.out.println("Meses invertidos: " + getMesesInvertidos());
        System.out.println("Saldo actual: $" + getSaldo());
    }

    /**
     * Muestra por consola el saldo inicial, meses invertidos,
     * saldo final estimado y la ganancia calculada.
     */
    public void mostrarGanancia() {
        calcularGanancia();
        double saldoFinal = saldoInicial + ganancia;

        System.out.println("=== Detalles de Inversión ===");
        System.out.println("Saldo inicial: $" + getSaldoInicial());
        System.out.println("Rendimiento mensual: %" + getRendimientoMensual());
        System.out.println("Meses invertidos: " + getMesesInvertidos());
        System.out.println("Saldo final estimado: $" + saldoFinal);
        System.out.println("Ganancia estimada: $" + getGanancia());
        System.out.println("============================");
    }

    /**
     * Permite retirar dinero de la cuenta de inversión después de validar el NIP.
     * Verifica que el monto sea positivo y que haya saldo suficiente.
     * Registra el movimiento si es exitoso.
     */
    @Override
    public void retirar(double monto) {
        String fechaHora = new Date().toString();
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a cero");
        } else if (monto > getSaldo()) {
            System.out.println("Fondos insuficientes. Saldo disponible: $" + getSaldo());
        } else {
            disminuirSaldo(monto);
            Movimientos retiro = new Movimientos(
                    Movimientos.TipoOperacion.RETIRAR,
                    monto,
                    fechaHora,
                    this,
                    null,
                    "Retiro de cuenta de inversión"
            );

            getMovimientos().add(retiro);
            retiro.procesarTicket();
            System.out.println("Retiro exitoso. Nuevo saldo: $" + getSaldo());
        }
    }

    /**
     * Permite depositar dinero en la cuenta de inversión después de validar el NIP.
     * Verifica que el monto sea positivo. Registra el movimiento si es exitoso.
     * Actualiza el saldo inicial si no hay meses invertidos aún.
     */
    @Override
    public void depositar(double monto) {
        String fechaHora = new Date().toString();
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor a cero");
        } else {
            aumentarSaldo(monto);
            if (mesesInvertidos == 0) {
                saldoInicial += monto;
            }

            Movimientos deposito = new Movimientos(
                    Movimientos.TipoOperacion.DEPOSITAR,
                    monto,
                    fechaHora,
                    null,
                    this,
                    "Depósito a cuenta de inversión"
            );

            getMovimientos().add(deposito);
            deposito.procesarTicket();
            System.out.println("Depósito exitoso. Nuevo saldo: $" + getSaldo());
        }
    }

    /**
     * Muestra información detallada de la cuenta de inversión,
     * incluyendo tipo de cuenta, número, saldo, cliente y detalles de la inversión.
     */
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Inversion.");
        System.out.println("Número de cuenta: " + getNumeroCuenta());
        System.out.println("Saldo actual: $" + getSaldo());
        System.out.println("Cliente: " + (getCliente() != null ? getCliente().getNombreC() : "No asignado"));
        mostrarGanancia();
    }

    /**
     * Muestra el tipo de cuenta: "Inversion".
     */
    @Override
    public void mostrarTipoCuenta() {
        System.out.println("Inversión");
    }
}