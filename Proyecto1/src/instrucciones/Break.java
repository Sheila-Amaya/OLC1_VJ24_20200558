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
public class Break extends Instruccion {

    public Break(int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        return null;
    }
    
    public RetornoAST ast(AST ast){
        return new RetornoAST("", 0);
    }

}