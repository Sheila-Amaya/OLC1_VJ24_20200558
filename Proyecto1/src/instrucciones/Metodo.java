/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import java.util.HashMap;
import java.util.LinkedList;
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
public class Metodo extends Instruccion{
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;

    public Metodo(String id,LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        for(var i: this.instrucciones){
            var resultado = i.interpretar(arbol, tabla);
            //resultado instancia de errores
            //resultado o i es instancia de break/continue
            // return;
            }return null;
    }

    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"METODO\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"]";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id";


        for (HashMap<String, Object> parametro : parametros) {
            int paramId = ast.getNewID();
            dot += "\nnodo_" + paramId + "[label=\"PARAMETRO\"];";
            dot += "\nnodo_" + id + " -> nodo_" + paramId;
            dot += "\nnodo_" + paramId + "_id[label=\"" + parametro.get("id") + "\"]";
            dot += "\nnodo_" + paramId + " -> nodo_" + paramId + "_id";
        }


        for (Instruccion instruccion : instrucciones) {
            RetornoAST instruccionAST = instruccion.ast(ast);
            dot += "\nnodo_" + id + " -> nodo_" + instruccionAST.id + ";";
            dot += "\n" + instruccionAST.dot;
        }

        return new RetornoAST(dot, id);
    }
}
