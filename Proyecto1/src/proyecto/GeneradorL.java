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
        jflex.Main.generate(
                new File(
                        "../../Proyecto1/src/Analizadores/Scanner.jflex"));
        //"../Proyecto1/src/Analizadores/Scanner.jflex" para hacer el jar
    }
}
