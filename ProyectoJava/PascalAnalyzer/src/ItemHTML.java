/**
 *
 * @author Michel
 */
public class ItemHTML{
    String text;
    String html;
    HTMLGenerator myGenerator;
    
    static ItemHTML statico;

    public ItemHTML(String text, String html,HTMLGenerator myGenerator){
        this.text = text;
        this.myGenerator = myGenerator;
        this.html = html;         
    }

    public String checkBool(String exp){
      return exp;
    }

    public String checkAsig(String id, String exp){
      String s = id + " := " + exp;
      return s;
    }

    public String checkRange(String simpvalue1, String simpvalue2){
      String s = simpvalue1+ " .. " +simpvalue2;
      try{
            int v1 = Integer.parseInt(simpvalue1);
            int v2 = Integer.parseInt(simpvalue2);
            if(v1 <= v2){
              return this.myGenerator.getError(s); // Orden incorrecto
            }
            return  s;
      }catch(NumberFormatException e){
            return this.myGenerator.getError(s);   // No numerico
      }
    }
} 
