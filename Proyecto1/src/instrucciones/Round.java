/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit este template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.AST;
import simbolo.Arbol;
import simbolo.RetornoAST;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 * 
 * @author eliza
 */
public class Round extends Instruccion {
    private Instruccion expresion;

    public Round(Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor = expresion.interpretar(arbol, tabla);
        if (valor instanceof Excepcion) {
            return valor;
        }

        if (valor instanceof Double) {
            return (int) Math.round((Double) valor);
        } else {
            return new Excepcion("Semantico", "El parámetro para la función round debe ser un número decimal.", linea, columna);
        }
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"ROUND\"];";

        RetornoAST valor = this.expresion.ast(ast);
        dot += "\nnodo_" + id + " -> nodo_" + valor.id + ";";
        dot += "\n" + valor.dot;

        return new RetornoAST(dot, id);
    }
}