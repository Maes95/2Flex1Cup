title FastTest

del AnalizadorLexico.java
del AnalizadorLexico.class

java -jar jflex-1.6.1.jar Gramatica.flex
javac AnalizadorLexico.java
java AnalizadorLexico EjAprobado.txt

pause
