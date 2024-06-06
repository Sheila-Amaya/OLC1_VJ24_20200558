package Instruccion;

import java.util.HashMap;

/**
 * Var
 */
public class Variable {

    public static HashMap<String, String> variables = new HashMap<>(); 

    public static void asignar(String nombre, String valor) {
        variables.put(nombre, valor);
    }

    public static String obtener(String nombre) {
        return variables.get(nombre);
    }
    
}
