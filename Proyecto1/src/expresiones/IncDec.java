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
/**
 *
 * @author eliza
 */
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
            var v = 0;
            switch (signo) {
                case "++":
                    v = Integer.parseInt(valor.getValor().toString()) + 1;
                    valor.setValor(v);
                    break;
                case "--":
                    v = Integer.parseInt(valor.getValor().toString()) - 1;
                    valor.setValor(v);
                    break;
                default:
                    break;
            }
        }
        return null;
    }
}
