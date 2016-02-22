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
HexadecimalInit = {OptionalSign}\$


// Estados
%xstate COMMENT_KEY, COMMENT_BRACKET

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
	"while"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"for"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"do"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"INTEGER"
		{
			System.out.println("RESERVADA: " + yytext());
		}
	"REAL"
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
	"VAR"
		{
			System.out.println("RESERVADA: " + yytext());
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