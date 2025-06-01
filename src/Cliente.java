import java.util.ArrayList;
import java.util.List;

/**
 * <p>Clase que representa a un cliente del sistema bancario.</p>
 *
 * <p>Contiene información personal como nombre, INE, teléfono, correo, usuario y contraseña.
 * Además, mantiene una lista de cuentas asociadas al cliente.</p>
 *
 * <p>Proporciona métodos para validar sesión, mostrar cuentas, asignar cuentas
 * y mostrar los datos del cliente.</p>
 */
public class Cliente {
    private String nombreC;
    private String ine;
    private String telefonoC;
    private String correoC;
    private String usuario;
    private String contrasenia;
    private List<Cuenta> cuentas;

    /**
     * Constructor por defecto.
     */
    public Cliente() {}

    /**
     * Constructor que inicializa los datos del cliente.
     *
     * @param nombreC      Nombre del cliente.
     * @param ine          Identificación oficial (INE).
     * @param telefonoC    Teléfono del cliente.
     * @param correoC      Correo electrónico del cliente.
     * @param usuario      Nombre de usuario.
     * @param contrasenia  Contraseña del cliente.
     */
    public Cliente(String nombreC, String ine, String telefonoC, String correoC, String usuario, String contrasenia) {
        this.nombreC = nombreC;
        this.ine = ine;
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

    public String getTelefonoC() {
        return telefonoC;
    }

    public String getCorreoC() {
        return correoC;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * Verifica si las credenciales ingresadas son válidas.
     *
     * @param usuarioIngresado     Usuario ingresado.
     * @param contraseniaIngresada Contraseña ingresada.
     * @return true si las credenciales coinciden, false en caso contrario.
     */
    public boolean sesionValida(String usuarioIngresado, String contraseniaIngresada){
        return usuarioIngresado.equals(getUsuario()) && contraseniaIngresada.equals(getContrasenia());
    }

    /**
     * Muestra la lista de cuentas asociadas al cliente.
     */
    public void mostrarCuentas() {
        int i = 1;
        for (Cuenta cuenta: cuentas){
            System.out.print(i + ". ");
            cuenta.mostrarTipoCuenta();
            i++;
        }
    }

    /**
     * Muestra los datos personales del cliente.
     */
    public void mostrarDatosCliente() {
        System.out.println("CLIENTE");
        System.out.println("Nombre: " + getNombreC() +
                "\nINE: " + getIne() +
                "\nTeléfono: " +  getTelefonoC() +
                "\nCorreo: " + getCorreoC());
    }

    /**
     * Asocia una cuenta al cliente.
     *
     * @param cuenta La cuenta que se va a asignar.
     */
    public void asignarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }
}
