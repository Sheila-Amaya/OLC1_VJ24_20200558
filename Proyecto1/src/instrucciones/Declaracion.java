/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.AST;
import simbolo.Arbol;
import simbolo.RetornoAST;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author eliza
 */

public class Declaracion extends Instruccion {
    public String Identificador;    // Identificador de la variable
    public Instruccion Valor;       // Valor de la variable
    public String mutabilidad;      // Mutabilidad de la variable

    public Declaracion(String mutabilidad, String Identificador, Tipo tipo, Instruccion Valor, int linea, int columna) {
        super(tipo, linea, columna);
        this.Identificador = Identificador;
        this.Valor = Valor;
        this.mutabilidad = mutabilidad;
    }

    public Declaracion(String Identificador, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.Identificador = Identificador;
        this.Valor = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valorIn = this.Valor != null ? this.Valor.interpretar(arbol, tabla) : null; // Interpretar el valor de la variable si lo hay
        if (valorIn instanceof Exception) {
            return valorIn;
        }

        // Validar tipos de datos
        if (this.Valor != null && this.Valor.tipo.getTipo() != this.tipo.getTipo()) {
            return new Excepcion("Semantico", "Error de tipos de datos en la declaracion de la variable", linea, columna);
        }

        // Crear el símbolo y agregarlo a la tabla de símbolos
        Simbolo s;
        if (this.Valor != null) {
            s = new Simbolo(this.tipo, this.Identificador, valorIn);
        } else {
            s = new Simbolo(this.tipo, this.Identificador, obtenerValorInicial());
        }

        if (this.mutabilidad != null && this.mutabilidad.toLowerCase().equals("const")) {
            s.setMutabilidad(true);
        }

        boolean create = tabla.agregarVariable(s);
        if (!create) {
            return new Excepcion("Semantico", "La variable " + this.Identificador + " ya existe", linea, columna);
        }

        return null;
    }

    private Object obtenerValorInicial() {
        switch (this.tipo.getTipo()) {
            case tipoDato.ENTERO:
                return 0;
            case tipoDato.DECIMAL:
                return 0.0;
            case tipoDato.BOOLEANO:
                return false;
            case tipoDato.CARACTER:
                return '\0';
            case tipoDato.CADENA:
                return "";
            default:
                return null; // En caso de otros tipos de datos o errores
        }
    }

     public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"DECLARACION\"];";

        if (this.Identificador != null) {
            dot += "\nnodo_" + id + "_id[label=\"" + this.Identificador + "\"];";
            dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

            dot += "\nnodo_" + id + "_muta[label=\"" + this.mutabilidad + "\"];";
            dot += "\nnodo_" + id + " -> nodo_" + id + "_muta;";

            if (this.Valor != null) {
                RetornoAST valor = this.Valor.ast(ast);
                dot += "\n" + valor.dot;
                dot += "\nnodo_" + id + " -> nodo_" + valor.id + ";";
            }
        } else {
            // si Identificador es nulo
            dot += "\nnodo_" + id + "_id[label=\"NULL\"];";
            dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";
        }

        return new RetornoAST(dot, id);
    }
} 

