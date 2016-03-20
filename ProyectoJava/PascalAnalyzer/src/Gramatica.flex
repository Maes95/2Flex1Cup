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

	private Symbol symbol(int type) {
    return new java_cup.runtime.Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new java_cup.runtime.Symbol(type, yyline+1, yycolumn+1, value);
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

Identifier = ({Letter}|_)\w*

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
			return symbol(sym.program);
		}
	"begin"
		{
			return symbol(sym.begin);
		}
	"end"
		{
			return symbol(sym.end);
		}
	"var"
		{
			System.out.println("_var");
			return symbol(sym.var);
		}
	"const"
		{
			return symbol(sym.const_t);
		}
	"if"
		{
			return symbol(sym.if_t);
		}
	"then"
		{
			return symbol(sym.then_t);
		}
	"else"
		{
			return symbol(sym.else_t);
		}
	"while"
		{
			return symbol(sym.while_t);
		}
	"for"
		{
			return symbol(sym.for_t);
		}
	"to"
		{
			return symbol(sym.to);
		}
	"do"
		{
			return symbol(sym.do_t);
		}
	"case"
		{
			return symbol(sym.case_t);
		}
	"of"
		{
			return symbol(sym.of);
		}
	"function"
		{
			return symbol(sym.function);
		}
	"procedure"
		{
			return symbol(sym.procedure);
		}
	"type"
		{
			return symbol(sym.type);
		}
	"record"
		{
			return symbol(sym.record);
		}
	"array"
		{
			return symbol(sym.array);
		}
	"INTEGER"
		{
			return symbol(sym.int_name);
		}
	"REAL"
		{
			return symbol(sym.real_name);
		}
	"CHARACTER"
		{
			return symbol(sym.char_name);
		}
	";"
		{
			return symbol(sym.semicolons);
		}
	":"
		{
			return symbol(sym.colons);
		}
	":="
		{
			return symbol(sym.asig);
		}
	"="
		{
			return symbol(sym.equal);
		}
	","
		{
			return symbol(sym.comma);
		}
	"."
		{
			return symbol(sym.point);
		}
	".."
		{
			return symbol(sym.two_points);
		}
	"("
		{
			return symbol(sym.open_bracket);
		}
	")"
		{
			return symbol(sym.close_bracket);
		}
	"["
		{
			return symbol(sym.open_square_bracket);
		}
	"]"
		{
			return symbol(sym.close_square_bracket);
		}

	"+" {
			return symbol(sym.plus);
		}

	"-" {
			return symbol(sym.minus);
		}

	\* {
			return symbol(sym.product);
		}

	"div" {
			return symbol(sym.div_op);
		}

	"mod" {
			return symbol(sym.mod_op);
		}

	"and" {
			return symbol(sym.and_op);
		}

	"or" {
			return symbol(sym.or_op);
		}

	"not"
		{
			return symbol(sym.not_op);
		}

	{ComparatorOp}
		{
			return symbol(sym.comparator_op);
		}

	(({DecimalInit}{DecimalDigit}+)(\.{DecimalDigit}+)?)
		{
			return symbol(sym.decimal_value);
		}

	(({HexadecimalInit}{HexadecimalDigit}+)(\.{HexadecimalDigit}+)?)
		{
			return symbol(sym.hexadecimal_value);
		}

	{Identifier}
		{
			System.out.println("_identifier: " + yytext());
			return symbol(sym.identifier, yytext());
		}

  "'"
    {
      System.out.println("COMIENZA STRING");
			string.setLength(0);
      yybegin(STRING);
    }

  {Comment}                      { /* IGNORAR */ }

	[^]                            { /* IGNORAR */ }

}

<STRING>
    {
      "'"                            { yybegin(YYINITIAL); return symbol(sym.string_literal, string.toString());}
      "''"	                         { string.append('\'');}
      [^\n\r\'\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }

      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
    }
