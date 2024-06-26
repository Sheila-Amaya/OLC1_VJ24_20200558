/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Errores.*;
import abstracto.Instruccion;
import simbolo.*;

/**
 *
 * @author eliza
 */
public class AccesoVariables extends Instruccion{
    private String id;

    public AccesoVariables(String id, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = tabla.getVariable(this.id);                     //busca la variable en la tabla de simbolos
        if(valor == null){
            return new Excepcion("Semantico", "La variable no existe", this.linea, this.columna);
        }
        this.tipo.setTipo(valor.getTipo().getTipo());
        return valor.getValor();
    }
    
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"" + this.id + "\"];";
        return new RetornoAST(dot, id);
    }
}
