/*
Esta clase llamada Inversion hereda de la clase Cuenta, la cual tiene como atributos:
ganancia, rendimientoMensual, saldoInicial, mesesInvertidos.
Se colocan ambos constructores, uno con parámetros y otro sin parámetros. En donde, sus
métodos propios son calcularGanancia() y mostrarGanancia().
Se sobreecribe la clase mostrarCuenta() de la clase Cuenta.
*/

public class Inversion extends Cuenta {
    private double ganancia;
    private double rendimientoMensual; // en porcentaje (2.5 significa 2.5%)
    private double saldoInicial;
    private int mesesInvertidos;

    //Constructor vacío
    public Inversion() {}

    //Constructor con parámetros
    public Inversion(String numeroCuenta, double saldoInicial, int nip, double rendimientoMensual, int mesesInvertidos, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.INVERSION, saldoInicial, nip, cliente);
        this.saldoInicial = saldoInicial;
        this.rendimientoMensual = rendimientoMensual;
        this.mesesInvertidos = mesesInvertidos;
    }

    public double getGanancia(){
        return ganancia;
    }

    public double getRendimientoMensual(){
        return rendimientoMensual;
    }

    public double getSaldoInicial(){
        return saldoInicial;
    }

    public int getMesesInvertidos(){
        return mesesInvertidos;
    }

    public void setGanancia(double ganancia){
        this.ganancia = ganancia;
    }

    public void setRendimientoMensual(double rendimientoMensual){
        this.rendimientoMensual = rendimientoMensual;
    }

    public void setSaldoInicial(double saldoInicial){
        this.saldoInicial = saldoInicial;
    }

    public void setMesesInvertidos(int mesesInvertidos){
        this.mesesInvertidos = mesesInvertidos;
    }

    //Métodos propios
    public double calcularGanancia() {
        double saldoFinal = getSaldoInicial() * Math.pow(1 + (getRendimientoMensual() / 100), getMesesInvertidos());
        //La función Math.pow(a, b) eleva un número 'a' a la potencia 'b'
        return saldoFinal - saldoInicial;
    }

    public void mostrarGanancia() {
        double saldoFinal = saldoInicial * Math.pow(1 + (rendimientoMensual / 100), mesesInvertidos);
        ganancia = saldoFinal - saldoInicial;

        System.out.printf("Saldo inicial: $%.2f\n", getSaldoInicial());
        System.out.println("Meses invertidos: " + getMesesInvertidos());
        System.out.printf("Saldo final estimado: $%.2f\n", saldoFinal);
        System.out.printf("Ganancia: $%.2f\n", getGanancia());

    }

    //Metodo sobreescrito
    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de tipo INVERSION");
        System.out.println("Número de cuenta: " + getNumeroCuenta());
        System.out.println("Saldo actual: $" + getSaldo());
        System.out.println("Cliente: " + (getCliente() != null ? getCliente().getNombreC() : "No hay un cliente."));
        mostrarGanancia();
    }

    @Override
    public void mostrarTipoCuenta(){
        System.out.println("Inversion");
    }
}