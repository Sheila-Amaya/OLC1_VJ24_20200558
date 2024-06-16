/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import java.util.HashMap;

/**
 *
 * @author eliza
 */
public class tablaSimbolos {
    private tablaSimbolos tablaAneterior;
    private HashMap<String, Object> tablaActual;
    private String nombre;

    public tablaSimbolos getTablaAneterior() {
        return tablaAneterior;
    }

    public tablaSimbolos(){
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void actualizarVariable(Simbolo simbolo){
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase()); //busca si ya existe la variable
        if(busqueda != null){ //si no existe la variable
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo); //agrega la variable
        }
    }

    //para la declaracion de variables
    public boolean agregarVariable(Simbolo simbolo){
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase()); //busca si ya existe la variable

        if(busqueda == null){ //si no existe la variable
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo); //agrega la variable
            return true;
        }
        return false;
        
    }

    public Simbolo getVariable(String id){
        for (tablaSimbolos tabla = this; tabla != null; tabla = tabla.getTablaAneterior()) {
            Simbolo busqueda = (Simbolo) tabla.getTablaActual().get(id.toLowerCase()); //busca si ya existe la variable
            if(busqueda != null){ //si no existe la variable
                return busqueda;
            }
        }
        return null;
    }

}
