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

public String string_acum = "";
public String string_literal = "";
StringBuffer string = new StringBuffer();

%}

// Macros
Letter = [a-zA-Z]
DecimalDigit = [0-9]
HexadecimalDigit = [0-9A-F]
OptionalSign = [\+-]?
DecimalInit = {OptionalSign}
HexadecimalInit = \${OptionalSign}
ComparatorOp = (>|<|=|>=|<=|<>)

Comment = {KeyComment} | {ParenthesisComment}

KeyComment = "{" .* "}"
ParenthesisComment = "(*" .* "*)"


// Estados
%xstate STRING

%% //{Reglas lexicas}

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

  "'"
    {
      System.out.println("COMIENZA STRING");
      yybegin(STRING);
    }

  {Comment}                      { /* IGNORAR */ }

	[^]                            { /* IGNORAR */ }

}

<STRING>
    {
      "'"                            { yybegin(YYINITIAL); return new java_cup.runtime.Symbol(sym.string_literal);}
      "''"	                         { string.append('\'');}
      [^\n\r\'\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }

      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
    }
