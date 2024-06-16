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
public class AsignacionVariables extends Instruccion{
    public String id;
    public Instruccion valor;

    public AsignacionVariables(String id, Instruccion valor, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var variable = tabla.getVariable(this.id);
        if(variable == null){
            return new Errores.Excepcion("Semantico", "La variable no existe", this.linea, this.columna);
        }

        var nuevoValor = this.valor.interpretar(arbol, tabla);
        if(nuevoValor instanceof Exception){
            return nuevoValor;
        }
        //coincida el tipo de dato
        if(variable.getTipo().getTipo() != this.valor.tipo.getTipo()){
            return new Errores.Excepcion("Semantico", "Error de tipos de datos en la asignacion de la variable", this.linea, this.columna);
        }     
        
        if(tabla.getVariable(id).isMutabilidad()){
            return new Errores.Excepcion("Semantico", "La variable no es mutable, no reasignar permitido", this.linea, this.columna);
        }

        variable.setValor(nuevoValor);
        tabla.actualizarVariable(variable);
        return null;
    }


}
