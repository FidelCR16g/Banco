import java.util.Scanner;

public abstract class Cuenta {
    public enum TipoCuenta {
        INVERSION,
        NOMINA,
        CORRIENTE,
        CREDITO
    }

    protected String numeroCuenta;
    protected TipoCuenta tipoCuenta;
    protected double saldo;
    private Cliente cliente;

    public Cuenta(){}

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void retirar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingresa el monto a retirar: ");
            double monto = scanner.nextDouble();

            if (saldo < monto) {
                System.out.println("Saldo insuficiente. Tu saldo es: " + saldo);
            } else {
                saldo -= monto;
                System.out.println("Tu saldo actual es " + saldo);
                break;
            }
        }
    }

    public void depositar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el monto a depositar: ");
        double monto = scanner.nextDouble();

        saldo += monto;
        System.out.println("Tu saldo actual es " + saldo);
    }

    public abstract void mostrarCuenta();

    public abstract void consultarSaldo();
}