/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
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
public class Llamada extends Instruccion{
    private String id;
    private LinkedList<Instruccion> parametros;

    public Llamada(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var busqueda = arbol.getFuncion(this.id);
        if(busqueda == null){
            return new Exception("Error semantico: la funcion " + this.id + " no existe");
        }
        if(busqueda instanceof Metodo metodo){
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("llamada metodo" + this.id);
            if(metodo.parametros.size() != this.parametros.size()){
                return new Exception("Error semantico: la cantidad de parametros no coincide con la cantidad de parametros de la funcion " + this.id);
            }
            for(int i = 0; i< this.parametros.size(); i++){
                var Identificador = (String) metodo.parametros.get(i).get("id");
                var valor = this.parametros.get(i);
                var tipo2 = (Tipo) metodo.parametros.get(i).get("tipo");

                var declaracioParametro = new Declaracion(Identificador, tipo2, this.linea, this.columna);

                var resultado = declaracioParametro.interpretar(arbol, newTabla);

                if(resultado instanceof Exception){
                    return resultado;
                }

                var valorInterpretado = valor.interpretar(arbol, tabla);
                if(valorInterpretado instanceof Exception){
                    return valorInterpretado;
                }

                var variable = newTabla.getVariable(Identificador);
                if(variable == null){
                    return new Exception("Error semantico: la variable " + Identificador + " no existe");
                }
                if(variable.getTipo().getTipo() != valor.tipo.getTipo()){
                    return new Exception("Error semantico: el tipo de la variable " + Identificador + " no coincide con el tipo del parametro de la funcion " + this.id);
                }
                variable.setValor(valorInterpretado);
            }
            var resultado = metodo.interpretar(arbol, newTabla);
            if(resultado instanceof Exception){
                return resultado;
            }
        }
        return null;
    }
    
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"LLAMADA\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"]";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id";

        for (Instruccion parametro : parametros) {
            RetornoAST valor = parametro.ast(ast);
            dot += "\nnodo_" + id + " -> nodo_" + valor.id + ";";
            dot += "\n" + valor.dot;
        }

        return new RetornoAST(dot, id);
    }

    
}