/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto;

import java.io.File;

/**
 *
 * @author amaya
 */
public class GeneradorL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Definir la ruta relativa al archivo .jflex
        String path = "src" + File.separator + "Analizadores" + File.separator + "Scanner.jflex";
        File file = new File(path);

        // Verificar si el archivo existe antes de intentar generar el lexer
        if (file.exists()) {
            try {
                // Generar el lexer con JFlex
                jflex.Main.generate(file);
                System.out.println("Lexer generado exitosamente.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al generar el lexer: " + e.getMessage());
            }
        } else {
            System.err.println("El archivo .jflex especificado no existe: " + file.getAbsolutePath());
        }
    }
}