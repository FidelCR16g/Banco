import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private String nombreC;
    private String ine;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String telefonoC;
    private String correoC;
    private String usuario;
    private String contrasenia;
    private List<Cuenta> cuentas;

    public Cliente() {}

    public Cliente(String nombreC, String ine, String fechaNacimiento, String lugarNacimiento, String telefonoC, String correoC, String usuario, String contrasenia) {
        this.nombreC = nombreC;
        this.ine = ine;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.telefonoC = telefonoC;
        this.correoC = correoC;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.cuentas = new ArrayList<>();
    }

    public String getUsuario(){
        return usuario;
    }

    public String getContrasenia(){
        return contrasenia;
    }

    public String getNombreC() {
        return nombreC;
    }

    public String getIne() {
        return ine;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public String getTelefonoC() {
        return telefonoC;
    }

    public String getCorreoC() {
        return correoC;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void iniciarSesion(){
        Scanner entrada = new Scanner(System.in);
        String usuario;
        String contrasenia;
        int numeroIntentos = 4;

        while (numeroIntentos > 0) {
            System.out.println("INICIAR SESION.");

            System.out.print("Usuario: ");
            usuario = entrada.nextLine();

            System.out.print("Contraseña: ");
            contrasenia = entrada.nextLine();

            if (!usuario.equals(getUsuario()) ||
                    !contrasenia.equals(getContrasenia())) {
                System.out.println("Por favor ingresa los datos correctos, te quedan " + (numeroIntentos - 1) + " intentos.\n");
                numeroIntentos--;
            } else {
                System.out.println("Bienvenido " + usuario + "!\n");
                break;
            }
        }

        if (numeroIntentos == 0){
            System.out.println("Alcanzaste el número máximo de intentos.\n");
        }
    }

    public void seleccionarCuenta() {
        if (cuentas.isEmpty()) {
            System.out.println("No tienes cuentas registradas.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecciona una cuenta:");
        for (int i = 0; i < cuentas.size(); i++) {
            Cuenta cuenta = cuentas.get(i);
            System.out.println((i + 1) + ". Número: " + cuenta.numeroCuenta +
                    ", Tipo: " + cuenta.tipoCuenta +
                    ", Saldo: $" + cuenta.saldo);
        }

        int opcion = -1;
        while (opcion < 1 || opcion > cuentas.size()) {
            System.out.print("Ingresa el número de la cuenta: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > cuentas.size()) {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Intenta de nuevo.");
                scanner.next(); // limpia entrada inválida
            }
        }

        Cuenta cuentaSeleccionada = cuentas.get(opcion - 1);
        System.out.println("Has seleccionado la cuenta número: " + cuentaSeleccionada.numeroCuenta);
        cuentaSeleccionada.mostrarCuenta(); // Opcional: muestra los detalles de la cuenta
    }


    public void mostrarDatosCliente() {
        System.out.println("CLIENTE");
        System.out.println("Nombre: " + getNombreC() +
                "\nINE: " + getIne() +
                "\nFecha de Nacimiento: " + getFechaNacimiento() +
                "\nLugar de Nacimiento: " + getLugarNacimiento() +
                "\nTeléfono: " +  getTelefonoC() +
                "\nCorreo: " + getCorreoC());
    }

    public void asignarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }
}