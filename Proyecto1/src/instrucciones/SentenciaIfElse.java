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
 * 
 * @autor eliza
 */
public class SentenciaIfElse extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private LinkedList<Instruccion> instruccionesElse;

    public SentenciaIfElse(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, LinkedList<Instruccion> instruccionesElse, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
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
        if ((boolean) cond) {   // Si la condición es verdadera, se recorren todas las instrucciones del if
            for (var i : this.instruccionesIf) {
                var resultado = i.interpretar(arbol, newTabla);
                if (i instanceof Break) {
                    return i;
                }
                if (resultado != null) {
                    return resultado;
                }
            }
        } else { 
            for (var i : this.instruccionesElse) {
                var resultado = i.interpretar(arbol, newTabla);
                if (i instanceof Break) {
                    return i;
                }
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"IF_ELSE\"];";

        // Nodo de la condición
        RetornoAST astCondicion = condicion.ast(ast);
        dot += "\nnodo_" + id + "_cond[label=\"CONDICION\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_cond;";
        dot += "\nnodo_" + id + "_cond -> nodo_" + astCondicion.id + ";";
        dot += astCondicion.dot;

        // Nodos de las instrucciones del if
        int ifId = ast.getNewID();
        dot += "\nnodo_" + ifId + "[label=\"IF\"];";
        dot += "\nnodo_" + id + " -> nodo_" + ifId + ";";
        String dotInstruccionesIf = "";
        for (Instruccion ins : instruccionesIf) {
            RetornoAST astIns = ins.ast(ast);
            dotInstruccionesIf += "\nnodo_" + ifId + " -> nodo_" + astIns.id + ";";
            dot += astIns.dot;
        }

        // Nodos de las instrucciones del else
        if (instruccionesElse != null) {
            int elseId = ast.getNewID();
            dot += "\nnodo_" + elseId + "[label=\"ELSE\"];";
            dot += "\nnodo_" + id + " -> nodo_" + elseId + ";";
            String dotInstruccionesElse = "";
            for (Instruccion ins : instruccionesElse) {
                RetornoAST astIns = ins.ast(ast);
                dotInstruccionesElse += "\nnodo_" + elseId + " -> nodo_" + astIns.id + ";";
                dot += astIns.dot;
            }
            dot += dotInstruccionesElse;
        }

        return new RetornoAST(dot + dotInstruccionesIf, id);
    }
}