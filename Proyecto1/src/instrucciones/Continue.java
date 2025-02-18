/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

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

public class Continue extends Instruccion {

    public Continue(int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return this;
    }
    
    public RetornoAST ast(AST ast){
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"CONTINUE\"];";
        return new RetornoAST(dot, id);
    }

}