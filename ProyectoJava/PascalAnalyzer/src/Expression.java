/**
 *
 * @author Michel
 */
public class Expression {

    public static HTMLGenerator htmlGenerator;
    private String error = "Expresion con tipos incompatibles: ";

    String tipo;
    String html;
    private boolean comp = false;

    public Expression(String op, Expression exp1, Expression exp2){
        this.html = exp1.html + op + exp2.html;
        this.tipo = checkType(op);
        if(!this.verifyType(exp1, exp2)){
            Expression.htmlGenerator.currentMethod.errores.add(error);
        }
    }

//    public Expression(String op, String exp1, String exp2){
//        this.html = exp1 + op + exp2;
//        this.tipo = checkType(op);
//    }

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
                this.comp = true;
                return "BOOLEAN";
        }
    }

    private boolean verifyType(Expression exp1, Expression exp2){

        if(this.comp){
            error += " se esperaban tipos iguales para su comparación";
            return exp1.tipo.equals(exp2.tipo);
        }
        if("BOOLEAN".equals(this.tipo)){
            error += " se esperaban expresiones de tipo booleano";
            return "BOOLEAN".equals(exp1.tipo) && "BOOLEAN".equals(exp1.tipo);
        }
        if("INTEGER".equals(this.tipo)){
            error += " se esperaban expresiones de tipo entero";
            return "INTEGER".equals(exp1.tipo) && "INTEGER".equals(exp1.tipo);
        }
        error += " tipos no validos";
        return false;
    }
}
