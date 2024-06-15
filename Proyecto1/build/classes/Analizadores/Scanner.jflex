//lexico
/*1. paquete de importaciones*/
package Analizadores;
import java_cup.runtime.Symbol;
import Errores.Excepcion;
import java.util.ArrayList; //para errores
import Token.TokenInfo;


%%
/*2. COnfiguraciones para el analisis (opciones y declaraciones) */

%{
    //codigo de usuario, clases , variables objetos, arreglos
    public ArrayList<Excepcion> Errores = new ArrayList();

    // Lista para almacenar los tokens
    private ArrayList<TokenInfo> tokens = new ArrayList<>();

    // Método para obtener la lista de tokens
    public ArrayList<TokenInfo> getTokens() {
        return tokens;
    }

    //

%}


//Directivas
%class scanner  // clase analizador del lexico 
%public 
%cup            //integrarse con cup
%char           //conteo de caracteres reconocidos 
%column
%line           //conteo de lineas
%unicode        //tipo de codf. que acepta carat. especiales 
%ignorecase     //no importa si son mayus o mins 


//inicializar varibales-----java...  para fila y columna en 1--jflex 
%init{
    yyline = 1;
    yychar = 1;
%init}

//-----------TOKENS-----------

//Expresiones regulares
EVITAR=[ \r\t]+
DECIMAL = [0-9]+\.[0-9]+
ENTERO = [0-9]+
ID = (\_)*[a-zA-Z][a-zA-Z0-9\_]* //dentificadores (nombres de variables, funciones, etc.) 
CADENA =  \"[^\"]*\"
CHAR = \'[^\']\'
comentarioMultilineal = "/*" [^*]*"*"+([^/*][^*]*"*"+)*"/"
comentarioSimple = \/\/[^\n\r]*[\n\r]?  

// Operadores lógicos
AND = "&&"
OR  = "||"
NOT = "!"
XOR = "^"

// Operadores relacionales 

IGUALACION = "=="
DIFERENCIACION = "!="
MAYOR = ">"
MENOR = "<"
MAYORIGUAL = ">="
MENORIGUAL = "<="

%%

/*3.Reglas semanticas, como se exportaran simbolos y terminales al cup */
"int"        {TokenInfo token = new TokenInfo(yytext(), "INT", yyline, yychar);  tokens.add(token); return new Symbol(sym.INT,yyline,yychar,yytext());}
"double"     {TokenInfo token = new TokenInfo(yytext(), "DOUBLE", yyline, yychar);  tokens.add(token); return new Symbol(sym.DOUBLE,yyline,yychar,yytext());}
"string"     {TokenInfo token = new TokenInfo(yytext(), "STRING", yyline, yychar);  tokens.add(token); return new Symbol(sym.STRING,yyline,yychar,yytext());}
"char"       {TokenInfo token = new TokenInfo(yytext(), "CHAR", yyline, yychar);  tokens.add(token); return new Symbol(sym.CHAR,yyline,yychar,yytext());}
"bool"       {TokenInfo token = new TokenInfo(yytext(), "BOOLEANO", yyline, yychar);  tokens.add(token); return new Symbol(sym.BOOLEANO,yyline,yychar,yytext());}

"true"       {TokenInfo token = new TokenInfo(yytext(), "TRUE", yyline, yychar);  tokens.add(token); return new Symbol(sym.TRUE,yyline,yychar,yytext());}
"false"      {TokenInfo token = new TokenInfo(yytext(), "FALSE", yyline, yychar);  tokens.add(token); return new Symbol(sym.FALSE,yyline,yychar,yytext());}

"var"        {TokenInfo token = new TokenInfo(yytext(), "VAR", yyline, yychar);  tokens.add(token); return new Symbol(sym.VAR,yyline,yychar,yytext());}
"const"      {TokenInfo token = new TokenInfo(yytext(), "CONST", yyline, yychar);  tokens.add(token); return new Symbol(sym.CONST,yyline,yychar,yytext());}

"if"         {TokenInfo token = new TokenInfo(yytext(), "IF", yyline, yychar); tokens.add(token); return new Symbol(sym.IF,yyline,yychar,yytext());}
"else"       {TokenInfo token = new TokenInfo(yytext(), "ELSE", yyline, yychar);  tokens.add(token); return new Symbol(sym.ELSE,yyline,yychar,yytext());}

"match"      {TokenInfo token = new TokenInfo(yytext(), "MATCH", yyline, yychar);  tokens.add(token); return new Symbol(sym.MATCH,yyline,yychar,yytext());}

"while"      {TokenInfo token = new TokenInfo(yytext(), "WHILE", yyline, yychar);  tokens.add(token); return new Symbol(sym.WHILE,yyline,yychar,yytext());}
"for"        {TokenInfo token = new TokenInfo(yytext(), "FOR", yyline, yychar);  tokens.add(token); return new Symbol(sym.FOR,yyline,yychar,yytext());}
"do"         {TokenInfo token = new TokenInfo(yytext(), "DO", yyline, yychar);  tokens.add(token); return new Symbol(sym.DO,yyline,yychar,yytext());}
"break"      {TokenInfo token = new TokenInfo(yytext(), "BREAK", yyline, yychar);  tokens.add(token); return new Symbol(sym.BREAK,yyline,yychar,yytext());}
"continue"   {TokenInfo token = new TokenInfo(yytext(), "CONTINUE", yyline, yychar);  tokens.add(token); return new Symbol(sym.CONTINUE,yyline,yychar,yytext());}
"return"     {TokenInfo token = new TokenInfo(yytext(), "RETURN", yyline, yychar);  tokens.add(token); return new Symbol(sym.RETURN,yyline,yychar,yytext());}

"List"       {TokenInfo token = new TokenInfo(yytext(), "LIST", yyline, yychar);  tokens.add(token); return new Symbol(sym.LIST,yyline,yychar,yytext());}
"new"        {TokenInfo token = new TokenInfo(yytext(), "NEW", yyline, yychar);  tokens.add(token); return new Symbol(sym.NEW,yyline,yychar,yytext());}
"append"     {TokenInfo token = new TokenInfo(yytext(), "APPEND", yyline, yychar);  tokens.add(token); return new Symbol(sym.APPEND,yyline,yychar,yytext());}
"remove"     {TokenInfo token = new TokenInfo(yytext(), "REMOVE", yyline, yychar);  tokens.add(token); return new Symbol(sym.REMOVE,yyline,yychar,yytext());}

"struct"     {TokenInfo token = new TokenInfo(yytext(), "STRUCT", yyline, yychar);  tokens.add(token); return new Symbol(sym.STRUCT,yyline,yychar,yytext());}

"void"       {TokenInfo token = new TokenInfo(yytext(), "VOID", yyline, yychar);  tokens.add(token); return new Symbol(sym.VOID,yyline,yychar,yytext());}
"println"    {TokenInfo token = new TokenInfo(yytext(), "PRINTLN", yyline, yychar);  tokens.add(token); return new Symbol(sym.PRINTLN,yyline,yychar,yytext());}

"round"      {TokenInfo token = new TokenInfo(yytext(), "ROUND", yyline, yychar);  tokens.add(token); return new Symbol(sym.ROUND,yyline,yychar,yytext());}
"length"     {TokenInfo token = new TokenInfo(yytext(), "LENGTH", yyline, yychar);  tokens.add(token); return new Symbol(sym.LENGTH,yyline,yychar,yytext());}
"toString"   {TokenInfo token = new TokenInfo(yytext(), "TOSTRING", yyline, yychar);  tokens.add(token); return new Symbol(sym.TOSTRING,yyline,yychar,yytext());}
"find"       {TokenInfo token = new TokenInfo(yytext(), "FIND", yyline, yychar);  tokens.add(token); return new Symbol(sym.FIND,yyline,yychar,yytext());}

"start_with" {TokenInfo token = new TokenInfo(yytext(), "START_WITH", yyline, yychar);  tokens.add(token); return new Symbol(sym.START_WITH,yyline,yychar,yytext());}

//-------------------

{CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    TokenInfo token = new TokenInfo(cadena, "CADENA", yyline, yychar);
    tokens.add(token);
    return new Symbol(sym.CADENA, yyline, yychar, cadena);
}

{CHAR} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    TokenInfo token = new TokenInfo(caracter, "CARACTER", yyline, yychar);
    tokens.add(token);
    return new Symbol(sym.CARACTER, yyline, yychar, caracter);
}


";"  {TokenInfo token = new TokenInfo(yytext(), "PT_COMA", yyline, yychar); tokens.add(token); return new Symbol(sym.PT_COMA,yyline,yychar, yytext());} 
":"  {TokenInfo token = new TokenInfo(yytext(), "DOS_PT", yyline, yychar);  tokens.add(token); return new Symbol(sym.DOS_PT,yyline,yychar, yytext());} 
"("  {TokenInfo token = new TokenInfo(yytext(), "PAR_IZQ", yyline, yychar);  tokens.add(token); return new Symbol(sym.PAR_IZQ,yyline,yychar, yytext());} 
")"  {TokenInfo token = new TokenInfo(yytext(), "PAR_DER", yyline, yychar);  tokens.add(token); return new Symbol(sym.PAR_DER,yyline,yychar, yytext());} 
"}"  {TokenInfo token = new TokenInfo(yytext(), "LLAV_DER", yyline, yychar);  tokens.add(token); return new Symbol(sym.LLAV_DER,yyline,yychar, yytext());} 
"{"  {TokenInfo token = new TokenInfo(yytext(), "LLAV_IZQ", yyline, yychar);  tokens.add(token); return new Symbol(sym.LLAV_IZQ,yyline,yychar, yytext());} 
"."  {TokenInfo token = new TokenInfo(yytext(), "PUNTO", yyline, yychar);  tokens.add(token); return new Symbol(sym.PUNTO,yyline,yychar, yytext());} 
","  {TokenInfo token = new TokenInfo(yytext(), "COMA", yyline, yychar);  tokens.add(token); return new Symbol(sym.COMA,yyline,yychar, yytext());} 
"["  {TokenInfo token = new TokenInfo(yytext(), "COR_IZQ", yyline, yychar);  tokens.add(token); return new Symbol(sym.COR_IZQ,yyline,yychar, yytext());} 
"]"  {TokenInfo token = new TokenInfo(yytext(), "COR_DER", yyline, yychar);  tokens.add(token); return new Symbol(sym.COR_DER,yyline,yychar, yytext());} 
"="  {TokenInfo token = new TokenInfo(yytext(), "IGUAL", yyline, yychar);  tokens.add(token); return new Symbol(sym.IGUAL,yyline,yychar, yytext());}

"++" {TokenInfo token = new TokenInfo(yytext(), "INC", yyline, yychar); tokens.add(token); return new Symbol(sym.INC, yyline, yychar, yytext());}
"+"  {TokenInfo token = new TokenInfo(yytext(), "MAS", yyline, yychar); tokens.add(token); return new Symbol(sym.MAS, yyline, yychar, yytext());}
"--" {TokenInfo token = new TokenInfo(yytext(), "DEC", yyline, yychar); tokens.add(token); return new Symbol(sym.DEC, yyline, yychar, yytext());}
"-"  {TokenInfo token = new TokenInfo(yytext(), "MENOS", yyline, yychar); tokens.add(token); return new Symbol(sym.MENOS, yyline, yychar, yytext());}
"*"  {TokenInfo token = new TokenInfo(yytext(), "POR", yyline, yychar); tokens.add(token); return new Symbol(sym.POR, yyline, yychar, yytext());}
"/"  {TokenInfo token = new TokenInfo(yytext(), "DIV", yyline, yychar); tokens.add(token); return new Symbol(sym.DIV, yyline, yychar, yytext());}
"%"  {TokenInfo token = new TokenInfo(yytext(), "MOD", yyline, yychar); tokens.add(token); return new Symbol(sym.MOD, yyline, yychar, yytext());}
"**" {TokenInfo token = new TokenInfo(yytext(), "POTENCIA", yyline, yychar); tokens.add(token); return new Symbol(sym.POTENCIA, yyline, yychar, yytext());}

"=>"  {TokenInfo token = new TokenInfo(yytext(), "CASE", yyline, yychar);  tokens.add(token); return new Symbol(sym.CASE,yyline,yychar, yytext());}
"_"  {TokenInfo token = new TokenInfo(yytext(), "DEFAULT", yyline, yychar);  tokens.add(token); return new Symbol(sym.DEFAULT,yyline,yychar, yytext());}


{AND} {TokenInfo token = new TokenInfo(yytext(), "AND", yyline, yychar);  tokens.add(token); return new Symbol(sym.AND, yyline, yychar, yytext()); }
{OR}  {TokenInfo token = new TokenInfo(yytext(), "OR", yyline, yychar);  tokens.add(token); return new Symbol(sym.OR, yyline, yychar, yytext()); }
{NOT} {TokenInfo token = new TokenInfo(yytext(), "NOT", yyline, yychar);  tokens.add(token); return new Symbol(sym.NOT, yyline, yychar, yytext()); }
{XOR} {TokenInfo token = new TokenInfo(yytext(), "XOR", yyline, yychar);  tokens.add(token); return new Symbol(sym.XOR, yyline, yychar, yytext()); }

{DIFERENCIACION} {TokenInfo token = new TokenInfo(yytext(), "DISTINTO", yyline, yychar);  tokens.add(token); return new Symbol(sym.DISTINTO, yyline, yychar, yytext()); }
{IGUALACION}     {TokenInfo token = new TokenInfo(yytext(), "IGUALIGUAL", yyline, yychar);  tokens.add(token); return new Symbol(sym.IGUALIGUAL, yyline, yychar, yytext()); }
{MAYOR}          {TokenInfo token = new TokenInfo(yytext(), "MAYOR", yyline, yychar);  tokens.add(token); return new Symbol(sym.MAYOR, yyline, yychar, yytext()); }
{MENOR}          {TokenInfo token = new TokenInfo(yytext(), "MENOR", yyline, yychar);  tokens.add(token); return new Symbol(sym.MENOR, yyline, yychar, yytext()); }
{MAYORIGUAL}     {TokenInfo token = new TokenInfo(yytext(), "MAYORIGUAL", yyline, yychar);  tokens.add(token); return new Symbol(sym.MAYORIGUAL, yyline, yychar, yytext()); }
{MENORIGUAL}     {TokenInfo token = new TokenInfo(yytext(), "MENORIGUAL", yyline, yychar);  tokens.add(token); return new Symbol(sym.MENORIGUAL, yyline, yychar, yytext()); }

{ENTERO}  {TokenInfo token = new TokenInfo(yytext(), "ENTERO", yyline, yychar); tokens.add(token);return new Symbol(sym.ENTERO,yyline,yychar,yytext());}
{DECIMAL} {TokenInfo token = new TokenInfo(yytext(), "DECIMAL", yyline, yychar); tokens.add(token);return new Symbol(sym.DECIMAL,yyline,yychar,yytext());}
{ID}      {TokenInfo token = new TokenInfo(yytext(), "ID", yyline, yychar); tokens.add(token); return new Symbol(sym.ID,yyline,yychar,yytext());}



\n {yychar=1;}

{EVITAR}                 {}
{comentarioMultilineal}  {}
{comentarioSimple}       {}

//Recuperar errores lexicos  
. {
    //guarda los errores lexicos
    Errores.add(new Excepcion("Lexico","Caracter no valido: "+ yytext(), yyline, yychar));
}
