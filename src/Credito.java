public abstract class Credito extends Cuenta {
    private String limiteEstablecido;

    public Credito() {}

    public Credito(String numeroCuenta, double saldo, String limiteEstablecido) {
        super(numeroCuenta, TipoCuenta.CREDITO, saldo);
        this.limiteEstablecido = limiteEstablecido;
    }

    public void mostrarLimite() {

    }

    public void realizarPagoAMeses() {

    }

    @Override
    public void mostrarCuenta() {

    }

    @Override
    public void consultarSaldo() {

    }
}
