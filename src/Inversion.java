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

    public Inversion() {}

    public Inversion(String numeroCuenta, double saldoInicial, double rendimientoMensual, int mesesInvertidos, Cliente cliente) {
        super(numeroCuenta, TipoCuenta.INVERSION, saldoInicial, cliente);
        this.saldoInicial = saldoInicial;
        this.rendimientoMensual = rendimientoMensual;
        this.mesesInvertidos = mesesInvertidos;
    }

    public double calcularGanancia() {
        double saldoFinal = saldoInicial * Math.pow(1 + (rendimientoMensual / 100), mesesInvertidos);
        return saldoFinal - saldoInicial;
    }

    public void mostrarGanancia() {
        double saldoFinal = saldoInicial * Math.pow(1 + (rendimientoMensual / 100), mesesInvertidos);
        double ganancia = saldoFinal - saldoInicial;
        double rentabilidad = (ganancia / saldoInicial) * 100;

        System.out.println("Saldo inicial: " + saldoInicial);
        System.out.println("Meses invertidos: " + mesesInvertidos);
        System.out.println("Saldo final estimado: " + saldoFinal);
        System.out.println("Ganancia: " + ganancia);
        System.out.println("Rentabilidad: " + rentabilidad);

    }

    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de tipo INVERSION");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo actual: $" + saldo);
        //System.out.println("Cliente: " + (cliente != null ? cliente.getNombre() : "No asignado"));
        mostrarGanancia();
    }
}
