/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.*;

/**
 * Clase que representa la instrucción 'if' en el lenguaje de programación.
 * Extiende de la clase base Instruccion.
 * 
 * @autor eliza
 */
public class SentenciaIf extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public SentenciaIf(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);  // Valida la condición del if
        if (cond instanceof Excepcion) {
            return cond;
        }

        // Verificar que la condición sea booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Excepcion("Semantico", "Expresion invalida para condicion if",
                    this.linea, this.columna);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) cond) {   // Si la condición es verdadera, se recorren todas las instrucciones
            for (var i : this.instrucciones) {
                var resultado = i.interpretar(arbol, newTabla);
                if (i instanceof Break) {
                    return i;
                }
                if(resultado != null) return resultado;
            }
        }
        return null;
    }
    
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"IF\"];";

        // Nodo de la condición
        RetornoAST astCondicion = condicion.ast(ast);
        dot += "\nnodo_" + id + "_cond[label=\"CONDICION\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_cond;";
        dot += "\nnodo_" + id + "_cond -> nodo_" + astCondicion.id + ";";
        dot += astCondicion.dot;

        // Nodos de las instrucciones del if
        String dotInstrucciones = "";
        for (Instruccion ins : instrucciones) {
            RetornoAST astIns = ins.ast(ast);
            dotInstrucciones += "\nnodo_" + id + " -> nodo_" + astIns.id + ";";
            dot += astIns.dot;
        }

        return new RetornoAST(dot + dotInstrucciones, id);
    }
}