import java.util.Scanner;

public abstract class Cuenta {
    public enum TipoCuenta {
        INVERSION,
        NOMINA,
        CREDITO
    }

    protected String numeroCuenta;
    protected TipoCuenta tipoCuenta;
    protected double saldo;
    private Cliente cliente;
    private int nip;

    public Cuenta(){}

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNip(){
        return nip;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setNip(int nip){
        this.nip = nip;
    }

    public void retirar() {
        if (!nipValido()){
            System.out.println(" ");
        }else{
            while (true) {
                Scanner entrada = new Scanner(System.in);
                System.out.println("Ingresa el monto a retirar: ");
                double monto = entrada.nextDouble();

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
    }

    public void depositar() {
        if (!nipValido()){
            System.out.println(" ");
        }else {
            Scanner entrada = new Scanner(System.in);
            while (true) {
                System.out.println("Ingresa el monto a depositar: ");
                double monto = entrada.nextDouble();

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
    }

    public void consultarSaldo() {
        System.out.println("Tu saldo actual es: " + saldo);
    }

    private boolean nipValido(){
        Scanner entrada = new Scanner(System.in);
        int numeroIntentos = 4;
        boolean resultado;

        while (numeroIntentos > 0) {
            System.out.print("Por favor ingrese su NIP: ");
            int nipIngresado = entrada.nextInt();

            if (nipIngresado == getNip()) {
                return true;
            } else {
                numeroIntentos--;
                if (numeroIntentos > 0) {
                    System.out.println("NIP incorrecto, te quedan " + numeroIntentos + " intentos.\n");
                } else {
                    System.out.println("Te quedaste sin intentos.");
                }
            }
        }

        return false;
    }

    public abstract void mostrarCuenta();
}