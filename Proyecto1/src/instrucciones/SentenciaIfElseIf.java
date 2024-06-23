package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.*;

public class SentenciaIfElseIf extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private Instruccion elseIf;
    private LinkedList<Instruccion> instruccionesElse;

    public SentenciaIfElseIf(Instruccion condicion, LinkedList<Instruccion> instruccionesIf, Instruccion elseIf, LinkedList<Instruccion> instruccionesElse, int linea, int col) {
        super(new Tipo(condicion.tipo.getTipo()), linea, col);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.elseIf = elseIf;
        this.instruccionesElse = instruccionesElse;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);

        // Validar si la condición es una excepción
        if (cond instanceof Excepcion) {
            return cond;
        }

        // Verificar que la condición sea booleana
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Excepcion("Semantico", "La expresión debe ser booleana", this.linea, this.columna);
        }

        var newTabla = new tablaSimbolos(tabla);

        // Evaluar la condición del primer if
        if ((boolean) cond) {
            for (var i : this.instruccionesIf) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break || resultado instanceof Continue) {
                    return resultado;
                }
                if (resultado instanceof Excepcion) {
                    return resultado;
                }
            }
        } else if (this.elseIf != null) {
            // Evaluar el else if si la condición del primer if es falsa
            var resultadoElseIf = this.elseIf.interpretar(arbol, newTabla);
            if (resultadoElseIf instanceof Break || resultadoElseIf instanceof Continue) {
                return resultadoElseIf;
            }
            if (resultadoElseIf instanceof Excepcion) {
                return resultadoElseIf;
            }
        } else if (this.instruccionesElse != null) {
            // Ejecutar las instrucciones del else si todos los anteriores son falsos
            for (var i : this.instruccionesElse) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Break || resultado instanceof Continue) {
                    return resultado;
                }
                if (resultado instanceof Excepcion) {
                    return resultado;
                }
            }
        }

        return null;
    }
}
