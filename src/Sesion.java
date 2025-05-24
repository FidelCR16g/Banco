import java.util.Scanner;

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
        Scanner entrada = new Scanner(System.in);
        String usuario;
        String contrasenia;
        int nip;

        while (true) {
            System.out.println("INICIAR SESION.");

            System.out.print("Usuario: ");
            usuario = entrada.nextLine();

            System.out.print("Contraseña: ");
            contrasenia = entrada.nextLine();

            System.out.print("NIP: ");
            nip = entrada.nextInt();
            entrada.nextLine();

            if (!usuario.equals(getUsuario()) ||
                    !contrasenia.equals(getContrasenia()) ||
                    nip != getNip()) {
                System.out.println("Por favor ingresa los datos correctos.\n");
            } else {
                System.out.println("Bienvenido " + usuario + "!\n");
                break;
            }
        }
    }
}
