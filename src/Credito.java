/*
Esta clase llamada Credito hereda de la clase Cuenta, la cual tiene como atributos:
limiteEstablecido,
Se colocan ambos constructores, uno con parámetros y otro sin parámetros. En donde, sus
métodos propios son

realizarPagoAMeses() y mostrarLimite()

Se sobreecribe la clase mostrarCuenta() de la clase Cuenta.
*/

public class Credito extends Cuenta {
    private double limiteEstablecido;

    //Constructor vacío
    public Credito() {}

    //Constructor con parámetros
    public Credito(String numeroCuenta, double saldo, int nip, double limiteEstablecido, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.CREDITO, saldo, nip, cliente);
        this.limiteEstablecido = limiteEstablecido;
    }

    public double getLimiteEstablecido(){
        return limiteEstablecido;
    }

    public void setLimiteEstablecido(double limiteEstablecido){
        this.limiteEstablecido = limiteEstablecido;
    }

    //Métodos propios
    public void mostrarLimite() {
        System.out.println("Límite de crédito establecido: $" + limiteEstablecido);

    }

    public void realizarPagoAMeses() {

    }

    //Método sobreescrito
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de tipo CREDITO");
        System.out.println("Número de cuenta: " + getNumeroCuenta());
        System.out.println("Saldo actual: $" + getSaldo());
        System.out.println("Saldo a deber: ");
        System.out.println("Límite establecido: $" + getLimiteEstablecido());
        System.out.println("Cliente: " + getCliente().getNombreC());
    }

    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Credito");
    }
}
