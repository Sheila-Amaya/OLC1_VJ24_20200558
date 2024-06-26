/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.AST;
import simbolo.Arbol;
import simbolo.RetornoAST;
import simbolo.Tipo;
import simbolo.tablaSimbolos;

/**
 *
 * @author eliza
 */
public class Nativo extends Instruccion{
    public Object valor;
    public Tipo tipo;

    public Nativo(Object valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var tipo = this.tipo.getTipo();
        switch (tipo) {
            case CADENA:
                this.valor = this.valor.toString().replace("\\n", "\n");
                this.valor = this.valor.toString().replace("\\t", "\t");
                this.valor = this.valor.toString().replace("\\\"", "\"");
                this.valor = this.valor.toString().replace("\\'", "\'");
                this.valor = this.valor.toString().replace("\\\\", "\\");
                return this.valor;
            default:
                return this.valor;
        }
    }
    
    public RetornoAST ast(AST ast){
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"" + this.valor + "\"];";
        return new RetornoAST(dot, id);
    }

}