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
public class ToString extends Instruccion {
    private Instruccion expresion;

    public ToString(Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.CADENA), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor = expresion.interpretar(arbol, tabla);
        if (valor instanceof Integer || valor instanceof Double || valor instanceof Character || valor instanceof Boolean || valor instanceof String) {
            return valor.toString();
        } else if (valor instanceof LinkedList) {
            return convertirListaATexto((LinkedList<?>) valor);
        } else {
            return new Excepcion("Semántico", "El parámetro para la función toString no es un tipo de dato compatible.", linea, columna);
        }
    }

    private String convertirListaATexto(LinkedList<?> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object elemento : lista) {
            if (elemento instanceof LinkedList) {
                sb.append(convertirListaATexto((LinkedList<?>) elemento));
            } else {
                sb.append(elemento.toString());
            }
            sb.append(", ");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Eliminar la última coma y espacio
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"TO_STRING\"];";
        
        RetornoAST valor = this.expresion.ast(ast);
        dot += "\nnodo_" + id + " -> nodo_" + valor.id + ";";
        dot += "\n" + valor.dot;
        
        return new RetornoAST(dot, id);
    }
}