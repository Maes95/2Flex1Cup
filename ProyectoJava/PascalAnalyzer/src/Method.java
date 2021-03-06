import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Michel
 */
    
public class Method{
    String name;
    String cabecera;
    String html;
    
    // Metodo que te declaró en su lista de declaraciones
    Method padre;
    // Tipos definidos en el type del método
    ArrayList<String> defTypes;
    // Mapa de variables declaradas de la funcion
    HashMap<String,String> defVariables;
    // Variables usadas
    ArrayList<String> variables;
    // Indica si es una funcion
    boolean function;

    public Method(String name, Method padre) {
        this.function = false;
        this.padre = padre;
        this.html = "";
        this.name = name;
        this.defTypes = new ArrayList<>();
        this.defVariables = new HashMap<>();
        this.variables = new ArrayList<>();
    }
    
    public void setCabecera(String html){
       String cleanHTML = HTMLGenerator.deleteTags(html);
       this.cabecera = cleanHTML.split(";")[0];
    }

}
