/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;
/**
 *
 * @author eliza
 */
import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.AST;
import simbolo.Arbol;
import simbolo.RetornoAST;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;


public class Casteos extends Instruccion {

    private Instruccion expresion;
    private Tipo tipoDestino;

    public Casteos(Tipo tipoDestino, Instruccion expresion, int linea, int col) {
        super(new Tipo(tipoDestino.getTipo()), linea, col);
        this.expresion = expresion;
        this.tipoDestino = tipoDestino;
    }

   @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor = this.expresion.interpretar(arbol, tabla);  
        if (valor instanceof Excepcion) {
            return valor;
        }

        Tipo tipoActual = this.expresion.tipo;

        try {
            if (tipoDestino.getTipo() == tipoDato.ENTERO) {
                if (tipoActual.getTipo() == tipoDato.DECIMAL) {              // decimal a entero
                    return Double.valueOf(valor.toString()).intValue();  
                } else if (tipoActual.getTipo() == tipoDato.CARACTER) {     // carácter a entero
                    return (int) valor.toString().charAt(0);  // Obtiene el valor ASCII del primer carácter de una cadena como entero
                }
            } else if (tipoDestino.getTipo() == tipoDato.DECIMAL) {
                if (tipoActual.getTipo() == tipoDato.ENTERO) {            // entero a decimal
                    return ((Integer) valor).doubleValue();  
                } else if (tipoActual.getTipo() == tipoDato.CARACTER) {   // carácter a decimal
                    return (double) valor.toString().charAt(0);  // Obtiene el valor ASCII del primer carácter de una cadena como decimal
                }
            } else if (tipoDestino.getTipo() == tipoDato.CARACTER) {     
                if (tipoActual.getTipo() == tipoDato.ENTERO) {              // entero a carácter
                    return (char) ((int) valor);  // entero a carácter
                }
            }
        } catch (NumberFormatException e) {
            return new Excepcion("Semantico", "Error de conversión: " + e.getMessage(), this.linea, this.columna);  
        }

        return new Excepcion("Semantico", "No se puede castear el valor de tipo " + tipoActual + " a tipo " + tipoDestino, this.linea, this.columna);  
    }
    

    public RetornoAST ast(AST ast){
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"CASTEO\"];\n";

        RetornoAST valor = this.expresion.ast(ast);
        dot += valor.dot;
        dot += "nodo_" + id + " -> nodo_" + valor.id + ";\n";

        dot += "nodo_" + id + "_tipoDestino[label=\"" + tipoDestino.getTipo().name() + "\"];\n";
        dot += "nodo_" + id + " -> nodo_" + id + "_tipoDestino;\n";

        return new RetornoAST(dot, id);
    }

}