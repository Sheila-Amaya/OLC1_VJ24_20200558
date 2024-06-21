/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author eliza
 */

public class Declaracion extends Instruccion{
    public String Identificador;    // Identificador de la variable
    public Instruccion Valor;       // Valor de la variable
    public String mutabilidad;     // Mutabilidad de la variable

    
    public Declaracion(String mutabilidad,String Identificador,Tipo tipo, Instruccion Valor,  int linea, int columna) {
        super(tipo, linea, columna);
        this.Identificador = Identificador;
        this.Valor = Valor;
        this.mutabilidad = mutabilidad;
    }


    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valorIn = this.Valor != null ? this.Valor.interpretar(arbol, tabla) : null;             //interpretar el valor de la variable
        if(valorIn instanceof Exception){
            return valorIn;
        }

        // validar tipos de datos
        if(this.Valor != null && this.Valor.tipo.getTipo() != this.tipo.getTipo()){
            return new Excepcion("Semantico","Error de tipos de datos en la declaracion de la variable", linea, columna);
        }

        if (this.Valor != null) {
            Simbolo s = new Simbolo(this.tipo, this.Identificador, valorIn);
            
            if(this.mutabilidad.toLowerCase().equals("const")){ // si la variable es constante
                System.err.println("mutabilidad " + true);
                s.setMutabilidad(true);
            }
    
            boolean create = tabla.agregarVariable(s);
            if(!create){
                return new Excepcion("Semantico","La variable "+this.Identificador+" ya existe", linea, columna);
            }
        } else {
            if (tipo.getTipo() == tipoDato.ENTERO) {
                Simbolo s = new Simbolo(this.tipo, this.Identificador, 0);
                if(this.mutabilidad.toLowerCase().equals("const")){ // si la variable es constante
                    s.setMutabilidad(true);
                }
        
                boolean create = tabla.agregarVariable(s);
                if(!create){
                    return new Excepcion("Semantico","La variable "+this.Identificador+" ya existe", linea, columna);
                }
            }
            if (tipo.getTipo() == tipoDato.DECIMAL) {
                Simbolo s = new Simbolo(this.tipo, this.Identificador, 0.0);
                if(this.mutabilidad.toLowerCase().equals("const")){ // si la variable es constante
                    s.setMutabilidad(true);
                }
        
                boolean create = tabla.agregarVariable(s);
                if(!create){
                    return new Excepcion("Semantico","La variable "+this.Identificador+" ya existe", linea, columna);
                }
            }
            if (tipo.getTipo() == tipoDato.BOOLEANO) {
                Simbolo s = new Simbolo(this.tipo, this.Identificador, true);
                if(this.mutabilidad.toLowerCase().equals("const")){ // si la variable es constante
                    s.setMutabilidad(true);
                }
        
                boolean create = tabla.agregarVariable(s);
                if(!create){
                    return new Excepcion("Semantico","La variable "+this.Identificador+" ya existe", linea, columna);
                }
            }
            if (tipo.getTipo() == tipoDato.CARACTER) {
                Simbolo s = new Simbolo(this.tipo, this.Identificador, '\0');
                if(this.mutabilidad.toLowerCase().equals("const")){ // si la variable es constante
                    s.setMutabilidad(true);
                }
        
                boolean create = tabla.agregarVariable(s);
                if(!create){
                    return new Excepcion("Semantico","La variable "+this.Identificador+" ya existe", linea, columna);
                }
            }
            if (tipo.getTipo() == tipoDato.CADENA) {
                Simbolo s = new Simbolo(this.tipo, this.Identificador, "");
                if(this.mutabilidad.toLowerCase().equals("const")){ // si la variable es constante
                    s.setMutabilidad(true);
                }
        
                boolean create = tabla.agregarVariable(s);
                if(!create){
                    return new Excepcion("Semantico","La variable "+this.Identificador+" ya existe", linea, columna);
                }
            }
        }

        return null;
    }
}