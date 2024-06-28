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
public class AsignacionVector1D extends Instruccion {
    private String id;
    private Instruccion indice;
    private Instruccion valor;

    public AsignacionVector1D(String id, Instruccion indice, Instruccion valor, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(this.id);
        if (simbolo == null) {
            return new Excepcion("Semántico", "El vector " + this.id + " no existe.", this.linea, this.columna);
        }

        if (!simbolo.isMutabilidad()) {
            return new Excepcion("Semántico", "No se puede modificar un vector declarado como const.", this.linea, this.columna);
        }

        Object valorIndice = this.indice.interpretar(arbol, tabla);
        if (valorIndice instanceof Excepcion) {
            return valorIndice;
        }

        if (!(valorIndice instanceof Integer)) {
            return new Excepcion("Semántico", "El índice del vector debe ser un entero.", this.linea, this.columna);
        }

        int index = (Integer) valorIndice;
        LinkedList<Object> vector = (LinkedList<Object>) simbolo.getValor();

        if (index < 0 || index >= vector.size()) {
            return new Excepcion("Semántico", "Índice fuera de los límites del vector.", this.linea, this.columna);
        }

        Object nuevoValor = this.valor.interpretar(arbol, tabla);
        if (nuevoValor instanceof Excepcion) {
            return nuevoValor;
        }

        vector.set(index, nuevoValor);
        return null;
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"ASIGNACION_VECTOR\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        RetornoAST indiceAST = this.indice.ast(ast);
        dot += "\n" + indiceAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + indiceAST.id + ";";

        RetornoAST valorAST = this.valor.ast(ast);
        dot += "\n" + valorAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + valorAST.id + ";";

        return new RetornoAST(dot, id);
    }
}