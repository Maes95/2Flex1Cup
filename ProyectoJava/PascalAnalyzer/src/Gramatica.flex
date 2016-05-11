// {Codigo de usuario}

// IMPORTS
import java_cup.runtime.*;

%% // {Opciones y declaraciones}
%unicode
%cup
%line
%column

// DECLARACION DE CLASES
%class AnalizadorLexico

%{
	StringBuffer string = new StringBuffer();
	String inicioComentario = "";

  private Symbol symbol(int type, Object value) {
    return new java_cup.runtime.Symbol(type, yyline, yycolumn, value);
  }

	private void errorLexico(String msg){
		System.err.println("ERROR LEXICO: "+msg);
	}

	private void comentarioAbierto(String tipoComentario){
		System.err.println("ADVERTENCIA EN LEXICO: No se cerro el comentario iniciado en "+inicioComentario+" ("+tipoComentario+")."
											 +"Se asume que el resto del codigo es un comentario."
											 +"Esto puede ocasionar errores irrecuperables en el analisis sintactico");
		yybegin(YYINITIAL);
	}

%}

// Macros

Letter = [a-zA-Z]
DecimalDigit = [0-9]
HexadecimalDigit = [0-9A-F]
OptionalSign = [\+-]?
DecimalInit = {OptionalSign}
HexadecimalInit = \${OptionalSign}
ComparatorOp = (>|<|=|>=|<=|<>)

HexadecimalValue = (({HexadecimalInit}{HexadecimalDigit}+)(\.{HexadecimalDigit}+)?)
DecimalValue = (({DecimalInit}{DecimalDigit}+)(\.{DecimalDigit}+)?)

Identifier = ({Letter}|_)\w*

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]


// Estados
%xstate STRING, KEYCOMMENT, BRACKETCOMMENT

%% //{Reglas lexicas}

<YYINITIAL>	{
	"program"
		{
			return symbol(sym.program, yytext());
		}
	"begin"
		{
			return symbol(sym.begin, yytext());
		}
	"end"
		{
			return symbol(sym.end, yytext());
		}
	"var"
		{
			return symbol(sym.var, yytext());
		}
	"const"
		{
			return symbol(sym.const_t, yytext());
		}
	"if"
		{
			return symbol(sym.if_t, yytext());
		}
	"then"
		{
			return symbol(sym.then_t, yytext());
		}
	"else"
		{
			return symbol(sym.else_t, yytext());
		}
	"while"
		{
			return symbol(sym.while_t, yytext());
		}
	"for"
		{
			return symbol(sym.for_t, yytext());
		}
	"to"
		{
			return symbol(sym.to, yytext());
		}
	"do"
		{
			return symbol(sym.do_t, yytext());
		}
	"case"
		{
			return symbol(sym.case_t, yytext());
		}
	"of"
		{
			return symbol(sym.of, yytext());
		}
	"function"
		{
			return symbol(sym.function, yytext());
		}
	"procedure"
		{
			return symbol(sym.procedure, yytext());
		}
	"type"
		{
			return symbol(sym.type, yytext());
		}
	"record"
		{
			return symbol(sym.record, yytext());
		}
	"array"
		{
			return symbol(sym.array, yytext());
		}
	"INTEGER"
		{
			return symbol(sym.int_name, yytext());
		}
	"REAL"
		{
			return symbol(sym.real_name, yytext());
		}
	"CHARACTER"
		{
			return symbol(sym.char_name, yytext());
		}
	";"
		{
			return symbol(sym.semicolons, yytext());
		}
	":"
		{
			return symbol(sym.colons, yytext());
		}
	":="
		{
			return symbol(sym.asig, yytext());
		}
	"="
		{
			return symbol(sym.equal, yytext());
		}
	","
		{
			return symbol(sym.comma, yytext());
		}
	"."
		{
			return symbol(sym.point, yytext());
		}
	".."
		{
			return symbol(sym.two_points, yytext());
		}
	"("
		{
			return symbol(sym.open_bracket, yytext());
		}
	")"
		{
			return symbol(sym.close_bracket, yytext());
		}
	"["
		{
			return symbol(sym.open_square_bracket, yytext());
		}
	"]"
		{
			return symbol(sym.close_square_bracket, yytext());
		}

	"+" {
			return symbol(sym.plus, yytext());
		}

	"-" {
			return symbol(sym.minus, yytext());
		}

	\* {
			return symbol(sym.product, yytext());
		}

	"div" {
			return symbol(sym.div_op, yytext());
		}

	"mod" {
			return symbol(sym.mod_op, yytext());
		}

	"and" {
			return symbol(sym.and_op, yytext());
		}

	"or" {
			return symbol(sym.or_op, yytext());
		}

	"not"
		{
			return symbol(sym.not_op, yytext());
		}

	{ComparatorOp}
		{
			return symbol(sym.comparator_op, yytext());
		}

	{DecimalValue}
		{
			return symbol(sym.decimal_value, yytext());
		}

	{HexadecimalValue}
		{
			return symbol(sym.hexadecimal_value, yytext());
		}

	{Identifier}
		{
			return symbol(sym.identifier, yytext());
		}

  "'"
    {
			string.setLength(0);
      yybegin(STRING);
    }

	"{"
		{
			yybegin(KEYCOMMENT); inicioComentario = "linea: "+(yyline+1)+", columna: "+yycolumn;
		}

	"(*"
		{
			yybegin(BRACKETCOMMENT); inicioComentario = "linea: "+(yyline+1)+", columna: "+yycolumn;
		}

	{WhiteSpace}                   { /* IGNORAR */ }

}

<KEYCOMMENT>
		{
			"}"   										 	 { yybegin(YYINITIAL); }
			<<EOF>>                      { comentarioAbierto("KeyComment"); }
			[^"}"]*											 { /* IGNORAR */ }
		}

<BRACKETCOMMENT>
		{
			"*)"   										 	 { yybegin(YYINITIAL); }
			<<EOF>>                      { comentarioAbierto("BracketComment"); }
			[^"*)"]*										 { /* IGNORAR */ }
		}

<STRING>
    {
      "'"                            { yybegin(YYINITIAL); return symbol(sym.string_literal, string.toString());}
      "''"	                         { string.append( yytext() );}
      [^\n\r\'\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
			{LineTerminator}               { errorLexico("La cadena de caracteres "+string.toString()+" no se cerro correctamente (Fin de linea encontrado)"); yybegin(YYINITIAL); }

    }

		// CARACTERES NO VÁLIDOS

		[^]                            { errorLexico("Cadena no valida:  \""+yytext()+
		                                                              "\" en la linea "+(yyline+1)+", columna "+yycolumn+1); }
