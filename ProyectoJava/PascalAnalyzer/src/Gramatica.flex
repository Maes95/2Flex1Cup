// {C�digo de usuario}

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
public String string_acum = "";
public String string_literal = "";

%}

%eofval{

%eofval}

// Macros
Letter = [a-zA-Z]
DecimalDigit = [0-9]
HexadecimalDigit = [0-9A-F]
OptionalSign = [\+-]?
DecimalInit = {OptionalSign}
HexadecimalInit = \${OptionalSign}
ComparatorOp = (>|<|=|>=|<=|<>)


// Estados
%xstate COMMENT_KEY, COMMENT_BRACKET, LITERAL_CONST

%% //{Reglas l�xicas}

<YYINITIAL>	{
	"program"
		{
			System.out.println("_program");
			return new java_cup.runtime.Symbol(sym.program);
		}
	"begin"
		{
			return new java_cup.runtime.Symbol(sym.begin);
		}
	"end"
		{
			return new java_cup.runtime.Symbol(sym.end);
		}
	"var"
		{
			System.out.println("_var");
			return new java_cup.runtime.Symbol(sym.var);
		}
	"const"
		{
			return new java_cup.runtime.Symbol(sym.const_t);
		}
	"if"
		{
			return new java_cup.runtime.Symbol(sym.if_t);
		}
	"then"
		{
			return new java_cup.runtime.Symbol(sym.then_t);
		}
	"else"
		{
			return new java_cup.runtime.Symbol(sym.else_t);
		}
	"while"
		{
			return new java_cup.runtime.Symbol(sym.while_t);
		}
	"for"
		{
			return new java_cup.runtime.Symbol(sym.for_t);
		}
	"to"
		{
			return new java_cup.runtime.Symbol(sym.to);
		}
	"do"
		{
			return new java_cup.runtime.Symbol(sym.do_t);
		}
	"case"
		{
			return new java_cup.runtime.Symbol(sym.case_t);
		}
	"of"
		{
			return new java_cup.runtime.Symbol(sym.of);
		}
	"function"
		{
			return new java_cup.runtime.Symbol(sym.function);
		}
	"procedure"
		{
			return new java_cup.runtime.Symbol(sym.procedure);
		}
	"type"
		{
			return new java_cup.runtime.Symbol(sym.type);
		}
	"record"
		{
			return new java_cup.runtime.Symbol(sym.record);
		}
	"array"
		{
			return new java_cup.runtime.Symbol(sym.array);
		}
	"INTEGER"
		{
			return new java_cup.runtime.Symbol(sym.int_name);
		}
	"REAL"
		{
			return new java_cup.runtime.Symbol(sym.real_name);
		}
	"CHARACTER"
		{
			return new java_cup.runtime.Symbol(sym.char_name);
		}
	";"
		{
			return new java_cup.runtime.Symbol(sym.semicolons);
		}
	":"
		{
			return new java_cup.runtime.Symbol(sym.colons);
		}
	":="
		{
			return new java_cup.runtime.Symbol(sym.asig);
		}
	"="
		{
			return new java_cup.runtime.Symbol(sym.equal);
		}
	","
		{
			return new java_cup.runtime.Symbol(sym.comma);
		}
	"."
		{
			return new java_cup.runtime.Symbol(sym.point);
		}
	".."
		{
			return new java_cup.runtime.Symbol(sym.two_points);
		}
	"("
		{
			return new java_cup.runtime.Symbol(sym.open_bracket);
		}
	")"
		{
			return new java_cup.runtime.Symbol(sym.close_bracket);
		}
	"["
		{
			return new java_cup.runtime.Symbol(sym.open_square_bracket);
		}
	"]"
		{
			return new java_cup.runtime.Symbol(sym.close_square_bracket);
		}

	"{"
		{
			yybegin(COMMENT_KEY);
		}


	"(*"
		{
			yybegin(COMMENT_BRACKET);
		}

	'
		{
			System.out.println("COMIENZA STRING");
			yybegin(LITERAL_CONST);
		}

	"+" {
			return new java_cup.runtime.Symbol(sym.plus);
		}

	"-" {
			return new java_cup.runtime.Symbol(sym.minus);
		}

	\* {
			return new java_cup.runtime.Symbol(sym.product);
		}

	"div" {
			return new java_cup.runtime.Symbol(sym.div_op);
		}

	"mod" {
			return new java_cup.runtime.Symbol(sym.mod_op);
		}

	"and" {
			return new java_cup.runtime.Symbol(sym.and_op);
		}

	"or" {
			return new java_cup.runtime.Symbol(sym.or_op);
		}

	"not"
		{
			return new java_cup.runtime.Symbol(sym.not_op);
		}

	{ComparatorOp}
		{
			return new java_cup.runtime.Symbol(sym.comparator_op);
		}

	(({DecimalInit}{DecimalDigit}+)(\.{DecimalDigit}+)?)
		{
			return new java_cup.runtime.Symbol(sym.decimal_value);
		}

	(({HexadecimalInit}{HexadecimalDigit}+)(\.{HexadecimalDigit}+)?)
		{
			return new java_cup.runtime.Symbol(sym.hexadecimal_value);
		}

	({Letter}|_)\w*
		{
			System.out.println("_identifier: " + yytext());
			return new java_cup.runtime.Symbol(sym.identifier);
		}

	//Para que las pruebas queden en columnas al hacer System.out.print (los espacios no se imprimir�n)
	// {return new java_cup.runtime.Symbol(sym.lambda);}

	(. | \n | \t | \t\n | \r\n | \r) {
	  }

}

<COMMENT_KEY> {
	"}"	{
			yybegin(YYINITIAL);
		}
}

<COMMENT_BRACKET> {
	"*)" {
			yybegin(YYINITIAL);
		 }
}

<LITERAL_CONST> {
	"''"	{
				//Sustituir en el token '' por '
				string_acum += "'";
			}
	'	{
			string_literal = string_acum;
			string_acum = "";
			yybegin(YYINITIAL);
			return new java_cup.runtime.Symbol(sym.string_literal);
		}
	.	{
			string_acum += yytext();
		}
}
