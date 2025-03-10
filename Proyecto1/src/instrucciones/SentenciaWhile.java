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
public class SentenciaWhile extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public SentenciaWhile(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla);

        while (true) {
            // Validar la condición del while
            var cond = this.condicion.interpretar(arbol, newTabla);
            if (cond instanceof Excepcion) {
                return cond;
            }

            // Verificar que la condición sea booleana
            if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
                return new Excepcion("Semantico", "La condición debe ser booleana", this.linea, this.columna);
            }

            if (!(boolean) cond) {
                break; // Si la condición es falsa, se rompe el bucle while
            }

            var newTabla2 = new tablaSimbolos(newTabla);
            boolean continueFound = false;

            // Ejecutar las instrucciones del while
            for (var instruccion : this.instrucciones) {
                var resultado = instruccion.interpretar(arbol, newTabla2);
                if (resultado instanceof Break) {
                    return null; // Si se encuentra una instrucción 'break', se termina el bucle
                }
                if (resultado instanceof Continue) {
                    continueFound = true;
                    break; // Si se encuentra una instrucción 'continue', se salta a la siguiente iteración
                }
                if (resultado instanceof Return) {
                    return resultado;
                }
                if (resultado instanceof Excepcion) {
                    return resultado;
                }
            }

            if (continueFound) {
                continue; 
            }
        }

        return null;
    }
    
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"WHILE\"];";

        RetornoAST astCondicion = condicion.ast(ast);
        dot += "\nnodo_" + id + "_cond[label=\"CONDICION\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_cond;";
        dot += "\nnodo_" + id + "_cond -> nodo_" + astCondicion.id + ";";
        dot += astCondicion.dot;

        String dotInstrucciones = "";
        for (Instruccion ins : instrucciones) {
            RetornoAST astIns = ins.ast(ast);
            dotInstrucciones += "\nnodo_" + id + " -> nodo_" + astIns.id + ";";
            dot += astIns.dot;
        }

        return new RetornoAST(dot + dotInstrucciones, id);
    }
}
