import java.util.Date;

/**
 * Clase que representa una cuenta de tipo Crédito.
 * Hereda de la clase Cuenta e incluye un límite de crédito establecido.
 * Contiene constructores, métodos propios y sobrescribe métodos de la clase base.
 */
public class Credito extends Cuenta {
    /**
     * Límite máximo de crédito asignado a la cuenta.
     */
    private double limiteEstablecido;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío.
     */
    public Credito() {
    }

    /**
     * Constructor con parámetros para inicializar una cuenta de crédito.
     *
     * @param numeroCuenta      Número único de la cuenta.
     * @param saldo             Saldo inicial de la cuenta.
     * @param nip               Número de identificación personal.
     * @param limiteEstablecido Límite máximo de crédito permitido.
     * @param cliente           Cliente propietario de la cuenta.
     */
    public Credito(String numeroCuenta, double saldo, int nip, double limiteEstablecido, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.CREDITO, saldo, nip, cliente);
        this.limiteEstablecido = limiteEstablecido;
    }

    //Getters
    public double getLimiteEstablecido() {
        return limiteEstablecido;
    }

    //Setters
    public void setLimiteEstablecido(double limiteEstablecido) {
        this.limiteEstablecido = limiteEstablecido;
    }

    /**
     * Muestra por consola el límite de crédito establecido.
     */
    public void mostrarLimite() {
        System.out.println("Límite de crédito establecido: $" + getLimiteEstablecido());
    }

    /**
     * Permite retirar dinero de la cuenta después de validar el NIP.
     * Verifica que el monto sea positivo, no supere el límite disponible
     * y que haya saldo suficiente. Registra el movimiento si es exitoso.
     */
    @Override
    public void retirar(double monto) {
        String fechaHora = new Date().toString();
        if (monto < 0) {
            System.out.println("El monto a retirar debe ser una cantidad positiva");
        } else if (monto > limiteEstablecido) {
            System.out.println("El monto excede tu límite de crédito disponible");
            System.out.println("Límite disponible: $" + (limiteEstablecido - saldo));
        } else if (saldo + monto > limiteEstablecido) {
            System.out.println("El retiro excedería tu límite de crédito");
            System.out.println("Monto máximo que puedes retirar: $" + (limiteEstablecido - saldo));
        } else {
            saldo += monto;
            Movimientos retiro = new Movimientos(
                    Movimientos.TipoOperacion.RETIRAR,
                    monto,
                    fechaHora,
                    this,
                    null,
                    "Retiro de efectivo con tarjeta de crédito"
            );

            this.getMovimientos().add(retiro);
            retiro.procesarTicket();
            System.out.println("Retiro exitoso");
        }
    }

    /**
     * Permite depositar dinero en la cuenta después de validar el NIP.
     * Verifica que el monto sea positivo. Registra el movimiento si es exitoso.
     * En cuentas de crédito, los depósitos reducen el saldo (disminuyen la deuda).
     */
    @Override
    public void depositar(double monto) {
        String fechaHora = new Date().toString();

        if (monto < 0) {
            System.out.println("El monto a depositar debe ser una cantidad positiva");
        } else {
            if (saldo > 0) {
                saldo = Math.max(0, saldo - monto);
            }
            Movimientos deposito = new Movimientos(
                    Movimientos.TipoOperacion.DEPOSITAR,
                    monto,
                    fechaHora,
                    null,
                    this,
                    "Depósito a tarjeta de crédito (pago)"
            );

            this.getMovimientos().add(deposito);
            deposito.procesarTicket();
            System.out.println("Depósito exitoso");
        }
    }

    /**
     * Muestra información detallada de la cuenta, incluyendo tipo, número,
     * saldo actual, límite establecido y cliente asociado.
     * Método sobrescrito de la clase Cuenta.
     */
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de credito.");
        System.out.println("Número de cuenta: " + getNumeroCuenta());
        System.out.println("Saldo actual: $" + getSaldo());
        System.out.println("Límite establecido: $" + getLimiteEstablecido());
        System.out.println("Cliente: " + getCliente().getNombreC());
    }

    /**
     * Muestra el tipo de cuenta.
     * Método sobrescrito de la clase Cuenta.
     */
    @Override
    public void mostrarTipoCuenta() {
        System.out.println("Credito");
    }
}