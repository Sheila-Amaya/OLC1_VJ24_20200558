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
public class Remove extends Instruccion {
    private String id;
    private Instruccion index;

    public Remove(String id, Instruccion index, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.id = id;
        this.index = index;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(this.id);
        if (simbolo == null) {
            return new Excepcion("Semántico", "La lista " + this.id + " no existe.", this.linea, this.columna);
        }

        Object valorIndice = this.index.interpretar(arbol, tabla);
        if (valorIndice instanceof Excepcion) {
            return valorIndice;
        }

        if (!(valorIndice instanceof Integer)) {
            return new Excepcion("Semántico", "El índice de la lista debe ser un entero.", this.linea, this.columna);
        }

        int indice = (Integer) valorIndice;
        LinkedList<Object> lista = (LinkedList<Object>) simbolo.getValor();

        if (indice < 0 || indice >= lista.size()) {
            return new Excepcion("Semántico", "Índice fuera de los límites de la lista.", this.linea, this.columna);
        }

        return lista.remove(indice);
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"REMOVE\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        RetornoAST indexAST = this.index.ast(ast);
        dot += "\n" + indexAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + indexAST.id + ";";

        return new RetornoAST(dot, id);
    }
}