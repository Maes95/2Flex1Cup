title FastTest

del AnalizadorLexicoSintacticoPascal.java
del AnalizadorLexicoSintacticoPascal.class

java -jar jflex-1.6.1.jar Gramatica.flex
javac AnalizadorLexicoSintacticoPascal.java
java AnalizadorLexicoSintacticoPascal EjAprobado.txt

pause
