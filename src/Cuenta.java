import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase abstracta que representa una cuenta bancaria genérica.
 *
 * <p>Esta clase puede ser extendida por tipos específicos de cuentas como Inversión,
 * Nómina o Crédito. Contiene atributos comunes como número de cuenta, tipo de cuenta,
 * saldo, NIP, cliente asociado y una lista de movimientos registrados.</p>
 *
 * <p>Proporciona métodos para realizar depósitos, retiros, validar NIP, registrar movimientos
 * y consultar el saldo. También define métodos abstractos que deben ser implementados por
 * las subclases para mostrar detalles específicos de cada tipo de cuenta.</p>
 *
 * */
public abstract class Cuenta implements Serializable {

    /**
     * Enumeración que representa los tipos posibles de cuentas.
     */
    public enum TipoCuenta {
        INVERSION,
        NOMINA,
        CREDITO
    }

    protected String numeroCuenta;
    protected TipoCuenta tipoCuenta;
    protected double saldo;
    protected int nip;
    private Cliente cliente;
    private List<Movimientos> movimientos;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío de la clase Cuenta.
     */
    public Cuenta() {}

    /**
     * Constructor que inicializa todos los atributos de la cuenta.
     *
     * @param numeroCuenta número de cuenta
     * @param tipoCuenta tipo de la cuenta (INVERSION, NOMINA, CREDITO)
     * @param saldo saldo inicial
     * @param nip clave personal de acceso
     * @param cliente cliente asociado a la cuenta
     */
    public Cuenta(String numeroCuenta, TipoCuenta tipoCuenta, double saldo, int nip, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.nip = nip;
        this.cliente = cliente;
        this.movimientos = new ArrayList<>();
    }

    // Métodos getters

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    protected int getNip() {
        return nip;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Movimientos> getMovimientos() {
        return movimientos;
    }

    // Métodos setters

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMovimientos(List<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }

    /**
     * Aumenta el saldo de la cuenta en el monto especificado.
     *
     * @param monto cantidad a incrementar
     */
    public void aumentarSaldo(double monto) {
        this.saldo += monto;
    }

    /**
     * Disminuye el saldo de la cuenta si hay suficiente saldo disponible.
     *
     * @param monto cantidad a disminuir
     * @return true si se pudo disminuir, false si no había suficiente saldo
     */
    public boolean disminuirSaldo(double monto) {
        if (this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    /**
     * Registra un movimiento manualmente en la lista de movimientos.
     *
     * @param tipo tipo de operación (RETIRAR o DEPOSITAR)
     * @param monto monto de la operación
     * @param concepto descripción del movimiento
     */
    public void registrarMovimiento(Movimientos.TipoOperacion tipo, double monto, String concepto) {
        String fechaHora = new Date().toString();
        Movimientos movimiento = new Movimientos(tipo, monto, fechaHora,
                tipo == Movimientos.TipoOperacion.RETIRAR ? this : null,
                tipo == Movimientos.TipoOperacion.DEPOSITAR ? this : null,
                concepto);
        movimientos.add(movimiento);
        movimiento.procesarTicket();
    }

    /**
     * Valida el NIP del usuario con un máximo de 4 intentos.
     *
     * @return true si el NIP ingresado es correcto, false si se agotan los intentos
     */
    protected boolean nipValido() {
        Scanner entrada = new Scanner(System.in);
        int numeroIntentos = 4;

        while (numeroIntentos > 0) {
            System.out.print("Por favor ingrese su NIP: ");
            int nipIngresado = entrada.nextInt();

            if (nipIngresado == (getNip())) {
                return true;
            } else {
                numeroIntentos--;
                if (numeroIntentos > 0) {
                    System.out.println("NIP incorrecto, te quedan " + numeroIntentos + " intentos.\n");
                } else {
                    System.out.println("Te quedaste sin intentos.");
                }
            }
        }

        return false;
    }

    /**
     * Muestra el saldo actual de la cuenta.
     */
    public void consultarSaldo() {
        System.out.println("Tu saldo actual es: " + getSaldo());
    }

    public static void guardarClientes(String archivo, List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(clientes);
            System.out.println("Datos guardados exitosamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> cargarClientes(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(archivo))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return new ArrayList<>(); // Retorna lista vacía si hay error
        }
    }

    /**
     * Permite retirar dinero de la cuenta después de validar el NIP.
     * Verifica que el monto sea positivo, no supere $9000 y que haya saldo suficiente.
     * Registra el movimiento si es exitoso.
     */
    public abstract void retirar(double monto);

    /**
     * Permite depositar dinero en la cuenta después de validar el NIP.
     * Verifica que el monto sea positivo. Registra el movimiento si es exitoso.
     */
    public abstract void depositar(double monto);

    /**
     * Método abstracto que debe mostrar la información específica de la cuenta.
     * Debe ser implementado por las subclases.
     */
    public abstract void mostrarCuenta();

    /**
     * Método abstracto que debe mostrar el tipo de cuenta.
     * Debe ser implementado por las subclases.
     */
    public abstract void mostrarTipoCuenta();
}
