/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.*;
import java.util.LinkedList;

/**
 *
 * @author eliza
 */
public class DeclaracionLista extends Instruccion {
    private Tipo tipo;
    private String id;

    public DeclaracionLista(Tipo tipo, String id, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        LinkedList<Object> lista = new LinkedList<>();

        Simbolo nuevoSimbolo = new Simbolo(this.tipo, this.id, lista);
        nuevoSimbolo.setMutabilidad(true);  // Las listas siempre son mutables

        if (!tabla.agregarVariable(nuevoSimbolo)) {
            return new Excepcion("Semántico", "La lista " + this.id + " ya existe.", this.linea, this.columna);
        }

        return null;
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"DECLARACION_LISTA\"];";
        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        return new RetornoAST(dot, id);
    }
}