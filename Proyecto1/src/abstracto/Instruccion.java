/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstracto;

import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos; //

/**
 *
 * @author eliza
 */
public abstract class Instruccion {
    public Tipo tipo;
    public int linea;
    public int columna;

    public Instruccion(Tipo tipo, int linea, int columna) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public abstract Object interpretar(Arbol arbol, tablaSimbolos tabla);
}
