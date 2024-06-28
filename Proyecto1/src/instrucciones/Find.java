/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import java.util.LinkedList;
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
public class Find extends Instruccion {
    private String id;
    private Instruccion expresion;

    public Find(String id, Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.id = id;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(this.id);
        if (simbolo == null) {
            return new Excepcion("Semántico", "El vector o lista " + this.id + " no existe.", this.linea, this.columna);
        }

        Object valorExpresion = this.expresion.interpretar(arbol, tabla);
        if (valorExpresion instanceof Excepcion) {
            return valorExpresion;
        }

        if (simbolo.getValor() instanceof LinkedList) {
            LinkedList<Object> lista = (LinkedList<Object>) simbolo.getValor();
            return lista.contains(valorExpresion);
        }

        return new Excepcion("Semántico", "El identificador no es un vector o lista.", this.linea, this.columna);
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"FIND\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        RetornoAST expresionAST = this.expresion.ast(ast);
        dot += "\n" + expresionAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + expresionAST.id + ";";

        return new RetornoAST(dot, id);
    }
}