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

// DECLATRACION DE CLASES
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

%% //{Reglas léxicas}


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