package Errores;

public class Excepcion{

    public String tipo; //tipo de error
    public String descripcion; //descripcion del error
    public int linea; //linea donde se produce el error
    public int columna; //columna donde se produce el error


    public Excepcion (String tipo, String descripcion, int linea, int columna){
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }
    
    
    @Override
    public String toString(){
        return this.tipo + ": " + this.descripcion + " en la linea " + this.linea + " y columna " + this.columna;
    }
    
}
