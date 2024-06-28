/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

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
public class AccesoVector2D extends Instruccion {
    private String id;
    private Instruccion indiceFila;
    private Instruccion indiceColumna;

    public AccesoVector2D(String id, Instruccion indiceFila, Instruccion indiceColumna, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.indiceFila = indiceFila;
        this.indiceColumna = indiceColumna;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(this.id);
        if (simbolo == null) {
            return new Excepcion("Semántico", "El vector " + this.id + " no existe.", this.linea, this.columna);
        }

        Object valorIndiceFila = this.indiceFila.interpretar(arbol, tabla);
        Object valorIndiceColumna = this.indiceColumna.interpretar(arbol, tabla);
        if (valorIndiceFila instanceof Excepcion || valorIndiceColumna instanceof Excepcion) {
            return valorIndiceFila instanceof Excepcion ? valorIndiceFila : valorIndiceColumna;
        }

        if (!(valorIndiceFila instanceof Integer) || !(valorIndiceColumna instanceof Integer)) {
            return new Excepcion("Semántico", "Los índices del vector deben ser enteros.", this.linea, this.columna);
        }

        int fila = (Integer) valorIndiceFila;
        int columna = (Integer) valorIndiceColumna;
        LinkedList<LinkedList<Object>> vector = (LinkedList<LinkedList<Object>>) simbolo.getValor();

        if (fila < 0 || fila >= vector.size() || columna < 0 || columna >= vector.get(fila).size()) {
            return new Excepcion("Semántico", "Índice fuera de los límites del vector.", this.linea, this.columna);
        }

        return vector.get(fila).get(columna);
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"ACCESO_VECTOR2D\"];";
        
        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";
        
        RetornoAST filaAST = this.indiceFila.ast(ast);
        dot += "\n" + filaAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + filaAST.id + ";";
        
        RetornoAST columnaAST = this.indiceColumna.ast(ast);
        dot += "\n" + columnaAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + columnaAST.id + ";";
        
        return new RetornoAST(dot, id);
    }
}