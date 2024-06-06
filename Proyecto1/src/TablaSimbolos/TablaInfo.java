package TablaSimbolos;
import java.util.ArrayList;

public class TablaInfo {
    private String nombre;
    private String tipo;
    private String valor;
    private int linea;
    private int columna;
    
    private final ArrayList<TablaInfo> tabla;

    public TablaInfo(String nombre, String tipo, String valor, int linea, int columna) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.tabla = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public ArrayList<TablaInfo> getTabla() {
        return tabla;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
