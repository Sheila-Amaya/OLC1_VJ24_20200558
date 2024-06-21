/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.*;

/**
 * 
 * 
 * @author eliza
 */

public class SentenciaDoWhile extends Instruccion {

    private LinkedList<Instruccion> instrucciones;
    private Instruccion condicion;

    public SentenciaDoWhile(LinkedList<Instruccion> instrucciones, Instruccion condicion, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.instrucciones = instrucciones;
        this.condicion = condicion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla);
        boolean cond;
        do {
            var newTabla2 = new tablaSimbolos(newTabla);

            // Ejecutar instrucciones
            boolean breakFound = false;
            for (Instruccion ins : this.instrucciones) {
                var resIns = ins.interpretar(arbol, newTabla2);
                if (resIns instanceof Break) {
                    breakFound = true;
                    break; 
                }
            }

            if (breakFound) {
                break; 
            }

            // Evaluar la condición
            cond = (boolean) this.condicion.interpretar(arbol, newTabla);
            if (cond) {
                newTabla = new tablaSimbolos(tabla);
            }
        } while (cond);

        return null;
    }
}
