package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.*;

/**
 * 
 * 
 * @autor eliza
 */
public class For extends Instruccion {
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(condicion.tipo.getTipo()), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var newTabla = new tablaSimbolos(tabla);

        var res1 = this.asignacion.interpretar(arbol, newTabla);
        if (res1 instanceof Excepcion) {
            return res1;
        }

        var cond = this.condicion.interpretar(arbol, newTabla);
        if (cond instanceof Excepcion) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Excepcion("SEMANTICO", "La condicion debe ser bool", this.linea, this.columna);
        }

        while ((boolean) this.condicion.interpretar(arbol, newTabla)) {
            var newTabla2 = new tablaSimbolos(newTabla);

            // Ejecutar instrucciones
            boolean breakFound = false;
            for (Instruccion ins : this.instrucciones) {
                var resIns = ins.interpretar(arbol, newTabla2);
                if (resIns instanceof Excepcion) {
                    return resIns;
                }
                if (resIns instanceof Break) {
                    breakFound = true;
                    break; 
                }
                if (resIns instanceof Continue) {
                    break;
                }
                if(resIns instanceof Return){
                break;
            }
            }

            if (breakFound) {
                break; 
            }

            var act = this.actualizacion.interpretar(arbol, newTabla);
            if (act instanceof Excepcion) {
                return act;
            }
        }

        return null;
    }
    
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"FOR\"];";

        RetornoAST asignacionAST = asignacion.ast(ast);
        dot += "\n" + asignacionAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + asignacionAST.id + ";";

        RetornoAST condicionAST = condicion.ast(ast);
        dot += "\n" + condicionAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + condicionAST.id + ";";

        RetornoAST actualizacionAST = actualizacion.ast(ast);
        dot += "\n" + actualizacionAST.dot;
        dot += "\nnodo_" + id + " -> nodo_" + actualizacionAST.id + ";";

        for (Instruccion instruccion : instrucciones) {
            RetornoAST instruccionAST = instruccion.ast(ast);
            dot += "\n" + instruccionAST.dot;
            dot += "\nnodo_" + id + " -> nodo_" + instruccionAST.id + ";";
        }

        return new RetornoAST(dot, id);
    }
}
    
