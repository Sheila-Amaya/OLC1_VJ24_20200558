package Token;
import java.util.ArrayList;

public class TokenInfo {
    private String lexema;
    private String token;
    private int linea;
    private int columna;
    private final ArrayList<TokenInfo> tokens;

    public TokenInfo(String lexema, String token, int linea, int columna) {
        this.lexema = lexema;
        this.token = token;
        this.linea = linea;
        this.columna = columna;
        this.tokens = new ArrayList<>();
    }

    public String getLexema() {
        return lexema;
    }

    public String getToken() {
        return token;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
    
    public ArrayList<TokenInfo> getTokens() {
        return tokens;
    }
}
