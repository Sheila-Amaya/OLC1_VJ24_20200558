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
 * 
 * @autor eliza
 */
public class SentenciaIfElseIf extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private Instruccion elseIf;
    private LinkedList<Instruccion> instruccionesElse;

    public SentenciaIfElseIf(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, Instruccion elseIf, LinkedList<Instruccion> instruccionesElse, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.elseIf = elseIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Excepcion) {
            return cond;
        }

        // verificar condicion sea booleano
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Excepcion("Semantico", "Expresion invalida", this.linea, this.columna);
        }

        var newTabla = new tablaSimbolos(tabla);
        if ((boolean) cond) {   // si la condición es verdadera se recorren todas las instrucciones del if
            for (var i : this.instruccionesIf) {
                if (i instanceof Break || i instanceof Continue) {
                    return i;
                }
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break || resultado instanceof Continue || resultado instanceof Excepcion) {
                    return resultado;
                }
            }
        } else if (this.elseIf != null) { // si la condición es falsa, se evalúa el else if
            var resultadoElseIf = this.elseIf.interpretar(arbol, newTabla);
            if (resultadoElseIf instanceof Excepcion) {
                return resultadoElseIf;
            }
            if (resultadoElseIf instanceof Break || resultadoElseIf instanceof Continue) {
                return resultadoElseIf;
            }
        } else { // si la condición else if es falsa, se recorren las instrucciones del else
            if (this.instruccionesElse != null) {
                for (var i : this.instruccionesElse) {
                    if (i instanceof Break || i instanceof Continue) {
                        return i;
                    }
                    var resultado = i.interpretar(arbol, newTabla);
                    if (resultado instanceof Break || resultado instanceof Continue || resultado instanceof Excepcion) {
                        return resultado;
                    }
                }
            }
        }
        return null;
    }
}
