import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = LectorDatos.cargarClientesDesdeArchivo("C:\\Users\\fave6\\OneDrive\\Documentos\\Fidel Escuela\\Codigos POO\\Banco\\src\\datos.txt");
        iniciarSesion(clientes);
    }

    public static void iniciarSesion(List<Cliente> clientes){
        Scanner entrada = new Scanner(System.in);
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
        }else {
            clienteActual.mostrarDatosCliente();
        }
    }
}