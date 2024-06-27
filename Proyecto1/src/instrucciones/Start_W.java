/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.AST;
import simbolo.Arbol;
import simbolo.RetornoAST;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 * 
 * @author eliza
 */
public class Start_W extends Instruccion{
    private String id;
    private LinkedList<Instruccion> parametros;

    public Start_W(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(tipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var busqueda = arbol.getFuncion(id);
        if(busqueda == null){
            return new Excepcion("Semantico", "No se encontro la funcion", this.linea, this.columna);
        }
        if(busqueda instanceof  Metodo metodo){
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("Start_With");

            if (metodo.parametros.size() != this.parametros.size()){
                return new Excepcion("Semantico", "El numero de parametros no coincide", this.linea, this.columna);
            }
            //asociar parametros en la lista clase metodos y clase start_w
            // declaro parametros de start_w
            for(int i = 0; i < this.parametros.size(); i++){
                var identificador = (String) metodo.parametros.get(i).get("id");
                var valor = this.parametros.get(i);
                var tipo2 = (Tipo) metodo.parametros.get(i).get("tipo");

                var declaracionParametro = new Declaracion("",identificador,tipo2, valor, this.linea, this.columna);

                var resultado = declaracionParametro.interpretar(arbol, newTabla);

                if(resultado instanceof Excepcion){
                    return resultado;
                }
                
            }
        
            var resultado = metodo.interpretar(arbol, newTabla);
            if(resultado instanceof Excepcion){
                return resultado;
            }
        }
        return null;
    }

    public RetornoAST ast(AST ast){
        return new RetornoAST("", 0);
    }

}
