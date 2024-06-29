```jflex

<instrucciones ::= instrucciones instruccion 
                 | instruccion

instruccion ::= declaracion_id ";" 
                | asignacion_id ";" 
                | sentencia_if 
                | sentencia_match 
                | sentencia_while 
                | sentencia_for 
                | sentencia_do_while ";" 
                | inc_dec ";" 
                | imprimir ";" 
                | declaracion_vector ";" 
                | asignacion_vl ";" 
                | declaracion_listas ";" 
                | append ";" 
                | struct ";" 
                | instanciar_struct ";" 
                | asignacion_struct ";" 
                | funcion 
                | metodo 
                | llamada ";" 
                | start_with ";" 
                | "RETURN" expresion ";" 
                | "RETURN" ";" 
                | "CONTINUE" ";" 
                | "BREAK" ";" 
                | "error" ";"

declaracion_id ::= mutabilidad "ID" ":" tipo_datos "=" expresion 
                 | mutabilidad "ID" ":" tipo_datos

mutabilidad ::= "VAR" 
              | "CONST"

asignacion_id ::= "ID" "=" expresion

sentencia_if ::= "IF" "(" expresion ")" "{" instrucciones "}" "ELSE" sentencia_if 
               | "IF" "(" expresion ")" "{" instrucciones "}" "ELSE" "{" instrucciones "}" 
               | "IF" "(" expresion ")" "{" instrucciones "}"

sentencia_match ::= "MATCH" expresion entornos

entornos ::= "{" cases_default "}" 
             | "{ }"

cases_default ::= cases default 
                | cases 
                | default

cases ::= cases case 
        | case

case ::= expresion "CASE" "{" instrucciones "}"

default ::= "DEFAULT" "CASE" "{" instrucciones "}"

sentencia_while ::= "WHILE" "(" expresion ")" "{" instrucciones "}"

sentencia_for ::= "FOR" "(" asignacion_id ";" expresion ";" inc_dec ")" "{" instrucciones "}"

sentencia_do_while ::= "DO" "{" instrucciones "}" "WHILE" "(" expresion ")"

imprimir ::= "PRINTLN" "(" expresion ")"

expresion ::= aritmeticos 
              | relacionales 
              | logicos 
              | casteo 
              | "ID" 
              | "ENTERO" 
              | "DECIMAL" 
              | "TRUE" 
              | "FALSE" 
              | "CADENA" 
              | "CARACTER" 
              | "(" expresion ")" 
              | llamada ";" 
              | acceso_vl 
              | remove 
              | acceso_struct 
              | find 
              | round 
              | length 
              | toString

aritmeticos ::= expresion "+" expresion 
                | expresion "-" expresion 
                | expresion "*" expresion 
                | expresion "/" expresion 
                | expresion "%" expresion 
                | expresion "**" expresion 
                | "-" expresion

relacionales ::= expresion "==" expresion 
                 | expresion "!=" expresion 
                 | expresion "" expresion 
                 | expresion "" expresion 
                 | expresion "=" expresion 
                 | expresion "=" expresion

logicos ::= expresion "OR" expresion 
            | expresion "AND" expresion 
            | expresion "XOR" expresion 
            | "NOT" expresion

casteo ::= "(" tipo_datos ")" expresion

inc_dec ::= "ID" "++" 
          | "ID" "--"

tipo_datos ::= "INT" 
               | "DOUBLE" 
               | "BOOL" 
               | "STRING" 
               | "CHAR"

declaracion_vector ::= mutabilidad "ID" ":" tipo_datos "[" "]" "=" "[" lista_valores "]" 
                    | mutabilidad "ID" ":" tipo_datos "[" "]" "[" "]" "=" "[" lista_valores2 "]"

lista_valores ::= lista_valores "," expresion 
                | expresion

lista_valores2 ::= lista_valores2 "," "[" lista_valores "]" 
                | "[" lista_valores "]"

acceso_vl ::= "ID" "[" expresion "]" 
            | "ID" "[" expresion "]" "[" expresion "]"

asignacion_vl ::= "ID" "[" expresion "]" "=" expresion 
                | "ID" "[" expresion "]" "[" expresion "]" "=" expresion

declaracion_listas ::= "LIST" "<" tipo_datos ">" "ID" "=" "NEW" "LIST" "(" ")"

append ::= "ID" "." "APPEND" "(" expresion ")"

remove ::= "ID" "." "REMOVE" "(" expresion ")"

struct ::= "STRUCT" "{" lista_structs "}" "ID"

lista_structs ::= lista_structs campos 
                | campos

campos ::= "ID" ":" tipo_datos ";"

instanciar_struct ::= mutabilidad "ID" ":" "ID" "=" "{" valores_struct "}"

valores_struct ::= valores_struct "," valor_struc 
                 | valor_struc

valor_struc ::= "ID" ":" expresion

acceso_struct ::= "ID" "." "ID"

asignacion_struct ::= "ID" "." "ID" "=" expresion

funcion ::= tipo_datos "ID" "(" parametros ")" "{" instrucciones "}"

parametros ::= parametros "," tipo_datos "ID" 
             | tipo_datos "ID"

metodo ::= "VOID" "ID" "(" parametros ")" "{" instrucciones "}" 
         | "VOID" "ID" "(" ")" "{" instrucciones "}"

parametros_llamada ::= parametros_llamada "," expresion 
                     | expresion

llamada ::= "ID" "(" parametros_llamada ")" 
          | "ID" "(" ")"

round ::= "ROUND" "(" expresion ")"

length ::= "LENGTH" "(" expresion ")"

toString ::= "TOSTRING" "(" expresion ")"

find ::= "ID" "." "FIND" "(" expresion ")"

start_with ::= "START_WITH" "ID" "(" parametros_llamada ")" 
             | "START_WITH" "ID" "(" ")"

```
