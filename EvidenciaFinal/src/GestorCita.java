import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GestorCita {

    private ArrayList<Cita> citas = new ArrayList();

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
                escritor.append("#id;fecha;hora;motivo;doctor;paciente\n");

            //Datos del último Cita registrada
            int ultimo = citas.size() - 1;
            escritor.append(citas.get(ultimo).generaLineaCSV());//Insertamos linea CSV

            escritor.close();
        } catch (IOException e) {
            System.out.println("Error de acceso a: " + archivo.getAbsolutePath());
        }
    }

    //Referencias a las instancias de los gestores de doctores y pacientes
    private GestorDoctor doctores;
    private GestorPacientes pacientes;

    public GestorCita(GestorDoctor gestDoc, GestorPacientes gestPaci) {
        citas = new ArrayList<Cita>();
        doctores = gestDoc;
        pacientes = gestPaci;
    }

    public boolean nuevoCita() {
        String id = JOptionPane.showInputDialog(null, "Introduzca Identificador:", "Nuevo Cita", JOptionPane.QUESTION_MESSAGE);
        String fecha = JOptionPane.showInputDialog(null, "Fecha:", "Nuevo Fecha", JOptionPane.QUESTION_MESSAGE);
        String hora = JOptionPane.showInputDialog(null, "Hora:", "Nuevo Hora", JOptionPane.QUESTION_MESSAGE);
        String motivo = JOptionPane.showInputDialog(null, "Motivo:", "Nuevo Motivo", JOptionPane.QUESTION_MESSAGE);
        String iDdoctor = JOptionPane.showInputDialog(null, "ID del Doctor:", "Nuevo Doctor", JOptionPane.QUESTION_MESSAGE);
        String iDpaciente = JOptionPane.showInputDialog(null, "ID del Paciente:", "Nuevo Paciente", JOptionPane.QUESTION_MESSAGE);

        //Pedimos los datos a los gestores que tenemos referenciados
        Doctor doctor = doctores.getDoctor(iDdoctor);
        Paciente paciente = pacientes.getPaciente(iDpaciente);

        Cita nuevoCita = new Cita(id, fecha, hora, motivo, doctor, paciente);
        return citas.add(nuevoCita);
        //Devuelve TRUE si se insertó correctamente, FALSE si no se pudo insertar
    }

}