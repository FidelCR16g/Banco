import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private String usuario;
    private String contrasenia;
    private String nombreC;
    private String ine;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String telefonoC;
    private String correoC;
    private List<Cuenta> cuentas;

    public Cliente() {}

    public Cliente(String nombreC, String ine, String fechaNacimiento, String lugarNacimiento, String telefonoC, String correoC) {
        this.nombreC = nombreC;
        this.ine = ine;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.telefonoC = telefonoC;
        this.correoC = correoC;
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
        int nip;
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
            System.out.println("Alcalzaste el numero maximo de intentos.\n");
        }
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