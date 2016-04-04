import java.util.Stack;
import java.util.regex.Pattern;


/**
 *
 * @author Michel & Pablo
 */
public class Regla{

    int numNT;
    String antecedente;
    Stack<String> consecuentes;

    public Regla (String cadena){
      this.numNT = 0;
      String[] array = cadena.split(" <%");
      this.antecedente = array[0];
      this.consecuentes = new Stack<>();
      for (String word : array[1].split("%>")[0].split(" ") ){
        consecuentes.push(word);
        if(!esTerminal(word)){numNT++;}
      }
    }

    private static boolean esTerminal(String cadena){
        return !Pattern.compile("[A-Z_]*").matcher(cadena).matches() 
                || "REAL".equals(cadena)
                || "INTEGER".equals(cadena)
                || "CHAR".equals(cadena);
    }

    public static void printSyntacticTree(Stack<Regla> reglas, String s){
        if (reglas.empty()){return;}
        Regla r = reglas.pop();
        while(!r.consecuentes.empty()){
            String word = r.consecuentes.pop();
            if (esTerminal(word)){
                System.out.println(s + "   |_" + word);
            }else{
                System.out.println(s + "   |_" + word);
                r.numNT--;
                // No incluira la continuación de una rama si es el ultimo hijo (el primero) y es un NO TERMINAL
                if(r.numNT == 0 && r.consecuentes.empty()){
                  printSyntacticTree(reglas, s + "    ");
                }else{
                  printSyntacticTree(reglas, s + "   |");
                }
            }
        }
    }

    /**
     * @param args the command line arguments

    public static void main(String[] args) {
        Regla regla = new Regla("SENTLIST <SENTLIST SENT>");
        Regla regla2 = new Regla("BLQ <DCLLIST begin SENTLIST end>");
        Regla regla3 = new Regla("PRG <program EjemploAprobado ; BLQ .>");
        Stack<Regla> stack = new Stack<>();
        stack.push(regla);
        stack.push(regla2);
        stack.push(regla3);
        System.out.println("_" + regla3.antecedente);
        printSyntacticTree(stack, "");

    }
    */
}
