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
import java.util.LinkedList;

/**
 *
 * @author eliza
 */
public class DeclaracionVector2D extends Instruccion {
    private String mutabilidad;
    private String id;
    private Tipo tipo;
    private LinkedList<LinkedList<Instruccion>> valores;

    public DeclaracionVector2D(String mutabilidad, String id, Tipo tipo, LinkedList<LinkedList<Instruccion>> valores, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.tipo = tipo;
        this.valores = valores;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        LinkedList<LinkedList<Object>> vector = new LinkedList<>();

        for (LinkedList<Instruccion> fila : valores) {
            LinkedList<Object> filaVector = new LinkedList<>();
            for (Instruccion valor : fila) {
                Object valorInterpretado = valor.interpretar(arbol, tabla);
                if (valorInterpretado instanceof Excepcion) {
                    return valorInterpretado;
                }
                filaVector.add(valorInterpretado);
            }
            vector.add(filaVector);
        }

        Simbolo nuevoSimbolo = new Simbolo(this.tipo, this.id, vector);
        nuevoSimbolo.setMutabilidad(this.mutabilidad.equals("var"));

        if (!tabla.agregarVariable(nuevoSimbolo)) {
            return new Excepcion("Semántico", "La variable " + this.id + " ya existe.", this.linea, this.columna);
        }

        return null;
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"DECLARACION_VECTOR2D\"];";
        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        for (LinkedList<Instruccion> fila : valores) {
            for (Instruccion valor : fila) {
                RetornoAST valorAST = valor.ast(ast);
                dot += "\nnodo_" + id + " -> nodo_" + valorAST.id + ";";
                dot += "\n" + valorAST.dot;
            }
        }

        return new RetornoAST(dot, id);
    }
}