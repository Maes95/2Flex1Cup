title FastTest

del AnalizadorLexico.java
del AnalizadorLexico.class

java -jar jflex-1.6.1.jar Gramatica.flex
java -jar java-cup-11b.jar Sintaxis.cup

pause
