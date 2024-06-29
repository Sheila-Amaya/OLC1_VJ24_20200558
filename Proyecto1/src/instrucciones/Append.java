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
public class Append extends Instruccion {
    private String id;
    private Instruccion valor;

    public Append(String id, Instruccion valor, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Simbolo simbolo = tabla.getVariable(this.id);
        if (simbolo == null) {
            return new Excepcion("Semántico", "La lista " + this.id + " no existe.", this.linea, this.columna);
        }

        if (!(simbolo.getValor() instanceof LinkedList)) {
            return new Excepcion("Semántico", "El identificador " + this.id + " no es una lista.", this.linea, this.columna);
        }

        Object valorInterpretado = this.valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Excepcion) {
            return valorInterpretado;
        }

        LinkedList<Object> lista = (LinkedList<Object>) simbolo.getValor();
        lista.add(valorInterpretado);
        simbolo.setValor(lista);  // Asegúrate de actualizar el valor en la tabla de símbolos.

        return null;
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"APPEND\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        RetornoAST valorAST = this.valor.ast(ast);
        dot += "\n" + valorAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + valorAST.id + ";";

        return new RetornoAST(dot, id);
    }
}