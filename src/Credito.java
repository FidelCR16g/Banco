public class Credito extends Cuenta {
    private String limiteEstablecido;

    public Credito() {}

    public Credito(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, int nip, Cliente cliente, String limiteEstablecido) {
        super(numeroCuenta, TipoCuenta.CREDITO, saldo, nip, cliente);
        this.limiteEstablecido = limiteEstablecido;
    }

    public void mostrarLimite() {

    }

    public void realizarPagoAMeses() {

    }

    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Crédito" + "\nNúmero de cuenta: " + numeroCuenta + "\nSaldo: " + saldo + "\nLímite establecido: " + limiteEstablecido);
    }
}
