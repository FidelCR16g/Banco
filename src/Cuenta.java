import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    protected int nip;
    private Cliente cliente;
    private List<Movimientos> movimientos;

    public Cuenta(){}

    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, int nip, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.nip = nip;
        this.cliente = cliente;
        this.movimientos = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoCuenta getTipoCuenta(){
        return tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    protected int getNip(){
        return nip;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Movimientos> getMovimientos() {
        return movimientos;
    }

    public void setNumeroCuenta(String numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta){
        this.tipoCuenta = tipoCuenta;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public void setNip(int nip){
        this.nip = nip;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMovimientos(List<Movimientos> movimientos){
        this.movimientos = movimientos;
    }

    public void aumentarSaldo(double monto) {
        this.saldo += monto;
    }

    public boolean disminuirSaldo(double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    public void retirar() {

        if (nipValido()){
            Scanner scanner = new Scanner(System.in);
            String fechaHora = new Date().toString();

            while (true) {
                System.out.println("Ingresa el monto a retirar: ");
                double monto = scanner.nextDouble();

                if (monto < 0) {
                    System.out.println("El monto a retirar debe ser una cantidad positiva");
                } else if (monto > 9000) {
                    System.out.println("No puedes retirar una cantidad mayor a 9000");
                } else if (saldo < monto) {
                    System.out.println("Saldo insuficiente. Tu saldo es: " + getSaldo());
                } else {
                    saldo -= monto;
                    Movimientos retiro = new Movimientos(
                            Movimientos.TipoOperacion.RETIRAR,
                            monto,
                            fechaHora,
                            this,
                            null,
                            "Retiro en efectivo"
                    );

                    this.getMovimientos().add(retiro);
                    retiro.procesarTicket();
                    System.out.println("Retiro exitoso");
                    break;
                }
            }
        }else{
            System.out.println(" ");
        }
    }

    public void depositar() {
        if (nipValido()){
            Scanner scanner = new Scanner(System.in);
            String fechaHora = new Date().toString();

            while (true) {
                System.out.println("Ingresa el monto a depositar: ");
                double monto = scanner.nextDouble();

                if (monto < 0) {
                    System.out.println("El monto a depositar debe ser una cantidad positiva");
                } else if (saldo < monto) {
                    System.out.println("Saldo insuficiente. Tu saldo es: " + getSaldo());
                } else {
                    saldo += monto;
                    Movimientos deposito = new Movimientos(
                            Movimientos.TipoOperacion.DEPOSITAR,
                            monto,
                            fechaHora,
                            null,
                            this,
                            "Depósito en efectivo"
                    );

                    this.getMovimientos().add(deposito);
                    deposito.procesarTicket();
                    System.out.println("Depósito exitoso");
                    break;
                }
            }
        }else{
            System.out.println(" ");
        }
    }

    public void registrarMovimiento(Movimientos.TipoOperacion tipo, double monto, String concepto) {
        String fechaHora = new Date().toString();
        Movimientos movimiento = new Movimientos(tipo, monto, fechaHora,
                tipo == Movimientos.TipoOperacion.RETIRAR ? this : null,
                tipo == Movimientos.TipoOperacion.DEPOSITAR ? this : null,
                concepto);
        movimientos.add(movimiento);
        movimiento.procesarTicket();
    }

    protected boolean nipValido() {
        Scanner entrada = new Scanner(System.in);
        int numeroIntentos = 4;

        while (numeroIntentos > 0) {
            System.out.print("Por favor ingrese su NIP: ");
            int nipIngresado = entrada.nextInt();

            if (nipIngresado == (getNip())) {
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


    public void consultarSaldo() {
        System.out.println("Tu saldo actual es: " + getSaldo());
    }

    public abstract void mostrarCuenta();

    public abstract void mostrarTipoCuenta();
}