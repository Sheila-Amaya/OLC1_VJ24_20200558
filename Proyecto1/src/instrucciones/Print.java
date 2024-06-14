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
public class Print extends Instruccion{
    
    private Instruccion expresion;

    public Print(Instruccion expresion, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var resultado = this.expresion.interpretar(arbol, tabla);
        if (resultado instanceof Exception) {
            return resultado;
        }
        arbol.Print(resultado.toString());
        return null;
    }
    
    
}
