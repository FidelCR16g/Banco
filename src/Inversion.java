public class Inversion extends Cuenta {
    private double ganancia;
    private double rendimientoMensual;

    public Inversion() {}

    public Inversion(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, Cliente cliente, double ganancia, double rendimientoMensual) {
        super(numeroCuenta, TipoCuenta.INVERSION, saldo, cliente);
        this.ganancia = ganancia;
        this.rendimientoMensual = rendimientoMensual;
    }

    public void mostrarGanancia() {

    }

    @Override
    public void mostrarCuenta() {
        System.out.println("Cuenta de Inversión" + "\nNúmero de cuenta: " + numeroCuenta + "\nSaldo: " + saldo + "\nGanancia: " + ganancia + "\nRendimiento mensual: " + rendimientoMensual);
    }

    @Override
    public void consultarSaldo() {

    }
}
