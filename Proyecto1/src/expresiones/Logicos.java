/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Errores.Excepcion;
import abstracto.*;
import simbolo.*;

/**
 *
 * @author eliza
 */
public class Logicos extends Instruccion {

    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresLogicos logico; // &&, ||, !, ^
    private Instruccion opUnico; // Operando único para operadores unarios

    public Logicos(OperadoresLogicos logico, Instruccion opUnico, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.logico = logico;
        this.opUnico = opUnico;
    }

    public Logicos(Instruccion cond1, OperadoresLogicos logico, Instruccion cond2, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.logico = logico;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        if (opUnico != null) {
            Object resultadoUnico = opUnico.interpretar(arbol, tabla);
            if (resultadoUnico instanceof Excepcion) {
                return resultadoUnico;
            }
            return not(resultadoUnico); // Ejecutar la operación unaria directamente
        } else {
            Object condIzq = cond1.interpretar(arbol, tabla);
            if (condIzq instanceof Excepcion) {
                return condIzq;
            }

            Object condDer = cond2.interpretar(arbol, tabla);
            if (condDer instanceof Excepcion) {
                return condDer;
            }

            switch (logico) {
                case AND:
                    return and(condIzq, condDer);
                case OR:
                    return or(condIzq, condDer);
                case XOR:
                    return xor(condIzq, condDer);
                default:
                    return new Excepcion("Semantico", "Operador lógico no válido", this.linea, this.columna);
            }
        }
    }

    public boolean and(Object comp1, Object comp2) {
        if (comp1 instanceof Boolean && comp2 instanceof Boolean) {
            return (boolean) comp1 && (boolean) comp2;
        }
        return false;
    }

    public boolean or(Object comp1, Object comp2) {
        if (comp1 instanceof Boolean && comp2 instanceof Boolean) {
            return (boolean) comp1 || (boolean) comp2;
        }
        return false;
    }

    public boolean not(Object comp) {
        if (comp instanceof Boolean) {
            return !(boolean) comp;
        }
        return false; // Manejar otro tipo de error 
    }

    public boolean xor(Object comp1, Object comp2) {
        if (comp1 instanceof Boolean && comp2 instanceof Boolean) {
            return (boolean) comp1 ^ (boolean) comp2;
        }
        return false;
    }

}
