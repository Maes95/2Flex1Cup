import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Michel
 */
public class JSON {
    
    public static void main(String[] args) throws JSONException{
	
      JSONObject obj = new JSONObject();
      JSONArray children = new JSONArray();
            
      JSONObject obj2 = new JSONObject();
      JSONObject obj3 = new JSONObject();
      JSONObject obj4 = new JSONObject();
      
      obj.put("name","program Ejemplo1Aprobado");
      obj2.put("name","function areaCuadrado ( lado: REAL ) : REAL ;");
      obj3.put("name","procedure intercambio ( v1, v2: INTEGER )  ;");
      obj4.put("name","programa principal");
      
      children.put(obj2);
      children.put(obj3);
      children.put(obj4);

      obj.put("children", children);

      System.out.print(obj.toString());
   }
}
