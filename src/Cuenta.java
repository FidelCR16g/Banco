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

    public Cuenta(){}

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    }

    public void retirar() {
        Scanner scanner = new Scanner(System.in);
        double monto;

        while (true) {
            System.out.println("Ingresa el monto a retirar: ");
            monto = scanner.nextDouble();

            if (saldo < monto) {
                System.out.println("Saldo insuficiente. Tu saldo es: " + saldo);
            } else {
                saldo -= monto;
                System.out.println("Tu saldo actual es " + saldo);
                break;
            }
        }
    }

    public void depositar(double monto) {
        saldo += monto;
        System.out.println("Tu saldo actual es " + saldo);
    }

    public abstract void mostrarCuenta();

    public abstract void consultarSaldo();
}
