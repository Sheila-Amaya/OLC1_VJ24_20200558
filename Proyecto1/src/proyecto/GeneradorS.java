/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto;
import java.io.File;
/**
 *
 * @author amaya
 */
public class GeneradorS {
    public static void main(String[] args) {
        String[] opciones = new String[7]; // Arreglo de opciones

        // Seleccionamos la opción de dirección de destino
        opciones[0] = "-destdir";

        // Le damos la dirección, carpeta donde se va a generar el parser.java & el simbolosxxx.java
        String destDir = "src" + File.separator + "Analizadores";
        opciones[1] = destDir;

        // Seleccionamos la opción de nombre de archivo símbolos
        opciones[2] = "-symbols";

        // Le damos el nombre que queremos que tenga
        opciones[3] = "sym";

        // Seleccionamos la opción de clase parser
        opciones[4] = "-parser";

        // Le damos nombre a esa clase del paso anterior
        opciones[5] = "Sintactico";

        // Le decimos donde se encuentra el archivo .cup
        String parserPath = destDir + File.separator + "Parser.cup";
        opciones[6] = parserPath;

        // Verificar si el archivo .cup existe antes de intentar generar el parser
        File parserFile = new File(parserPath);
        if (parserFile.exists()) {
            try {
                java_cup.Main.main(opciones);
                System.out.println("Parser generado exitosamente.");
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Error al generar el parser: " + ex.getMessage());
            }
        } else {
            System.err.println("El archivo .cup especificado no existe: " + parserFile.getAbsolutePath());
        }
    }
}