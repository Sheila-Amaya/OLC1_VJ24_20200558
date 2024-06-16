```jflex

<ini> ::= <instrucciones>

<instrucciones> ::= <instrucciones> <instruccion>
                  | <instruccion>

<instruccion> ::= <declaracion_id> ";"
                | <asignacion_id> ";"
                | <sentencia_if>
                | <sentencia_match>
                | <sentencia_while>
                | <sentencia_for>
                | <sentencia_do_while>
                | <inc_dec> ";"
                | <imprimir>
                | "RETURN" <expresion> ";"
                | "RETURN" ";"
                | "CONTINUE" ";"
                | "BREAK" ";"
                | <error>

<declaracion_id> ::= <mutabilidad> <ID> ":" <tipo_datos> "=" <expresion>
                   | <mutabilidad> <ID> ":" <tipo_datos>

<mutabilidad> ::= "VAR"
                | "CONST"

<asignacion_id> ::= <ID> "=" <expresion>

<sentencia_if> ::= "IF" "(" <expresion> ")" "{" <instrucciones> "}" "ELSE" <sentencia_if>
                 | "IF" "(" <expresion> ")" "{" <instrucciones> "}" "ELSE" "{" <instrucciones> "}"
                 | "IF" "(" <expresion> ")" "{" <instrucciones> "}"

<sentencia_match> ::= "MATCH" <expresion> <entornos>

<entornos> ::= "{" <cases_default> "}"
             | "{ }"

<cases_default> ::= <cases> <default>
                  | <cases>
                  | <default>

<cases> ::= <cases> <case>
          | <case>

<case> ::= <expresion> "CASE" <entorno>

<default> ::= "DEFAULT" "CASE" <entorno>

<sentencia_while> ::= "WHILE" "(" <expresion> ")" <entorno>

<sentencia_for> ::= "FOR" "(" <asignacion_id> ";" <expresion> ";" <inc_dec> ")" "{" <instrucciones> "}"

<sentencia_do_while> ::= "DO" <entorno> "WHILE" "(" <expresion> ")"

<imprimir> ::= "PRINTLN" "(" <expresion> ")" ";"

<expresion> ::= <aritmeticos>
              | <relacionales>
              | <logicos>
              | <casteo>
              | <ID>
              | <ENTERO>
              | <DECIMAL>
              | "TRUE"
              | "FALSE"
              | <CADENA>
              | <CARACTER>
              | "(" <expresion> ")"

<aritmeticos> ::= <expresion> "+" <expresion>
                | <expresion> "-" <expresion>
                | <expresion> "*" <expresion>
                | <expresion> "/" <expresion>
                | <expresion> "%" <expresion>
                | <expresion> "**" <expresion>
                | "-" <expresion>

<relacionales> ::= <expresion> "==" <expresion>
                 | <expresion> "!=" <expresion>
                 | <expresion> ">" <expresion>
                 | <expresion> "<" <expresion>
                 | <expresion> ">=" <expresion>
                 | <expresion> "<=" <expresion>

<logicos> ::= <expresion> "OR" <expresion>
            | <expresion> "AND" <expresion>
            | <expresion> "XOR" <expresion>
            | "NOT" <expresion>

<casteo> ::= "(" <tipo_datos> ")" <expresion>

<inc_dec> ::= <ID> "++"
            | <ID> "--"

<tipo_datos> ::= "INT"
               | "DOUBLE"
               | "BOOL"
               | "STRING"
               | "CHAR"


```
