package Instruccion;

import java.util.LinkedList;

public class Aritmetica {

    public static LinkedList<Object> aritmetica = new LinkedList<>();

    public static String realizarOperacion(String entrada, String operacion) {
        String[] numeros = entrada.split(",");
        if (numeros.length == 2) {
            double a = Double.parseDouble(numeros[0]);
            double b = Double.parseDouble(numeros[1]);
            double result = 0;
    
            switch (operacion) {
                                case "SUM":
                                    result = a + b;
                                    break;
                                case "RES":
                                    result = a - b;
                                    break;
                                case "MUL":
                                    result = a * b;
                                    break;
                                case "DIV":
                                    if (b != 0) {
                                        result = a / b;
                                    }else{
                                        return "Error: No se permite la división por cero.";
                                    }
                                    break;
                                case "MOD":
                                    if (b != 0) {
                                        result = a % b;
                                    }else{
                                        return "Error: No se permite la operación módulo por cero.";
                                    }
                                    break;
                            }
                    
                            return String.valueOf(result);
                        }
                    
                        return "entrada incorrecta";
                    }
                    
}