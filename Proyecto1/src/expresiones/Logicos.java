/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Errores.Excepcion;
import abstracto.*;
import simbolo.*;

/**
 *
 * @author eliza
 */
public class Logicos extends Instruccion {

    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresLogicos logico; // &&, ||, !, ^
    

    public Logicos(Instruccion cond1, OperadoresLogicos logico, Instruccion cond2, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.logico = logico;
        
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Excepcion) {
            return condIzq;
        }

        var condDer = this.cond2 != null ? this.cond2.interpretar(arbol, tabla) : null;
        if (condDer instanceof Excepcion) {
            return condDer;
        }
        return switch (logico) {
            case AND -> 
                this.and(condIzq, condDer);
            case OR ->
                this.or(condIzq, condDer);
            case NOT ->
                this.not(condIzq);
            case XOR ->
                this.xor(condIzq, condDer);
            default->
                new Excepcion("Semantico", "Operador lógico no válido", this.linea, this.columna);
        };
    }

    public boolean and(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case BOOLEANO -> 
                switch (comparando2) {
                    case BOOLEANO -> 
                        (boolean) comp1 && (boolean) comp2;
                    default -> 
                        false;
                    };
            default ->
                false;
        };
}
    
        public boolean or(Object comp1, Object comp2) {
            var comparando1 = this.cond1.tipo.getTipo();
            var comparando2 = this.cond2.tipo.getTipo();
    
            return switch (comparando1) {
                case BOOLEANO -> 
                    switch (comparando2) {
                        case BOOLEANO -> 
                            (boolean) comp1 || (boolean) comp2;
                        default -> 
                            false;
                        };
                default ->
                    false;
            };
        }
    
        public boolean not(Object comp1) {
            var comparando1 = this.cond1.tipo.getTipo();
    
            return switch (comparando1) {
                case BOOLEANO -> 
                    !Boolean.parseBoolean(comp1.toString().toLowerCase());
                default ->
                    false;
            };
        }
    
        public boolean xor(Object comp1, Object comp2) {
            var comparando1 = this.cond1.tipo.getTipo();
            var comparando2 = this.cond2.tipo.getTipo();
    
            return switch (comparando1) {
                case BOOLEANO -> 
                    switch (comparando2) {
                        case BOOLEANO -> 
                            (boolean) comp1 ^ (boolean) comp2;
                        default -> 
                            false;
                        };
                default ->
                    false;
            };
        }
    
        @Override
        public String toString() {
            return this.cond1.toString() + " " + this.logico + " " + this.cond2.toString();
        }

        public RetornoAST ast(AST ast){
            return new RetornoAST("", 0);
        }
}