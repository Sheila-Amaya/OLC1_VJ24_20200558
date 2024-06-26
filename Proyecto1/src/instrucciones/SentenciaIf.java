/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.*;

/**
 * Clase que representa la instrucción 'if' en el lenguaje de programación.
 * Extiende de la clase base Instruccion.
 * 
 * @autor eliza
 */
public class SentenciaIf extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public SentenciaIf(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);  // Valida la condición del if
        if (cond instanceof Excepcion) {
            return cond;
        }

        // Verificar que la condición sea booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Excepcion("Semantico", "Expresion invalida para condicion if",
                    this.linea, this.columna);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) cond) {   // Si la condición es verdadera, se recorren todas las instrucciones
            for (var i : this.instrucciones) {
                if (i instanceof Break) {
                    return i;
                }
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break) {
                    return resultado;
                }
                if (resultado instanceof Continue) {
                    return resultado; 
                }
                if (resultado instanceof Excepcion) {
                    return resultado;
                }
            }
        }
        return null;
    }
    
    public RetornoAST ast(AST ast){
        return new RetornoAST("", 0);
    }

}
