package Instruccion;

import java.util.*;

public class Estadistica {
    public static String operacionEstadistica(String entrada, String operacion) {
        String[] numerosString = entrada.split(",");
        double[] numeros = new double[numerosString.length];
        for (int i = 0; i < numerosString.length; i++) {
            numeros[i] = Double.parseDouble(numerosString[i]);
        }

        switch (operacion) {
            case "media":
                return String.valueOf(media(numeros));
            case "mediana":
                return String.valueOf(mediana(numeros));
            case "moda":
                return String.valueOf(moda(numeros));
            case "varianza":
                return String.valueOf(varianza(numeros));
            case "max":
                return String.valueOf(max(numeros));
            case "min":
                return String.valueOf(min(numeros));
            default:
                return "Operación no reconocida";
        }
    }

    public static String media(double[] numeros) {
        double sum = 0.0;
        for (double num : numeros) {
            sum += num;
        }
        return String.valueOf(sum / numeros.length);
    }

    public static String mediana(double[] numeros) {
        Arrays.sort(numeros);
        double mediana;
        int middle = numeros.length / 2;
        if (numeros.length % 2 == 0) {
            mediana = (numeros[middle] + numeros[middle - 1]) / 2;
        } else {
            mediana = numeros[middle];
        }
        return String.valueOf(mediana);
    }

    public static String moda(double[] numeros) {
        Map<Double, Integer> numCounts = new HashMap<>();
        for (double num : numeros) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }

        double maxKey = 0;
        int maxCount = 0;

        for (Map.Entry<Double, Integer> entry : numCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxKey = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return String.valueOf(maxKey);
    }

    public static String varianza(double[] numeros) {
        double media = Double.parseDouble(media(numeros));
        double sum = 0.0;
        for (double num : numeros) {
            sum += Math.pow(num - media, 2);
        }
        return String.format("%.2f", sum / (numeros.length - 1));
    }

    public static String max(double[] numeros) {
        double max = numeros[0];
        for (double num : numeros) {
            if (num > max) {
                max = num;
            }
        }
        return String.valueOf(max);
    }

    public static String min(double[] numeros) {
        double min = numeros[0];
        for (double num : numeros) {
            if (num < min) {
                min = num;
            }
        }
        return String.valueOf(min);
    }


}