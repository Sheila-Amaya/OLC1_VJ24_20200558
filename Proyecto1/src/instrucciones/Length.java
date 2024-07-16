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
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 * 
 * @author eliza
 */
public class Length extends Instruccion {
    private Instruccion expresion;

    public Length(Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Evaluar la expresión para obtener el valor
        Object valor = expresion.interpretar(arbol, tabla);
        if (valor instanceof String) {
            return ((String) valor).length();
        } else if (valor instanceof LinkedList) {
            return ((LinkedList<?>) valor).size();
        } else if (valor.getClass().isArray()) {
            return java.lang.reflect.Array.getLength(valor);
        } else {
            return new Excepcion("Semántico", "El parámetro para la función length no es una cadena, lista o arreglo.", linea, columna);
        }
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"LENGTH\"];";
        
        RetornoAST valor = this.expresion.ast(ast);
        dot += "\nnodo_" + id + " -> nodo_" + valor.id + ";";
        dot += "\n" + valor.dot;
        
        return new RetornoAST(dot, id);
    }
}