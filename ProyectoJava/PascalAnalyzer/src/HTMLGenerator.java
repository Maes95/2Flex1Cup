import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;

public class HTMLGenerator {

  /**
   *  HTMLGenerator
   *
   *  Motivación de la clase:
   *    - Almacenar recursos reconocidos por CUP
   *    - Proporcionar métodos de construcción de HTML al CUP
   *    - Mantener estados del análisis sintáctico
   *    - Generar a partir de los recursos un HTML final
   *
   */

    private final String CIERRE_HTML =  "</div>\n" +
                                        "</div>\n" +
                                        "</div>\n" +
                                        "<div class='row' style='height: 5em;'></div>\n\n" +
                                        "<script>$('a[href^=\"#\"]').click(function(e){ \n   $('.selected').removeClass('selected');\n" +
                                        "var name = e.target.getAttribute('href').substring(1);\n   $('a[name=\"' + name + '\"]').find('span[class=\"ident\"]').addClass('selected');\n" +
                                        "$('html, body').animate({scrollTop: $('[name=\"' + name + '\"]').offset().top}, 250);});</script>" +
                                        "</body>\n" +
                                        "</html>";


    private final String CABECERA_HASTA_BODY;
    private String fileName;        // Nombre del fichero fuente (y de salida)

    // Variables de almacenamiento

    private String mainProgram;      // Programa principal
    private String mainProgramDcl;   // Declaración del programa principal
    public HashMap<String, Method> methods;
    public Method currentMethod;

    // Variables de estado

    public boolean sentCond;        // Indica si se esta dentro de una sentencia de control de flujo
    public int indentLevel;         // Nivel de identación actual
    private boolean openDiv;

    // Constructor

    public HTMLGenerator (String fileName){
        this.currentMethod = new Method("Principal", null);
        this.fileName = fileName;
        this.mainProgramDcl = "";
        this.methods = new HashMap<>();
        this.indentLevel = 0;
        this.sentCond = false;
        this.openDiv = false;
        this.fileName = this.fileName.replaceAll("(src/|.txt)", "");

        this.CABECERA_HASTA_BODY =  "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<meta charset='UTF-8'/>\n" +
                                    "<meta name='viewport' content='width=device-width, initial-scale=1'>"+
                                    "<title>"+this.fileName+"</title>\n" +
                                    APIFrontEnd.getLibraries() +
                                    APIFrontEnd.getInitialStyle() +
                                    APIFrontEnd.getScripts() +
                                    "</head>\n\n" +
                                    "<body>\n" +
                                    "<div class='row' style='height: 5em;'></div>\n" +
                                    "<div class='row'>\n" +
                                    "<div class='col-md-8 col-md-offset-2'>\n" +
                                    "<div class='ui raised segments'>\n";
    }


    /*********************************************************************************************************
                                           METODOS DE FORMATEO A HTML (CONSTRUCTORES)
                                       Estos métodos rellenan los parametros de la clase
     *********************************************************************************************************/

    public void getMainProgram(String s){
        this.mainProgram = s;
    }

   /**
     * Comienza el ámbito de un procedimiento o función
     * @param name
     * @param isFunction
     */

    public void addMethod(String name, boolean isFunction){
        Method newFunc = new Method(name, this.currentMethod);
        newFunc.function = isFunction;
        if(isMain()){
           this.methods.put(newFunc.name, newFunc);
        }else{
           this.indentLevel++;
        }
        this.currentMethod = newFunc;
    }

   /**
     * El ambito de función actual volverá a ser el del padre
     */

    public void backMethod(){
        this.indentLevel= Math.max(0, indentLevel - 1);
        // El main nunca llegaria aqui, solo se llega tras reconocer un método
        this.currentMethod = this.currentMethod.padre;
    }

   /**
     * Termina el ámbito de una función
     * @param id
     * @param formal_paramlist
     * @param alltypes
     * @param blq
     * @return
     */

    public String getFunc(String id, String formal_paramlist, String alltypes, String blq) {
        String html = "<a name='" + id + "'>" + getIndentBlock(this.getReservedWord("function ") + id + " " + formal_paramlist + ":" + alltypes + ";") +blq + "\n";
        this.currentMethod.setCabecera(html);
        this.currentMethod.html = html;
        backMethod();
        return html; // SOLO PARA METODOS DENTRO DE METODOS
    }

   /**
     * Termina el ámbito de un procedimiento
     * @param id
     * @param formal_paramlist
     * @param blq
     * @return
     */

    public String getProc(String id, String formal_paramlist, String blq) {
        String html = "<a name='" + id + "'>" + getIndentBlock(this.getReservedWord("procedure ") + id + " " + formal_paramlist + ";")+ blq + "\n";
        this.currentMethod.html = html;
        this.currentMethod.setCabecera(html);
        backMethod();
        return html; // SOLO PARA METODOS DENTRO DE METODOS
    }

    /**
     * Actualiza la lista de declaración del programa principal (declaraciones que no estan incluidas en funciones)
     * @param s
     */

    public void updateLastDcl(String s){
        if(isMain()){
          this.mainProgramDcl += s;
        }
    }

    /*********************************************************************************************************
                                      METODOS DE FORMATEO A HTML (NO CONSTRUCTORES)
            Estos métodos devuelve bloque HTML a partir del estado del analisis, almacenado en el objeto
     *********************************************************************************************************/

    public String getIdent(String s){
        return "<a href='#" + s + this.currentMethod.name + "'>" + s + "</a>";
    }

    public String getIdentOfMethod(String s, String m){
        if("".equals(m)){
            return getIdent(s);
        }
        return "<a href='#" + s +"'>" + s + "</a>";
    }

    public String getConst (String s){
        return "<span class='cte'>" + s + "</span>";
    }

    public String getIndentBlock(String s){
        if(this.sentCond){
          this.sentCond = false;
          return "<div style='text-indent: " + (this.indentLevel + 1) + "cm'>" + s + "</div>\n";
        }
        return "<div style='text-indent: " + this.indentLevel + "cm'>" + s + "</div>\n";
    }
    
    /**
     *  Devuelve un "end;" si no es una declaración de sentencia, "end" en otro caso
     * @return
     */

    public String getIndentationEnd(){
        if(!sentCond){
          return getIndentBlock("end;");
        }
        return getIndentBlock("end");
    }

    public String getSentOpen (String s){
        this.openDiv = true;
        return "<div style='text-indent: " + this.indentLevel + "cm'>" + s;
    }

    public String getSentClose (String s){
        if(this.openDiv){
            this.openDiv = false;
            return s + "</div>\n";
        }
        return s;
    }

    public String getIdentDeclaration (String s) {
        return "<a name='" + s + this.currentMethod.name +"'>\n<span class='ident'>" + s + "</span>";
    }

    public String getReservedWord (String t){
        return "<span class='palres'>" + t + "</span>";
    }

    public String getReservedWordIdent (String t){
        return getIndentBlock(getReservedWord(t));
    }
    
    /**
     *  Marca un segmento de codigo el cual es erroneo y le añade un mensaje de error
     * @param t
     * @param msg
     * @return
     */

    public String getError(String t, String msg){
      return "<span class='error tooltipSP noIndet'>" + t + "<span class='tooltiptextSP noIndent'>" + msg + "</span></span>";
    }


    /*********************************************************************************************************
                                                METODOS AUXILIARES
     *********************************************************************************************************/

   /**
     * Elimina etiquetas HTML
     * @param s
     * @return
     */

    public static String deleteTags (String s){
        s = s.replaceAll("<[^>]*>", "");
        return s;
    }

    /*********************************************************************************************************
                                              METODOS DE COMPROBACIÓN
     *********************************************************************************************************/
    
    
    /**
     * Devuelve true si es el metodo principal
     * @return
     */
    public boolean isMain(){
        return this.currentMethod.padre == null;
    }
    
    /**
     *  Introduce una o varias variables en el método actual con su respectivo tipo
     * @param varlist
     * @param alltypes
     */

    public void pushVar(String varlist, String alltypes){
        String varlistClean = deleteTags(varlist);
        String alltypesClean = deleteTags(alltypes).trim();
        for(String var : varlistClean.split(",")){
            this.currentMethod.defVariables.put(var.trim(),alltypesClean);
        }
    }

    /**
     *  Introduce un tipo en la declaración de tipos de un método
     * @param type
     */

    public void pushType(String type){
        this.currentMethod.defTypes.add(type);
    }
    
    /**
     *  Reconoce y marca como error si una expresion no es de tipo booleano
     * @param tipo
     * @param exp
     * @return 
     */

    public String checkBool(String tipo, String exp){
      if(!"BOOLEAN".equals(tipo)){
          return this.getError(exp, "Se esperaba una expresion de tipo booleano");
      }
      return exp; 
    }
    
    /**
      *  Reconoce y marca como error si una expresion no es de tipo entero
      * @param tipo
      * @param exp
      * @return 
      */
    
    public String checkInt(String tipo, String exp){
      if(!"INTEGER".equals(tipo)){
          String var = deleteTags(exp);
          if(this.existVar(var)){
              if("INTEGER".equals(this.currentMethod.defVariables.get(var))){
                  return exp;  
              }else{
                  return this.getError(var, var+" variable / expresion no valida");
              }
          }
          return this.getError(exp, "Se esperaba una expresion de tipo entero");
      }
      return exp; 
    }
    
    /**
     *  Reconoce y marca como error si una asignacion asigna directamente un registro o una matriz
     * @param id
     * @param exp
     * @return 
     */

    public String checkAsig(String id, String exp){
        String s = id + " := " + exp;
        // Añade la variable utilizada a la lista de variables
        this.currentMethod.variables.add(deleteTags(id));
        // Comprueba si la parte izquierda de la asignación es de un tipo declarado en el ambito del programa o función
        String type = this.currentMethod.defVariables.get(deleteTags(id));
        if( type != null && this.currentMethod.defTypes.contains(type)){
            return this.getError(s, "Asignación incorrecta de registro o matriz, deben asignarse elemento a elemento");
        }
        return s;
    }
    
    /**
     *  Comprueba si una variable es de tipo entero
     * @param n
     * @return 
     */

    public String checkIntVar(String n){
        String s = "<a href='#" + n + this.currentMethod.name + "'>" + n + "</a>";
        if("INTEGER".equals(this.currentMethod.defVariables.get(n))){
            return s;
        }else if(!this.existVar(n)){
            return this.getError(s, n+": variable no definida");
        }else{
            return this.getError(s, n+" debe ser una variable entera"); // Orden incorrecto
        }
    }
    
    /**
     *  Devuelve si una variable existe o no
     * @param s
     * @return 
     */
    
    public boolean existVar(String s){
        return this.currentMethod.defVariables.get(s) != null;
    }
    
    /**
     *  Comprueba si una funcion devuelve un valor (asignado en la parte izquierda)
     * @param blq
     * @return 
     */
    
    public String checkReturnParam(String blq){
        // COMPROBAR QUE ALMENOS HAY UNA ASIGNACIÓN AL NOMBRE DE LA FUNCION
        if(this.currentMethod.function
                && !this.currentMethod.variables.contains(this.currentMethod.name)){
            this.indentLevel++;
            blq = blq + getIndentBlock(getError(this.currentMethod.name+" := ?","Falta valor de retorno para la función"));
            this.indentLevel--;
        }
        return blq;
    }
    
    /**
     *  Comprueba que dos numeros forman un rango creciente
     * @param simpvalue1
     * @param simpvalue2
     * @return 
     */

    public String checkRange(String simpvalue1, String simpvalue2){
      String s = simpvalue1+ " .. " +simpvalue2;
      String msg = "Declaracion inválida de array en el método "+this.currentMethod.name;
      try{
            int v1 = Integer.parseInt(deleteTags(simpvalue1));
            int v2 = Integer.parseInt(deleteTags(simpvalue2));
            if(v1 > v2){
              return this.getError(s,msg + " " + v2+" debe ser mayor que "+v1); // Orden incorrecto
            }
            return  s;
      }catch(NumberFormatException e){
            return this.getError(s, msg + " los indices deben ser números enteros");   // No numerico
      }
    }

    /*********************************************************************************************************
                                           METODOS DE CREACION DEL HTML
             Estos métodos se encargan de generar el html final a partir de los datos almacenados
     *********************************************************************************************************/

    public void closeHTML (String nameProgram){
        String s = CABECERA_HASTA_BODY + generateIndexPart(nameProgram) + generateMainProgramPart() + generateMethodsPart() + CIERRE_HTML;
        createHtml(s);
    }

    public String generateIndexPart(String nameProgram){
        String s = "<div class='ui segment secondary row'>" +
                   "<div class='col-md-9'><a name='inicio'>\n" +
                   "<h1>Programa: " + nameProgram + "</h1></div>\n" +
                   "<div class='col-md-3'><a class='git-button pull-right' href='https://github.com/Maes95/2Flex1Cup.git'><img id='img-git' src='http://brandao.io/icon-github.png'></img></a><a class='mini ui secondary button pull-right' onclick='changeActiveStyle(!activeStyle)'>Cambiar tema</a></div>\n" +
                   "</div>" +
                   "<div class='ui segment'>" +
                   "<h2>Funciones y procedimientos</h2>\n" +
                   "<ul>\n";
        s += "<li><a href='#ProgPpal'>Programa principal</a></li>\n";
        for (Method m : this.methods.values()) {
            s += "<li><a href='#" + m.name + "'>" + m.cabecera + "</a></li>\n";
        }
        s+=  "</ul>\n" +
             "</div>\n\n";
        return s;
    }

    public String generateMethodsPart(){
        String s = "";
        for (Method m : this.methods.values()) {
            if("Principal".equals(m.name)) continue;
            s += "<div class='ui segment'>";
            s +=  m.html + "<br/>\n" +
                 "<div style='text-align: center;'>\n" +
                 "<a class='mini ui secondary button' style='display: inline-block;' href='#" + m.name + "'>Inicio de rutina</a>\n" +
                 "<a class='mini ui secondary button' style='display: inline-block;' href='#inicio'>Inicio de programa</a>" +
                 "</div>\n" +
                 "</div>\n\n";
        }
        return s;
    }

    public String generateMainProgramPart(){
        String s = "<div class='ui segment'>" +
                   "<a name='ProgPpal'>\n" +
                   "<h2>Programa Principal</h2>\n";
        s += this.mainProgramDcl;
        s += "<span class='palres'>begin</span>\n";
        s += this.mainProgram;
        s += "<span class='palres'>end</span>.<br/>\n";
        s += "<div style='text-align: center;'>\n" +
             "<a class='mini ui secondary button' style='display: inline-block;' href='#ProgPpal'>Inicio del programa principal</a>\n" +
             "<a class='mini ui secondary button' style='display: inline-block;' href='#inicio'>Inicio de programa</a>\n" +
             "</div>\n" +
             "</div>\n\n";
        return s;
    }


    /**
     * Este método creara un archivo html listo para ser visualizado
     * @param html
     */

    public void createHtml(String html){
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName+".html"), "UTF-8"));
            try {
                out.write(html);
            } catch (IOException ex) {
                System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
            }

        } catch (UnsupportedEncodingException | FileNotFoundException ex2) {
            System.out.println("Mensaje error 2: " + ex2.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex3) {
                System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
            }
        }
    }
}
