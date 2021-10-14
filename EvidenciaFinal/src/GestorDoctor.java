import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public final class GestorDoctor {

    private ArrayList<Doctor> doctores = new ArrayList();

    public void crearCSV() {

        File archivo = new File("C:\\Users\\Emeth.Paredes\\IdeaProjects\\EvidenciaFinal\\ArchivoCSV\\");

        try {
            //Si no existe archivo, intentamos crearlo
            boolean primeraVez = false;
            if (!archivo.exists()) {
                File carpeta = archivo.getParentFile();
                carpeta.mkdirs();
                archivo.createNewFile();
                primeraVez = true; //Primera vez que trabajamos en este archivo
            }

            FileWriter escritor = new FileWriter(archivo, true);
            //Cabecera para datos del CSV, solo si es la primera vez
            if (primeraVez)
                escritor.append("#ID;Nombre;Apellidos;Especialidad\n");

            //Datos del último Doctor registrado
            int ultimo = doctores.size() - 1;
            escritor.append(doctores.get(ultimo).generaLineaCSV());//Insertamos linea CSV

            escritor.close();
        } catch (IOException e) {
            System.out.println("Error de acceso a: " + archivo.getAbsolutePath());
        }
    }

    public void nuevoDoctor() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nALTA NUEVO DOCTOR");
        System.out.println("---- ----- ------\n");
        System.out.print("ID: ");
        String id = teclado.nextLine();
        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Apellidos: ");
        String apell = teclado.nextLine();
        System.out.print("Especialidad: ");
        String espe = teclado.nextLine();

        doctores.add(new Doctor(id, nombre, apell, espe));
        crearCSV(); //Añadimos nueva línea CSV al archivo en disco
    }

    public Doctor getDoctor(String id) {
        for (Doctor doc: doctores)
            if (doc.getId().equals(id))
                return doc; //Doctor encontrado
        //Si el bucle no ha retornado ningún Doctor, es que no existe ese ID
        return null;
    }
}