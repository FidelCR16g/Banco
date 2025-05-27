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

            if (monto < 0) {
                System.out.println("El monto a retirar debe ser una cantidad positiva");
            } else if (monto > 9000) {
                System.out.println("No puedes retirar una cantidad mayor a 9000");
            } else if (saldo < monto) {
                System.out.println("Saldo insuficiente. Tu saldo es: " + saldo);
            } else {
                saldo -= monto;
                break;
            }
        }
    }

    public void depositar() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingresa el monto a depositar: ");
            double monto = scanner.nextDouble();

            if (monto < 0) {
                System.out.println("El monto a depositar debe ser una cantidad positiva");
            } else if (saldo < monto) {
                System.out.println("Saldo insuficiente. Tu saldo es: " + saldo);
            } else {
                saldo += monto;
                break;
            }
        }
    }

    public void consultarSaldo() {
        System.out.println("Tu saldo actual es: " + saldo);
    }

    public abstract void mostrarCuenta();
}