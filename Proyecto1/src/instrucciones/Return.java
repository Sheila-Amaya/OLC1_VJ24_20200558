/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;
/**
 *
 * @author eliza
 */
public class Return extends Instruccion {
    private Instruccion valorRetorno;

    public Return(Instruccion valorRetorno, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.valorRetorno = valorRetorno;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        if (valorRetorno != null) {
            return valorRetorno.interpretar(arbol, tabla);
        }
        return this;
    }

    public Instruccion getValorRetorno() {
        return valorRetorno;
    }
}