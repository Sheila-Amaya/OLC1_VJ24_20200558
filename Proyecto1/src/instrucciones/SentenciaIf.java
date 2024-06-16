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
 * @author eliza
 */
public class SentenciaIf extends Instruccion {

    private Instruccion condicion;
    public LinkedList<Instruccion> instrucciones;

    public SentenciaIf(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condicion = this.condicion.interpretar(arbol, tabla);
        
        if(condicion instanceof Excepcion){
            return condicion;
        }
        
        //validar que la condicion sea de tipo boolean
        if(this.condicion.tipo.getTipo() != tipoDato.BOOLEANO){
            return new Excepcion("Semantico", "La condicion del if debe ser de tipo boolean", this.linea, this.columna);
        }
        
        var newTabla = new tablaSimbolos(tabla); //setea tabla anterior
        if((boolean)condicion){
            for(Instruccion var : this.instrucciones){
                var result = var.interpretar(arbol, newTabla);
                if(result instanceof Excepcion){
                    return new Excepcion("Semantico", "", this.linea, this.columna); 
                }
                
            }
        }
        return null;
    }

}
