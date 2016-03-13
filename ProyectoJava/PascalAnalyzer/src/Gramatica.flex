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

%}

%eofval{
	return(0);
%eofval}

// Macros
Letter = [a-zA-Z]
DecimalDigit = [0-9]
HexadecimalDigit = [0-9A-F]
OptionalSign = [\+-]?
DecimalInit = {OptionalSign}
HexadecimalInit = \${OptionalSign}
ArithmeticOp = (\+|-|\*|div|mod)
LogicalOp = (or|and)

ComparatorOp = (>|<|=|>=|<=|<>)


// Estados
%xstate COMMENT_KEY, COMMENT_BRACKET, LITERAL_CONST

%% //{Reglas l�xicas}

<YYINITIAL>	{
	"program"
		{
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
			return new java_cup.runtime.Symbol(sym.var);
		}
	"const"
		{
			return new java_cup.runtime.Symbol(sym.const);
		}
	"if"
		{
			return new java_cup.runtime.Symbol(sym.if);
		}
	"then"
		{
			return new java_cup.runtime.Symbol(sym.then);
		}
	"else"
		{
			return new java_cup.runtime.Symbol(sym.else);
		}
	"while"
		{
			return new java_cup.runtime.Symbol(sym.while);
		}
	"for"
		{
			return new java_cup.runtime.Symbol(sym.for);
		}
	"to"
		{
			return new java_cup.runtime.Symbol(sym.to);
		}
	"do"
		{
			return new java_cup.runtime.Symbol(sym.do);
		}
	"case"
		{
			return new java_cup.runtime.Symbol(sym.case);
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

	{ArithmeticOp}
		{
			return new java_cup.runtime.Symbol(sym.arithmetic_op);
		}

	{ComparatorOp}
		{
			return new java_cup.runtime.Symbol(sym.comparator_op);
		}

	{LogicalOp}
		{
			return new java_cup.runtime.Symbol(sym.logical_op);
		}
	"not"
		{
			return new java_cup.runtime.Symbol(sym.not_op);
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
			return new java_cup.runtime.Symbol(sym.identifier);
		}

	//Para que las pruebas queden en columnas al hacer System.out.print (los espacios no se imprimir�n)
	. {return new java_cup.runtime.Symbol(sym.lambda);}

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
				System.out.print("'");
			}
	'	{
			System.out.println("\nTERMINA STRING");
			yybegin(YYINITIAL);
		}
	.	{
			System.out.print(yytext());
		}
}
