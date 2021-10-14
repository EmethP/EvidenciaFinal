import java.util.Scanner;
import javax.swing.JOptionPane;

public class Paciente {

    private String id;
    private String nombre;
    private String apellidos;

    public String generaLineaCSV() {
        return String.format("%s;%s;%s;%s;%s;%s\n", id, nombre, apellidos);
    }

    public Paciente(String id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Muestra por panel todos los datos del doctor
     */
    public void mostrar() {
        String mensaje = "ID paciente: " + id + "\nNombre: " + nombre
                + "\nApellidos: " + apellidos;
        JOptionPane.showMessageDialog(null, mensaje, "Mostrar paciente", JOptionPane.INFORMATION_MESSAGE);
    }

}