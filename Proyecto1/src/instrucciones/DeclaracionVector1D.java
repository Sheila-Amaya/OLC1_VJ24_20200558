/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import Errores.Excepcion;
import abstracto.Instruccion;
import simbolo.AST;
import simbolo.Arbol;
import simbolo.RetornoAST;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;
import java.util.LinkedList;

/**
 *
 * @author eliza
 */
public class DeclaracionVector1D extends Instruccion {
    public String id;
    public LinkedList<Instruccion> valores;
    public String mutabilidad;

    public DeclaracionVector1D(String mutabilidad, String id, Tipo tipo, LinkedList<Instruccion> valores, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.valores = valores;
        this.mutabilidad = mutabilidad;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        LinkedList<Object> vectorValores = new LinkedList<>();
        for (Instruccion valor : valores) {
            Object resultado = valor.interpretar(arbol, tabla);
            if (resultado instanceof Excepcion) {
                return resultado;
            }
            vectorValores.add(resultado);
        }

        if (!validarTipos(vectorValores)) {
            return new Excepcion("Semantico", "Error de tipos de datos en la declaracion del vector", linea, columna);
        }

        Simbolo s = new Simbolo(this.tipo, this.id, vectorValores);
        if (this.mutabilidad != null && this.mutabilidad.toLowerCase().equals("const")) {
            s.setMutabilidad(false);
        } else {
            s.setMutabilidad(true);
        }

        boolean create = tabla.agregarVariable(s);
        if (!create) {
            return new Excepcion("Semantico", "El vector " + this.id + " ya existe", linea, columna);
        }

        return null;
    }

    private boolean validarTipos(LinkedList<Object> vectorValores) {
        for (Object valor : vectorValores) {
            if (!esTipoCompatible(valor, this.tipo)) {
                return false;
            }
        }
        return true;
    }

    private boolean esTipoCompatible(Object valor, Tipo tipo) {
        switch (tipo.getTipo()) {
            case ENTERO:
                return valor instanceof Integer;
            case DECIMAL:
                return valor instanceof Double;
            case BOOLEANO:
                return valor instanceof Boolean;
            case CARACTER:
                return valor instanceof Character;
            case CADENA:
                return valor instanceof String;
            default:
                return false;
        }
    }

    @Override
    public RetornoAST ast(AST ast) {
        int id = ast.getNewID();
        String dot = "nodo_" + id + "[label=\"DECLARACION_VECTOR1D\"];";

        dot += "\nnodo_" + id + "_id[label=\"" + this.id + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_id;";

        dot += "\nnodo_" + id + "_muta[label=\"" + this.mutabilidad + "\"];";
        dot += "\nnodo_" + id + " -> nodo_" + id + "_muta;";

        for (Instruccion valor : valores) {
            RetornoAST valorAST = valor.ast(ast);
            dot += "\n" + valorAST.dot;
            dot += "\nnodo_" + id + " -> nodo_" + valorAST.id + ";";
        }

        return new RetornoAST(dot, id);
    }
}