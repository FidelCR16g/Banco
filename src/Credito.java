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

    /**
     * Constructor vacío.
     */
    public Credito() {}

    /**
     * Constructor con parámetros para inicializar una cuenta de crédito.
     *
     * @param numeroCuenta Número único de la cuenta.
     * @param saldo Saldo inicial de la cuenta.
     * @param nip Número de identificación personal.
     * @param limiteEstablecido Límite máximo de crédito permitido.
     * @param cliente Cliente propietario de la cuenta.
     */
    public Credito(String numeroCuenta, double saldo, int nip, double limiteEstablecido, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.CREDITO, saldo, nip, cliente);
        this.limiteEstablecido = limiteEstablecido;
    }

    /**
     * Obtiene el límite de crédito establecido para esta cuenta.
     *
     * @return El límite de crédito.
     */
    public double getLimiteEstablecido(){
        return limiteEstablecido;
    }

    /**
     * Establece un nuevo límite de crédito para esta cuenta.
     *
     * @param limiteEstablecido Nuevo límite de crédito.
     */
    public void setLimiteEstablecido(double limiteEstablecido){
        this.limiteEstablecido = limiteEstablecido;
    }

    /**
     * Muestra por consola el límite de crédito establecido.
     */
    public void mostrarLimite() {
        System.out.println("Límite de crédito establecido: $" + limiteEstablecido);
    }

    /**
     * Muestra información detallada de la cuenta, incluyendo tipo, número,
     * saldo actual, límite establecido y cliente asociado.
     * Método sobrescrito de la clase Cuenta.
     */
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de tipo CREDITO");
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
    public void mostrarTipoCuenta(){
        System.out.println("Credito");
    }
}
