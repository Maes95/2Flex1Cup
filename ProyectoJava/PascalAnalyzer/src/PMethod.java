import java.util.ArrayList;
/**
 *
 * @author Michel
 */
    
public class PMethod extends ItemHTML{
    String name;
    String cabecera;
    ArrayList<String> variables;

    public PMethod(String text, String html, HTMLGenerator myGenerator) {
        super(text, html, myGenerator);
        this.name = getMethodName(text);
        this.cabecera = getMethodHeader(text);
        this.variables = new ArrayList<>();
    }

    /**
     *  Obtiene el nombre de un método
     * @param s
     * @return 
     */
    private static String getMethodName(String s){
       return s.split("\\s+")[1];
    }



    /**
    *  Obtiene la cabecera de un método
     * @param s
     * @return 
    */
    private static String getMethodHeader (String s){
        return s.split(";")[0];
    }


}
