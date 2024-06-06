package Instruccion;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Imprimir
 */

    public class Imprimir{
        public static String imprimirValores(String id, String exp) {
            StringBuilder sb = new StringBuilder();
                    sb.append("----------------------------------\n").append(id).append("\n");
                    sb.append("----------------------------------\n"); 
                    String[] valores = exp.split(",");
                    for (String valor : valores) {
                        sb.append(valor).append("\n");
                    }
                    return sb.toString();
                }
            }
