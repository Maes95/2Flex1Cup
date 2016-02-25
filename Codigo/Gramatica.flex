// {Código de usuario}

// IMPORTS
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.PrintWriter;
import java.io.FileWriter;

%% // {Opciones y declaraciones}
%standalone

// DECLARACION DE CLASES
%class AnalizadorLexicoSintacticoPascal
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
RealFloatPoint = e{OptionalSign}
DecimalInit = {OptionalSign}
HexadecimalInit = \${OptionalSign}
ArithmeticOp = (\+|-|\*|div|mod)
LogicalOp = (or|and)

ComparatorOp = (>|<|=|>=|<=|<>)


// Estados
%xstate COMMENT_KEY, COMMENT_BRACKET, LITERAL_CONST

%% //{Reglas léxicas}

<YYINITIAL>	{
	"program"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"begin"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"end"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"var"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"const"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"if"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"then"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"while"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"for"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"to"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"do"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"case"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"of"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"function"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"procedure"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"type"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"INTEGER"
		{
			System.out.println("TBAS: " + yytext());
		}
	"REAL"
		{
			System.out.println("TBAS: " + yytext());
		}
	"CHARACTER"
		{
			System.out.println("TBAS: " + yytext());
		}
	";"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	":"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	":="
		{
			System.out.println("RESERVADA: " + yytext());
		}
	","
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"."
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"("
		{
			System.out.println("RESERVADA: " + yytext());
		}
	")"
		{
			System.out.println("RESERVADA: " + yytext());
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
			System.out.println("OPERADOR ARITMETICO: " + yytext());
		}

	{ComparatorOp}
		{
			System.out.println("OPERADOR DE COMPARACION: " + yytext());
		}

	{LogicalOp}
		{
			System.out.println("OPERADOR LÓGICO: " + yytext());
		}
	"not"
		{
			System.out.println("OPERADOR LÓGICO NOT: " + yytext());
		}

	(({DecimalInit}{DecimalDigit}+)(\.{DecimalDigit}+)?)
		{
			System.out.println("VALOR DECIMAL: " + yytext());
		}

	(({HexadecimalInit}{HexadecimalDigit}+)(\.{HexadecimalDigit}+)?)
		{
			System.out.println("VALOR HEXADECIMAL: " + yytext());
		}

	({Letter}|_)\w*
		{
			System.out.println("IDENTIFICADOR: " + yytext());
		}
	" " {}
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
