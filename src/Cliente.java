import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    private String nombreC;
    private String ine;
    private String telefonoC;
    private String correoC;
    private String usuario;
    private String contrasenia;
    private List<Cuenta> cuentas;

    public Cliente() {}

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

    public boolean sesionValida(String usuarioIngresado, String contraseniaIngresada){
        if (!usuarioIngresado.equals(getUsuario()) || !contraseniaIngresada.equals(getContrasenia())) {
            return false;
        } else {
            return true;
        }
    }

    public void mostrarCuentas() {

    }


    public void mostrarDatosCliente() {
        System.out.println("CLIENTE");
        System.out.println("Nombre: " + getNombreC() +
                "\nINE: " + getIne() +
                "\nTeléfono: " +  getTelefonoC() +
                "\nCorreo: " + getCorreoC());
    }

    public void asignarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }
}