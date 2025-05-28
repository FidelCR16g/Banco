import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<Cliente> clientes = new ArrayList<>();

        Cliente cliente1 = new Cliente("Ana Pérez", "INE123", "1234567890", "ana@mail.com", "ana123", "clave1");
        Cliente cliente2 = new Cliente("Luis Gómez", "INE456", "0987654321", "luis@mail.com", "luis456", "clave2");

        clientes.add(cliente1);
        clientes.add(cliente2);

        Inversion cuenta1 = new Inversion("12345", 1000, 123, 2.5, 4, cliente1);
        Nomina cuenta2 = new Nomina("123456", Cuenta.TipoCuenta.NOMINA, 500, 456, cliente2, "Ana", "Xalapa", 1200);

        Movimientos movimientos = new Movimientos(Movimientos.TipoOperacion.TRANSFERIR, 1000, "2025-05-28 10:00:00", cuenta1, cuenta2, "Pago de renta", null);

        movimientos.transferir();

        int numeroIntentos = 4;
        boolean sesionExitosa = false;
        Cliente clienteActual = null;

        while (numeroIntentos > 0 && !sesionExitosa) {
            System.out.print("Usuario: ");
            String usuarioIngresado = entrada.nextLine();

            System.out.print("Contraseña: ");
            String contraseniaIngresada = entrada.nextLine();

            for (Cliente cliente : clientes) {
                if (cliente.sesionValida(usuarioIngresado, contraseniaIngresada)) {
                    System.out.println("Bienvenido " + cliente.getNombreC() + "!");
                    sesionExitosa = true;
                    clienteActual = cliente;
                    break;
                }
            }

            if (!sesionExitosa) {
                numeroIntentos--;
                System.out.println("Datos incorrectos. Te quedan " + numeroIntentos + " intentos.\n");
            }
        }

        if (!sesionExitosa) {
            System.out.println("Has alcanzado el número máximo de intentos.");
        } else {
            clienteActual.mostrarDatosCliente();
        }

        entrada.close();


    }
}