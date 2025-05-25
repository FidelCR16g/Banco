public class Cliente {
    private String nombreC;
    private String ine;
    private String fechaNacimiento;
    private String lugarNacimiento;
    private String telefonoC;
    private String correoC;
    private Cuenta cuenta;

    public Cliente() {}

    public Cliente(String nombreC, String ine, String fechaNacimiento, String lugarNacimiento, String telefonoC, String correoC, Cuenta cuenta) {
        this.nombreC = nombreC;
        this.ine = ine;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.telefonoC = telefonoC;
        this.correoC = correoC;
        this.cuenta = cuenta;
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

    public void mostrarDatosCliente() {
        System.out.println("CLIENTE");
        System.out.println("Nombre: " + getNombreC() + "\nINE: " + getIne() + "\nFecha de Nacimiento: " + getFechaNacimiento() + "\nLugar de Nacimiento: " + getLugarNacimiento() + "\nTeléfono: " +  getTelefonoC() + "\nCorreo: " + getCorreoC());
    }

    public void asignarCuenta(Cuenta cuenta) {

    }
}
