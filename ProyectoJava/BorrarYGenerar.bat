title FastClean

cd PascalAnalyzer/src

del AnalizadorLexico.java
del AnalizadorLexico.java~
del parser.java
del sym.java

java -jar jflex-1.6.1.jar Gramatica.flex
java -jar java-cup-11b.jar Sintaxis.cup

pause
