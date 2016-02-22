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
			System.out.println(yytext());
		}
	"begin"
		{
			System.out.println(yytext());
		}
	"end"
		{
			System.out.println(yytext());
		}
	"while"
		{
			System.out.println(yytext());
		}
	"for"
		{
			System.out.println(yytext());
		}
	"do"
		{
			System.out.println(yytext());
		}
	"INTEGER"
		{
			System.out.println(yytext());
		}
	"REAL"
		{
			System.out.println(yytext());
		}
	"function"
		{
			System.out.println(yytext());
		}
	"procedure"
		{
			System.out.println(yytext());
		}
	"VAR"
		{
			System.out.println(yytext());
		}
	";"
		{
			System.out.println(yytext());
		}
	":"
		{
			System.out.println(yytext());
		}
	":="
		{
			System.out.println(yytext());
		}
	","
		{
			System.out.println(yytext());
		}
	"."
		{
			System.out.println(yytext());
		}
	"("
		{
			System.out.println(yytext());
		}
	")"
		{
			System.out.println(yytext());
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