# AnalizadorLexicoSintacticoPascal

Especificaciones léxicas

Los elementos del lenguaje que aparecen entrecomillados en la gramática, deben aparecer tal cual (sin las dobles comillas) en cualquier programa correctamente escrito en este lenguaje, el resto de elementos se especifican a continuación.

Los identificadores son ristras de símbolos compuestas por letras, dígitos (de base decimal) y guiones bajos "_" (underscore). Empiezan obligatoriamente por una letra o un guión bajo. Ejemplos correctos: contador , contador1 , _acumulador_total_2

Las constantes numéricas pueden ser de dos tipos, representados en la gramática por los símbolos terminales numeric_integer_const y numeric_real_const, y se pueden especificar en dos bases distintas.

Bases posibles: decimal y hexadecimal.
Las constantes numéricas en base decimal (10), estarán compuestas por los dígitos decimales, del 0 al 9, además de los símbolos necesarios en función del tipo al que pertenezcan (enteras o reales).
Las constantes numéricas en base hexadecimal (16), siempre comienzan con el símbolo "$" y estarán compuestas por los dígitos decimales, del 0 al 9 y las letras de la A a la F, además de los símbolos necesarios en función del tipo al que pertenezcan (enteras o reales).
Tipos posibles: enteros y reales.
Las constantes numéricas enteras son una ristra de dígitos, opcionalmente precedidas de un signo + o -.
Las constantes numéricas reales son dos ristras de dígitos, opcionalmente precedidas de un signo + o - y separadas por el punto decimal.
Ejemplos de constantes correctamente escritas:
Enteras:
Base decimal: +123 , -69 , 45
Base hexadecimal: $+123 , $-A6F9 , $FFFF
Reales:
Base decimal: +123.456 , -0.69 , 45.0
Base hexadecimal: $+123.0 , $-E.A6F9 , $0.FFFF
Las constantes literales, representadas en la gramática por el símbolo terminal string_const, son ristras de símbolos entre comillas simples,'contenido de la constante literal'. El contenido de las constantes puede ser cualquier carácter que pueda aparecer en el programa fuente. Si se desea que aparezca la comilla simple como contenido, ésta debe duplicarse, por ejemplo: el contenido de la constante 'constante literal con ''contenido'' entrecomillado' sería: constante literal con 'contenido' entrecomillado

El formato de los comentarios de propósito general es: cualquier carácter que pueda aparecer en el código fuente, entre llaves: { contenido del comentario }, o entre las parejas de símbolos (* y *): (* contenido del comentario *). Lógicamente el contenido del comentario no puede tener los caracteres de finalización del mismo. Este tipo de comentarios pueden aparecer antes o después de cualquier elemento del leguaje, pero nunca dentro.

Sintaxis general

Un programa está compuesto por dos partes: la zona de declaraciones y la zona de sentencias del programa.

   PRG ::= "program" identifier  ";" BLQ "."
   BLQ ::= DCLLIST "begin" SENTLIST "end"
   DCLLIST ::= lambda | DCLLIST ";" DCL
   SENTLIST ::= SENT | SENTLIST ";" SENT   
La zona de declaraciones es una lista de declaraciones de constantes, tipos, variables, procedimientos y/o funciones.

   DCL ::= DEFCTE | DEFVAR | DEFPROC | DEFFUN
   DEFCTE ::= "const" CTELIST
   CTELIST ::= identifier "=" SIMPVALUE ";"
             | CTELIST identifier "=" SIMPVALUE ";"
   SIMPVALUE ::= numeric_integer_const | numeric_real_const | string_const

   DEFVAR ::= "var" DEFVARLIST 
   DEFVARLIST ::= VARLIST ":" TBAS ";"
                | VARLIST ":" TBAS ";" DEFVARLIST
   VARLIST ::= identifier | identifier "," VARLIST
   DEFPROC ::=  "procedure" identifier FORMAL_PARAMLIST ";" BLQ ";" 
   DEFFUN ::=  "function" identifier FORMAL_PARAMLIST ":" TBAS ";" BLQ ";" 
   FORMAL_PARAMLIST ::= lambda | "(" FORMAL_PARAM ")" 
   FORMAL_PARAM ::= VARLIST ":" TBAS 
                  | VARLIST ":" TBAS ";" FORMAL_PARAM
   TBAS ::= "INTEGER" | "REAL" | "CHARACTER"
La zona de sentencias es una lista de sentencias como asignaciones, sentencias de flujo, llamadas a procedimientos y bloques de ejecución anónimos:

   SENT ::= ASIG | PROC_CALL | EXEBLQ
   ASIG ::= ID ":=" EXP 
   ID := identifier
   EXP ::= EXP OP EXP | FACTOR 
   OP ::= OPCOMP | OPLOG | OPARIT 
   OPCOMP ::= "<" | ">" | "<=" | ">=" | "=" | "<>"
   OPARIT ::= "+" | "-" | "*" | "div" | "mod"
   OPLOG ::= "or" | "and"
   FACTOR ::= SIMPVALUE | "not" FACTOR 
            | "(" EXP ")" | identifier SUBPPARAMLIST
   SUBPPARAMLIST ::= lambda | "(" EXPLIST ")"
   EXPLIST ::= EXP | EXP "," EXPLIST
   PROC_CALL ::= identifier SUBPPARAMLIST
   EXEBLQ ::= DCLLIST_BLQ "begin" SENTLIST "end" 
   DCLLIST_BLQ ::= lambda | DCLLIST_BLQ ";" DCL_BLQ
   DCL_BLQ ::= DEFCTE | DEFTYPE | DEFVAR
Especificación de la traducción dirigida por la sintaxis

...

Parte opcional:

Tipos de datos:

Matrices:

   DCL ::= ... | DEFTYPE
   DEFTYPE ::= "type" TYPELIST 
   TYPELIST ::= identifier "=" UDTYPE ";"
              | identifier "=" UDTYPE ";" TYPELIST
   UDTYPE ::= "array" "[" SIMPVALUE ".." SIMPVALUE "]" "of" ALLTYPES
   
   FACTOR ::= ... | identifier "[" EXP "]"
   
   ID ::= ... | identifier "[" EXP "]"

   ALLTYPES ::= TBAS | identifier
   Sustituir TBAS por ALLTYPES en los símbolos: DEFVARLIST, DEFFUN, FORMAL_PARAM
En las declaraciones, el tamaño de la matriz se define como un intervalo. Este intervalo debe definirse con números enteros y no debe ser vacío (el límite inferior debe ser estrictamente menor que el límite superior). Los índices de la matriz deben ser de tipo entero. No se permite la asignación directa de arrays, éstos deben ser copiados elemento a elemento.

Registros:

   DCL ::= ... | DEFTYPE
   DEFTYPE ::= "type" TYPELIST 
   TYPELIST ::= identifier "=" UDTYPE ";"
              | identifier "=" UDTYPE ";" TYPELIST
   UDTYPE ::= "record" DEFVARLIST "end"
   
   FACTOR ::= ... | identifier "." identifier

   ID ::= ... | identifier "." identifier

   ALLTYPES ::= TBAS | identifier
   Sustituir TBAS por ALLTYPES en los símbolos: DEFVARLIST, DEFFUN, FORMAL_PARAM
No se permite realizar asignaciones entre variables de tipo registro. Los registros deben ser copiados campo a campo.

Sentencias de control de flujo:

Sentencia IF:

   SENT ::= ... | COND
   COND ::= "if" EXP "then" SENT ELSECOND
   ELSECOND ::= lambda | "else" SENT
Esta sentencia está gobernada por una expresión que debe ser de tipo booleano.

Sentencia WHILE:

   SENT ::= ... | WLOOP
   WLOOP ::= "while" EXP "do" SENT 

Esta sentencia está gobernada por una expresión que debe ser de tipo booleano.

Sentencia FOR:

   SENT ::= ... | FLOOP
   FLOOP ::= "for" identifier ":=" EXP "to" EXP "do" SENT 
Sólo puede utilizarse una variable de tipo entero como contador del bucle. Las expresiones que delimitan los índices inferior y superior deben ser de tipo entero.

Sentencia CASE:

   SENT ::= ... | CASE
   CASE ::= "case" EXP "of" CASELIST "end"
   CASELIST ::= EXP ":" SENT ";" | EXP ":" SENT ";" CASELIST
Sólo pueden utilizarse expresiones de tipo entero. Las expresiones de los diferentes casos deben ser del mismo tipo que la expresión que gobierna el case.

Especificación de la traducción dirigida por la sintaxis

...
