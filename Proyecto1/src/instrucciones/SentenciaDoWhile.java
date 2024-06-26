package instrucciones;

import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.*;

/**
 * 
 * 
 * @author eliza
 */

public class SentenciaDoWhile extends Instruccion {

    private LinkedList<Instruccion> instrucciones;
    private Instruccion condicion;

    public SentenciaDoWhile(LinkedList<Instruccion> instrucciones, Instruccion condicion, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.instrucciones = instrucciones;
        this.condicion = condicion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla);
        boolean cond;
        do {
            var newTabla2 = new tablaSimbolos(newTabla);

            // Ejecutar instrucciones
            boolean breakFound = false;
            boolean continueFound = false;
            for (Instruccion ins : this.instrucciones) {
                var resIns = ins.interpretar(arbol, newTabla2);
                if (resIns instanceof Break) {
                    breakFound = true;
                    break; 
                }
                if (resIns instanceof Continue) {
                    continueFound = true;
                    break;
                }
            }

            if (breakFound) {
                break; 
            }

            // Evaluar la condición
            if (!continueFound) {
                cond = (boolean) this.condicion.interpretar(arbol, newTabla);
            } else {
                cond = true; 
            }

            if (cond) {
                newTabla = new tablaSimbolos(tabla);
            }
        } while (cond);

        return null;
    }
    
    public RetornoAST ast(AST ast){
        return new RetornoAST("", 0);
    }
}

