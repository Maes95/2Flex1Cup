import java_cup.runtime.*;
%%
%class AnalizadorLexico
%unicode
%cup
%line
%column
%%
"=" {return new java_cup.runtime.Symbol(sym.igual);}
"+" {return new java_cup.runtime.Symbol(sym.op_mas);}
"*" {return new java_cup.runtime.Symbol(sym.op_mul);}
[a-zA-Z]+ {return new java_cup.runtime.Symbol(sym.ident);}
/*errorfallback*/
.|\n {;}
