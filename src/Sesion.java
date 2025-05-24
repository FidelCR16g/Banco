public class Sesion {
    private String usuario;
    private String contrasenia;
    private int nip;

    public Sesion(){}

    public Sesion(String usuario, String contrasenia, int nip){
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nip = nip;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getContrasenia(){
        return contrasenia;
    }
    public int getNip(){
        return nip;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }

    public void setNip(int nip){
        this.nip = nip;
    }

    public void iniciarSesion(){

    }
}
