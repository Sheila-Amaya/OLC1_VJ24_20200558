/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.*;

/**
 *
 * @author eliza
 */
public class Relacionales extends Instruccion {

    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int columna) {
        super(new Tipo(tipoDato.BOOLEANO), linea, columna);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Excepcion) {
            return condIzq;
        }
        var condDer = this.cond2.interpretar(arbol, tabla);
        if (condDer instanceof Excepcion) {
            return condDer;
        }
        return switch (relacional) {
            case EQUALS ->                                   // ==
                this.equals(condIzq, condDer);
            case NOT_EQUALS ->                               // !=
                this.notEquals(condIzq, condDer);
            case GREATER_THAN ->                             // >
                this.greaterThan(condIzq, condDer); 
            case LESS_THAN ->                                // <        
                this.lessThan(condIzq, condDer);
            case GREATER_THAN_OR_EQUALS ->                   // >=
                this.greaterThanOrEquals(condIzq, condDer);
            case LESS_THAN_OR_EQUALS ->                      // <=
                this.lessThanOrEquals(condIzq, condDer);
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }

    
    public Object equals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->                     // entero == entero
                        (int) comp1 == (int) comp2;
                    case DECIMAL ->                    // entero == decimal
                        (int) comp1 == (double) comp2;
                    case CARACTER ->                   // entero == caracter
                        (int) comp1 == (char)((String) comp2).charAt(0) ;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->                 // decimal == entero
                        (double) comp1 == (int) comp2;
                    case DECIMAL ->                 // decimal == decimal
                        (double) comp1 == (double) comp2;
                    case CARACTER ->               // decimal == caracter
                        (double) comp1 == (char)((String) comp2).charAt(0) ;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CARACTER ->
                switch (comparando2) {
                    case ENTERO ->                 // caracter == entero
                        (char)((String) comp1).charAt(0)  == (int) comp2;
                    case DECIMAL ->                // caracter == decimal
                        (char)((String) comp1).charAt(0) == (double) comp2;
                    case CARACTER ->               // caracter == caracter
                        (char)((String) comp1).charAt(0) == (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->                 // cadena == cadena
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case BOOLEANO ->
                switch (comparando2) {
                    case BOOLEANO ->                 // booleano == booleano
                        (boolean) comp1 == (boolean) comp2;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }

    
    public Object notEquals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->                     // entero != entero                   
                        (int) comp1 != (int) comp2;
                    case DECIMAL ->                    // entero != decimal
                        (int) comp1 != (double) comp2;
                    case CARACTER ->                   // entero != caracter
                        (int) comp1 != (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->                 // decimal != entero
                        (double) comp1 != (int) comp2;
                    case DECIMAL ->                 // decimal != decimal
                        (double) comp1 != (double) comp2;
                    case CARACTER ->               // decimal != caracter
                        (double) comp1 != (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CARACTER ->
                switch (comparando2) {
                    case ENTERO ->                 // caracter != entero
                        (char)((String) comp1).charAt(0) != (int) comp2;
                    case DECIMAL ->                // caracter != decimal
                        (char)((String) comp1).charAt(0) != (double) comp2;
                    case CARACTER ->               // caracter != caracter
                        (char)((String) comp1).charAt(0) != (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->                 // cadena != cadena
                        !comp1.toString().equals(comp2.toString());
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case BOOLEANO ->
                switch (comparando2) {
                    case BOOLEANO ->                 // booleano != booleano
                        (boolean) comp1 != (boolean) comp2;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }
    

    public Object greaterThan(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->                     // entero > entero
                        (int) comp1 > (int) comp2;
                    case DECIMAL ->                    // entero > decimal
                        (int) comp1 > (double) comp2;
                    case CARACTER ->                   // entero > caracter
                        (int) comp1 > (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->                 // decimal > entero
                        (double) comp1 > (int) comp2;
                    case DECIMAL ->                 // decimal > decimal
                        (double) comp1 > (double) comp2;
                    case CARACTER ->               // decimal > caracter
                        (double) comp1 > (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CARACTER ->
                switch (comparando2) {
                    case ENTERO ->                 // caracter > entero
                        (char)((String) comp1).charAt(0) > (int) comp2;
                    case DECIMAL ->                // caracter > decimal
                        (char)((String) comp1).charAt(0) > (double) comp2;
                    case CARACTER ->               // caracter > caracter
                        (char)((String) comp1).charAt(0) > (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->                 // cadena > cadena
                        comp1.toString().compareTo(comp2.toString()) > 0;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }

    
    public Object lessThan(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->                     // entero < entero
                        (int) comp1 < (int) comp2;
                    case DECIMAL ->                    // entero < decimal
                        (int) comp1 < (double) comp2;
                    case CARACTER ->                   // entero < caracter
                        (int) comp1 < (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->                 // decimal < entero
                        (double) comp1 < (int) comp2;
                    case DECIMAL ->                 // decimal < decimal
                        (double) comp1 < (double) comp2;
                    case CARACTER ->               // decimal < caracter
                        (double) comp1 < (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CARACTER ->
                switch (comparando2) {
                    case ENTERO ->                 // caracter < entero
                        (char)((String) comp1).charAt(0) < (int) comp2;
                    case DECIMAL ->                // caracter < decimal
                        (char)((String) comp1).charAt(0) < (double) comp2;
                    case CARACTER ->               // caracter < caracter
                        (char)((String) comp1).charAt(0) < (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->                 // cadena < cadena
                        comp1.toString().compareTo(comp2.toString()) < 0;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case BOOLEANO ->
                switch (comparando2) {
                    case BOOLEANO ->                 // booleano < booleano
                        (boolean) comp1 == (boolean) comp2;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }

    public Object greaterThanOrEquals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->                     // entero >= entero
                        (int) comp1 >= (int) comp2;
                    case DECIMAL ->                    // entero >= decimal
                        (int) comp1 >= (double) comp2;
                    case CARACTER ->                   // entero >= caracter
                        (int) comp1 >= (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->                 // decimal >= entero
                        (double) comp1 >= (int) comp2;
                    case DECIMAL ->                 // decimal >= decimal
                        (double) comp1 >= (double) comp2;
                    case CARACTER ->               // decimal >= caracter
                        (double) comp1 >= (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CARACTER ->
                switch (comparando2) {
                    case ENTERO ->                 // caracter >= entero
                        (char)((String) comp1).charAt(0) >= (int) comp2;
                    case DECIMAL ->                // caracter >= decimal
                        (char)((String) comp1).charAt(0) >= (double) comp2;
                    case CARACTER ->               // caracter >= caracter
                        (char)((String) comp1).charAt(0) >= (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->                 // cadena >= cadena
                        comp1.toString().compareTo(comp2.toString()) >= 0;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }
    
    public Object lessThanOrEquals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case ENTERO ->
                switch (comparando2) {
                    case ENTERO ->                     // entero <= entero
                        (int) comp1 <= (int) comp2;
                    case DECIMAL ->                    // entero <= decimal
                        (int) comp1 <= (double) comp2;
                    case CARACTER ->                   // entero <= caracter
                        (int) comp1 <= (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case DECIMAL ->
                switch (comparando2) {
                    case ENTERO ->                 // decimal <= entero
                        (double) comp1 <= (int) comp2;
                    case DECIMAL ->                 // decimal <= decimal
                        (double) comp1 <= (double) comp2;
                    case CARACTER ->               // decimal <= caracter
                        (double) comp1 <= (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CARACTER ->
                switch (comparando2) {
                    case ENTERO ->                 // caracter <= entero
                        (char)((String) comp1).charAt(0) <= (int) comp2;
                    case DECIMAL ->                // caracter <= decimal
                        (char)((String) comp1).charAt(0) <= (double) comp2;
                    case CARACTER ->               // caracter <= caracter
                        (char)((String) comp1).charAt(0) <= (char)((String) comp2).charAt(0);
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            case CADENA ->
                switch (comparando2) {
                    case CADENA ->                 // cadena <= cadena
                        comp1.toString().compareTo(comp2.toString()) <= 0;
                    default ->
                        new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
                };
            default ->
                new Excepcion("Semantico", "No se puede comparar los tipos de datos", this.linea, this.columna);
        };
    }
    
    public RetornoAST ast(AST ast){
        return new RetornoAST("", 0);
    }
    
}
  
