/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import Errores.*;
import abstracto.Instruccion;
import simbolo.*;

/**
 *
 * @author eliza
 */
public class Aritmeticas extends Instruccion {

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion opUnico;

    // Constructor para operaciones negacion
    public Aritmeticas(Instruccion opUnico,OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.operacion = operacion;
        this.opUnico = opUnico;
    }

    // Constructor para cualquier otra operacion menos negacion
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.opUnico != null) {
            Unico = this.opUnico.interpretar(arbol, tabla);
            if (Unico instanceof Excepcion) {
                return Unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Excepcion) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Excepcion) {
                return opDer;
            }
        }

        return switch (operacion) {
            case SUMA ->
                this.suma(opIzq, opDer);  //funcion
            case RESTA ->
                this.resta(opIzq, opDer);
            case MULTIPLICACION ->
                this.multiplicacion(opIzq, opDer);
            case DIVISION ->
                this.division(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            case MODULO ->
                this.modulo(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            default ->
                new Excepcion("Semantico", "Operacion no valida", this.linea, this.columna);
        };
    }

    public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {                          //entero + entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Integer.parseInt(op1.toString()) + Integer.parseInt(op2.toString());                    //==retorna entero
                    }
                    case DECIMAL -> {                        //entero + decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) + Double.parseDouble(op2.toString());                 //==retorna decimal 
                    }
                    case BOOLEANO -> {                       //entero + booleano
                        return new Excepcion("Semantico", "La suma de un entero y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                         //entero + cadena
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(op1) + String.valueOf(op2);       //==retorna cadena
                    }
                    case CARACTER -> {                       //entero + caracter
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) ((String) op2).charAt(0);               //==retorna entero 
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la suma", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {                            //decimal + entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) + Double.parseDouble(op2.toString());               //==retorna decimal
                    }
                    case DECIMAL -> {                          //decimal + decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) + Double.parseDouble(op2.toString());                //==retorna decimal
                    }
                    case BOOLEANO -> {                         //decimal + booleano
                        return new Excepcion("Semantico", "La suma de un decimal y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                            //decimal + cadena
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf((double) op1) + (String) op2; //==retorna cadena
                    }
                    case CARACTER -> {                          //decimal + caracter
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (int) ((String) op2).charAt(0);           //==retorna decimal
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la suma", this.linea, this.columna);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case ENTERO -> {                            //booleano + entero
                        return new Excepcion("Semantico", "La suma de un booleano y un entero no está permitida", this.linea, this.columna);
                    }
                    case DECIMAL -> {                          //booleano + decimal
                        return new Excepcion("Semantico", "La suma de un booleano y un decimal no está permitida", this.linea, this.columna);
                    }
                    case BOOLEANO -> {                         //booleano + booleano
                        return new Excepcion("Semantico", "La suma de dos booleanos no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                           //booleano + cadena
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();           //==retorna cadena
                    }
                    case CARACTER -> {                         //booleano + caracter
                        return new Excepcion("Semantico", "La suma de un booleano y un caracter no está permitida", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la suma", this.linea, this.columna);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case ENTERO -> {                            //cadena + entero
                        this.tipo.setTipo(tipoDato.CADENA);             //==retorna cadena
                        return (String) op1 + String.valueOf((int) op2);
                    }
                    case DECIMAL -> {                          //cadena + decimal
                        this.tipo.setTipo(tipoDato.CADENA);
                        return (String) op1 + String.valueOf((double) op2); //==retorna cadena
                    }
                    case BOOLEANO -> {                         //cadena + booleano
                        this.tipo.setTipo(tipoDato.CADENA);
                        return String.valueOf(op1) + String.valueOf(op2);  //==retorna cadena
                    }
                    case CADENA -> {                           //cadena + cadena
                        this.tipo.setTipo(tipoDato.CADENA);
                        return (String) op1 + (String) op2;                 //==retorna cadena
                    }
                    case CARACTER -> {                          //cadena + caracter
                        this.tipo.setTipo(tipoDato.CADENA);
                        return  op1.toString() + op2.toString();                 //==retorna cadena
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la suma", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {                            //caracter + entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) ((Character) op1) + (int) op2;                    //==retorna entero
                    }
                    case DECIMAL -> {                          //caracter + decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (double) op2;                //==retorna decimal
                    }
                    case BOOLEANO -> {                         //caracter + booleano
                        return new Excepcion("Semantico", "La suma de un caracter y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                               //caracter + cadena
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();                 //==retorna cadena
                    }
                    case CARACTER -> {                         //caracter + caracter
                        this.tipo.setTipo(tipoDato.CADENA);
                        return  op1.toString() + op2.toString();                   //==retorna cadena
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la suma", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la suma", this.linea, this.columna);
            }
        }
    } // fin suma

    public Object resta(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {                          //entero - entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Integer.parseInt(op1.toString()) - Integer.parseInt(op2.toString());                    //==retorna entero
                    }
                    case DECIMAL -> {                             //entero - decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) - Double.parseDouble(op2.toString());                 //==retorna decimal 
                    }
                    case BOOLEANO -> {                       //entero - booleano
                        return new Excepcion("Semantico", "La resta de un entero y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                         //entero - cadena
                        return new Excepcion("Semantico", "La resta de un entero y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                       //entero - caracter
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 - (int) ((String) op2).charAt(0);                 //==retorna entero
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la resta", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {                            //decimal - entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) - Double.parseDouble(op2.toString());                   //==retorna decimal
                    }
                    case DECIMAL -> {                          //decimal - decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) - Double.parseDouble(op2.toString());                //==retorna decimal
                    }
                    case BOOLEANO -> {                         //decimal - booleano
                        return new Excepcion("Semantico", "La resta de un decimal y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                            //decimal - cadena
                        return new Excepcion("Semantico", "La resta de un decimal y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                          //decimal - caracter
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (int) ((String) op2).charAt(0);      //==retorna decimal
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la resta", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {                            //caracter - entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) ((String) op1).charAt(0)- (int) op2 ;                  //==retorna entero
                    }
                    case DECIMAL -> {                          //caracter - decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) ((String) op1).charAt(0) - (double) op2 ;                    //==retorna decimal
                    }
                    case BOOLEANO -> {                         //caracter - booleano
                        return new Excepcion("Semantico", "La resta de un caracter y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                           //caracter - cadena
                        return new Excepcion("Semantico", "La resta de un caracter y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                         //caracter - caracter
                        return new Excepcion("Semantico", "La resta de un caracter y un caracter no está permitida", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la resta", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la resta", this.linea, this.columna);
            }

        }

    } // fin resta

    public Object multiplicacion(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {                          //entero * entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Integer.parseInt(op1.toString()) * Integer.parseInt(op2.toString());                    //==retorna entero
                    }
                    case DECIMAL -> {                        //entero * decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) * Double.parseDouble(op2.toString());                 //==retorna decimal 
                    }
                    case BOOLEANO -> {                       //entero * booleano
                        return new Excepcion("Semantico", "La multiplicacion de un entero y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                         //entero * cadena
                        return new Excepcion("Semantico", "La multiplicacion de un entero y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                       //entero * caracter
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 * (int) ((String) op2).charAt(0); //==retorna entero
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la multiplicacion", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {                            //decimal * entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) * Double.parseDouble(op2.toString());                   //==retorna decimal
                    }
                    case DECIMAL -> {                          //decimal * decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) * Double.parseDouble(op2.toString());                //==retorna decimal
                    }
                    case BOOLEANO -> {                         //decimal * booleano
                        return new Excepcion("Semantico", "La multiplicacion de un decimal y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                            //decimal * cadena
                        return new Excepcion("Semantico", "La multiplicacion de un decimal y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                          //decimal * caracter
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (int) ((String) op2).charAt(0);   //==retorna decimal
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la multiplicacion", this.linea, this.columna);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {                            //caracter * entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) ((String) op1).charAt(0) * (int) op2 ;                     //==retorna entero
                    }
                    case DECIMAL -> {                          //caracter * decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) ((String) op1).charAt(0)* (double) op2 ;                      //==retorna decimal
                    }
                    case BOOLEANO -> {                         //caracter * booleano
                        return new Excepcion("Semantico", "La multiplicacion de un caracter y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                           //caracter * cadena
                        return new Excepcion("Semantico", "La multiplicacion de un caracter y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                         //caracter * caracter
                        return new Excepcion("Semantico", "La multiplicacion de un caracter y un caracter no está permitida", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la multiplicacion", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la multiplicacion", this.linea, this.columna);
            }
        }
    } // fin de multiplicacion


    public Object division(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {                          //entero / entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());         //==retorna decimal
                    }
                    case DECIMAL -> {                        //entero / decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());              //==retorna decimal 
                    }
                    case BOOLEANO -> {                       //entero / booleano
                        return new Excepcion("Semantico", "La division de un entero y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                         //entero / cadena
                        return new Excepcion("Semantico", "La division de un entero y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                       //entero / caracter
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return  (double) op1 / (double) ((String) op2).charAt(0);        //==retorna decimal
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la division", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {                            //decimal / entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());                 //==retorna decimal
                    }
                    case DECIMAL -> {                          //decimal / decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());             //==retorna decimal
                    }
                    case BOOLEANO -> {                         //decimal / booleano
                        return new Excepcion("Semantico", "La division de un decimal y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                            //decimal / cadena
                        return new Excepcion("Semantico", "La division de un decimal y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                          //decimal / caracter
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 / (int) ((String) op2).charAt(0);     //==retorna decimal
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la division", this.linea, this.columna);
                    }
                }
            }
            case CARACTER->{
                switch (tipo2) {
                    case ENTERO -> {                            //caracter / entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) ((String) op1).charAt(0)/ (int) op2 ;           //==retorna decimal
                    }
                    case DECIMAL -> {                          //caracter / decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) ((String) op1).charAt(0)/ (double) op2 ;               //==retorna decimal
                    }
                    case BOOLEANO -> {                         //caracter / booleano
                        return new Excepcion("Semantico", "La division de un caracter y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                           //caracter / cadena
                        return new Excepcion("Semantico", "La division de un caracter y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                         //caracter / caracter
                        return new Excepcion("Semantico", "La division de un caracter y un caracter no está permitida", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la division", this.linea, this.columna);
                    }
                }
            
            }default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la division", this.linea, this.columna);
            }
        }
    }// fin de division

    public Object potencia(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {                          //entero ^ entero
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Math.pow(Integer.parseInt(op1.toString()), Integer.parseInt(op2.toString()));         //==retorna entero
                    }
                    case DECIMAL -> {                        //entero ^ decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow((int) op1, (double) op2);      //==retorna decimal 
                    }
                    case BOOLEANO -> {                       //entero ^ booleano
                        return new Excepcion("Semantico", "La potencia de un entero y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                         //entero ^ cadena
                        return new Excepcion("Semantico", "La potencia de un entero y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                        //entero ^ caracter
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Math.pow((int) op1, ((String) op2).charAt(0));         //==retorna entero
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la potencia", this.linea, this.columna);
                    }
                }

            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {                            //decimal ^ entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow((double) op1, (double) op2);       //==retorna decimal
                    }
                    case DECIMAL -> {                          //decimal ^ decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow((double) op1, (double) op2);    //==retorna decimal
                    }
                    case BOOLEANO -> {                         //decimal ^ booleano
                        return new Excepcion("Semantico", "La potencia de un decimal y un booleano no está permitida", this.linea, this.columna);
                    }
                    case CADENA -> {                            //decimal ^ cadena
                        return new Excepcion("Semantico", "La potencia de un decimal y una cadena no está permitida", this.linea, this.columna);
                    }
                    case CARACTER -> {                          //decimal ^ caracter
                        return new Excepcion("Semantico", "La potencia de un decimal y un caracter no está permitida", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la potencia", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la potencia", this.linea, this.columna);
            }
        }
    } // fin de potencia

    public Object modulo(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {                          //entero % entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) % Double.parseDouble(op2.toString());        //==retorna decimal
                    }
                    case DECIMAL -> {                        
                        this.tipo.setTipo(tipoDato.DECIMAL);           //entero % decimal
                        return Double.parseDouble(op1.toString()) % Double.parseDouble(op2.toString());             //==retorna decimal
                    }
                    case BOOLEANO -> {                       //entero % booleano
                        return new Excepcion("Semantico", "La operacion modulo no es valida para un entero y un booleano", this.linea, this.columna);
                    }
                    case CADENA -> {                         //entero % cadena
                        return new Excepcion("Semantico", "La operacion modulo no es valida para un entero y una cadena", this.linea, this.columna);
                    }
                    case CARACTER -> {                       //entero % caracter
                        return new Excepcion("Semantico", "La operacion modulo no es valida para un entero y un caracter", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la operacion modulo", this.linea, this.columna);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {                            //decimal % entero
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) % Double.parseDouble(op2.toString());       //==retorna decimal
                    }
                    case DECIMAL -> {                          //decimal % decimal
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) % Double.parseDouble(op2.toString());              //==retorna decimal
                    }
                    case BOOLEANO -> {                         //decimal % booleano
                        return new Excepcion("Semantico", "La operacion modulo no es valida para un decimal y un booleano", this.linea, this.columna);
                    }
                    case CADENA -> {                            //decimal % cadena
                        return new Excepcion("Semantico", "La operacion modulo no es valida para un decimal y una cadena", this.linea, this.columna);
                    }
                    case CARACTER -> {                          //decimal % caracter
                        return new Excepcion("Semantico", "La operacion modulo no es valida para un decimal y un caracter", this.linea, this.columna);
                    }
                    default -> {
                        return new Excepcion("Semantico", "Tipo de dato no valido para la operacion modulo", this.linea, this.columna);
                    }
                }
            }default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la operacion modulo", this.linea, this.columna);
            }
        }
    }

    public Object negacion(Object op1) {
        var tipo1 = this.opUnico.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case DECIMAL -> {
                this.tipo.setTipo(tipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Excepcion("Semantico", "Tipo de dato no valido para la negacion", this.linea, this.columna);
            }
        }
    }
    
    public RetornoAST ast(AST ast){
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"" + this.operacion + "\"];";
        
        if (this.operando1 != null) {
            RetornoAST value1 = this.operando1.ast(ast);
            dot += "\n" + value1.dot;
            dot += "\nnodo_" + id + " -> nodo_" + value1.id + ";";
        }
        if (this.operando2 != null) {
            RetornoAST value2 = this.operando2.ast(ast);
            dot += "\n" + value2.dot;
            dot += "\nnodo_" + id + " -> nodo_" + value2.id + ";";
        }   
        return new RetornoAST(dot, id);
    }

}
