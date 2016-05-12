/**
 *
 * @author Michel
 */
public class Expression {

    public static HTMLGenerator htmlGenerator;
    
    private String error = "Expresion con tipos incompatibles: ";
    String tipo;
    String html;

    public Expression(String op, Expression exp1, Expression exp2){
        this.html = exp1.html + op + exp2.html;
        this.tipo = checkType(op);
    }

    public Expression(String tipo, String value){
        this.tipo = tipo;
        this.html = value;
    }

    private String checkType(String s){
        switch(s){
            case " + ":
            case " - ":
            case " * ":
            case " div ":
            case " mod ":
                return "INTEGER";
            case " and ":
            case " or ":
                return "BOOLEAN";
            default:
                return "BOOLEAN";
        }
    }
}
