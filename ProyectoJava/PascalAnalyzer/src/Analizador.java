import java.io.IOException;
import java.io.PrintWriter;
/**
*/
public class Analizador{
	public static void main(String argv[])
	{
		if (argv.length == 0) {
			System.out.println("Inserta nombre de archivo\n"+
			"( Usage : java Analizador <inputfile> )");
		} else {
                    for (String argv1 : argv) {
                        AnalizadorLexico lexico = null;
                        try {
                            lexico = new AnalizadorLexico(new java.io.FileReader(argv1));
                            parser sintactico = new parser(lexico);
                            sintactico.fileName = argv1;
                            sintactico.parse();
                        } catch (java.io.FileNotFoundException e) {
                            System.out.println("Archivo \"" + argv1 + "\" no encontrado.");
                        } catch (java.io.IOException e) {
                            System.out.println("Error durante la lectura del"
                                    + " archivo \"" + argv1 + "\".");
                            e.printStackTrace();
                        }catch (Exception e) {
                            System.out.println("Excepcion:");
                            e.printStackTrace();
                        }
                    }
		}
	}
}