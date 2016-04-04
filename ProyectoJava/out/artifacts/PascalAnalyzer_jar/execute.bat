set /P id=INTRODUZCA EL NOMBRE DEL ARCHIVO A ANALIZAR:
java -jar PascalAnalyzer.jar %id% > salida.txt
start notepad "salida.txt"
pause