/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

public class IncDec extends Instruccion {
    private String id;
    private String signo;

    public IncDec(String id, String signo, int linea, int columna) {
        super(new Tipo(tipoDato.ENTERO), linea, columna);
        this.id = id;
        this.signo = signo;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var valor = tabla.getVariable(id);
        if (valor != null) {
            var v = valor.getValor();
            switch (signo) {
                case "++":
                    if (v instanceof Integer) {
                        valor.setValor((int) v + 1);
                    } else if (v instanceof Double) {
                        valor.setValor((double) v + 1);
                    }
                    break;
                case "--":
                    if (v instanceof Integer) {
                        valor.setValor((int) v - 1);
                    } else if (v instanceof Double) {
                        valor.setValor((double) v - 1);
                    }
                    break;
                default:
                    break;
            }
        }
        return null;
    }
}
