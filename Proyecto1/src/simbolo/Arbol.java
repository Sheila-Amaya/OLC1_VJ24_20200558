/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import abstracto.Instruccion;
import instrucciones.Metodo;
import instrucciones.Funcion;
import java.util.LinkedList;

/**
 *
 * @author eliza
 */
public class Arbol {

    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private tablaSimbolos tablaGlobal;
    public LinkedList<Exception> errores;
    private LinkedList<Instruccion> funciones;
    //private LinkedList<HashMap> structs;              // struc hashmap  
    // metodo linkedlist

    
    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new tablaSimbolos();
        this.errores = new LinkedList<>();
        this.funciones = new LinkedList<>();
        
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public tablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(tablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<Exception> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Exception> errores) {
        this.errores = errores;
    }

    public void Print(String valor) {
        this.consola += valor + "\n";
    }

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }
    
    public void addFunciones(Instruccion funcion){
        this.funciones.add(funcion);
    }

    public Instruccion getFuncion(String id) { 
        // Obtener una función o método por su id
        for (var i : this.funciones) {
            if (i instanceof Metodo metodo) {
                if (metodo.id.equalsIgnoreCase(id)) {
                    return i;
                }
            } else if (i instanceof Funcion funcion) {
                if (funcion.id.equalsIgnoreCase(id)) {
                    return i;
                }
            }
        }
        return null;
    }
    

}
