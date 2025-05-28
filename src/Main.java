public class Main {
    public static void main(String[] args) {

        Cliente cliente = new Cliente("Ana Georgina", "COEUDJ56464", "15/07/2004", "Carmen", "9381194597", "anarosorio@hotmail.com", "ANA15", "1234");
        Inversion inversion = new Inversion("INV001", 10000.0, 1253, 2.0, 12, cliente);

        cliente.asignarCuenta(inversion);
        inversion.mostrarCuenta();

    }
}