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
    public boolean inProcFun;       // Variable que indica si estamos dentro de un procedimiento o funcion
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
        this.inProcFun = false;
        this.openDiv = false;
        this.fileName = this.fileName.replaceAll("(src/|.txt)", "");

        this.CABECERA_HASTA_BODY =  "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<title>"+this.fileName+"</title>\n" +
                                    this.getLibraries() +
                                    this.getInitialStyle() +
                                    this.getScripts() +
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
     */

    public void addMethod(String name){
        Method newFunc = new Method(name, this.currentMethod);
        this.methods.put(newFunc.name, newFunc);
        this.currentMethod = newFunc;
    }
    
   /**
     * El ambito de función actual volverá a ser el del padre
     */

    public void backMethod(){
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
        String html = "<a name='" + id + "'>" + this.getReservedWord("function ") + id + " " + formal_paramlist + ":" + alltypes + ";" + "</br>" + blq + ";\n";    
        this.currentMethod.setCabecera(html);  
        
        // COMPROBAR QUE ALMENOS HAY UNA ASIGNACIÓN AL NOMBRE DE LA FUNCION
        if(!this.currentMethod.variables.contains(this.currentMethod.name)){
            html = getErrorFunc(html);
            this.currentMethod.errores.add("La funcion "+this.currentMethod.name+" no tiene valor de retorno");
        }
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
        String html = "<a name='" + id + "'>" + this.getReservedWord("procedure ") + id + " " + formal_paramlist + ";"  + "</br>" + blq + ";\n";
        this.currentMethod.html = html;
        this.currentMethod.setCabecera(html);
        backMethod();
        return html; // SOLO PARA METODOS DENTRO DE METODOS
    }

    /**
     * Actualiza la lista de declaración del programa principal (declaraciones que no estan incluidas en funciones)
     * @param s
     */

    public void updateLastDcl (String s){
        if(!this.inProcFun)
          this.mainProgramDcl += s;
    }

    /*********************************************************************************************************
                                      METODOS DE FORMATEO A HTML (NO CONSTRUCTORES)
            Estos métodos devuelve bloque HTML a partir del estado del analisis, almacenado en el objeto
     *********************************************************************************************************/

    public String getIdent (String s){
        return "<a href='#" + s + this.currentMethod.name + "'>" + s + "</a>";
    }

    public String getConst (String s){
        return "<span class='cte'>" + s + "</span>";
    }

    public String getSent (String s){
        if(this.sentCond){
          this.sentCond = false;
          return "<div style='text-indent: " + (this.indentLevel + 1) + "cm'>" + s + "</div>\n";
        }
        return "<div style='text-indent: " + this.indentLevel + "cm'>" + s + "</div>\n";
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
        return getSent(getReservedWord(t));
    }

    public String getError(String t){
      return "<span class='error'>" + t + "</span>";
    }
    
    public String getErrorFunc(String html){
      // Sumamos 1 a la identacion porque se aplicará la misma que se aplica sobre el end siguiente (Salimos del bloque)
      String error = "<div style='text-indent: " + (this.indentLevel+1)+ "cm'><span class='error'>"+this.currentMethod.name+" := ?</span></div>";
      String errorCode = html.replace("<span class='palres'>end</span>;", error+"<span class='palres'>end</span>;");
      return errorCode;
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

    /**
     *  Obtiene el nombre de un método
     * @param s
     * @return 
     */
    public String getMethodName (String s){
        return s.split("\\s+")[1];
    }
    /**
     *  Obtiene la cabecera de un método
     */

    /**
     * Obtiene la cabecera de un método
     * @param s
     * @return 
     */
    public String getMethodHeader (String s){
        return s.split(";")[0];
    }

    /*********************************************************************************************************
                                              METODOS DE COMPROBACIÓN
     *********************************************************************************************************/
    
    /**
     *  Introduce una o varias variables en el método actual con su respectivo tipo
     * @param varlist
     * @param alltypes
     */
    
    public void pushVar(String varlist, String alltypes){
        String varlistClean = deleteTags(varlist);
        String alltypesClean = deleteTags(alltypes);
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
    
    public String checkBool(String exp){
      return exp; // POR HACER
    }

    public String checkAsig(String id, String exp){
        String s = id + " := " + exp;
        // Añade la variable utilizada a la lista de variables
        this.currentMethod.variables.add(deleteTags(id));
        
        String type = this.currentMethod.defVariables.get(deleteTags(id));
        if( type != null && this.currentMethod.defTypes.contains(type)){
            this.currentMethod.errores.add("Asignación incorrecta de registro o matriz, deben asignarse elemento a elemento");
            return this.getError(s);
        }
        return s;
    }

    public String checkInt(String n){
        return n; // POR HACER
    }

    public String checkRange(String simpvalue1, String simpvalue2){
      String s = simpvalue1+ " .. " +simpvalue2;
      String msg = "Declaracion inválida de array en el método "+this.currentMethod.name;
      try{
            int v1 = Integer.parseInt(deleteTags(simpvalue1));
            int v2 = Integer.parseInt(deleteTags(simpvalue2));
            if(v1 > v2){
              this.currentMethod.errores.add(" "+v2+" debe ser mayor que "+v1);
              return this.getError(s); // Orden incorrecto
            }
            return  s;
      }catch(NumberFormatException e){
            this.currentMethod.errores.add(" los indices deben ser números enteros");
            return this.getError(s);   // No numerico
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
                   "<div class='col-md-3'><a class='mini ui secondary button pull-right' onclick='changeActiveStyle(!activeStyle)'>Cambiar tema</a></div>\n" +
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

    /*********************************************************************************************************
                                           ESTILOS DEL HTML (CSS)
     *********************************************************************************************************/

    private String getInitialStyle(){ //Por defecto a 'style-light'
        return "\n<style id='style-light'>" + this.getStyleLight() + "</style>\n";
    }

    private String getStyleLight(){
        String style =  "body {height: 100%;}" +
                        ".ui.segments .segment, .ui.segment {font-size: 15px;}"+
                        ".cte {color:rgb(19,189,72);}" +
                        ".ident {color:rgb(55,40,244);}" +
                        ".palres {color:rgb(0,0,0);font-weight:bold;}" +
                        errorStyle+
                        ".ui.segments .segment, .ui.segment {padding-left: 2em;}"+
                        "a[name] {text-decoration: none !important;}";
        return style;
    }

    private String getStyleDark(){
        String style =  "body {height: 100%; color: white;}" +
                        ".ui.segments .segment, .ui.segment {font-size: 15px;}"+
                        "a {color:hsl(220, 14%, 71%);}" +
                        "a:hover {color:hsl(220, 14%, 71%);}" +
                        ".ui.segments > .segment {border-top: 1px solid hsla(220, 14%, 71%, 0.18);}"+
                        ".cte {color:hsl( 29, 54%, 61%);}" +
                        ".ident {color: hsl(207, 82%, 66%);}" +
                        ".palres {color:hsl(286, 60%, 67%);}" +
                        errorStyle+
                        "body {background-color: hsl(222, 11%, 12%);}"+
                        ".ui.center.aligned.segment.secondary {background-color: hsl(222, 11%, 15%);}"+
                        ".ui.segment {background-color: hsl(222, 11%, 18%) !important;}"+
                        ".ui.segments .segment, .ui.segment {padding-left: 2em;}"+
                        ".ui.raised.segments {border: 1px solid rgb(54, 57, 65);}" +
                        "a[name] {text-decoration: none !important;}";
        return style;
    }

    /*********************************************************************************************************
                                            LIBRERIAS
     *********************************************************************************************************/

    private String getLibraries(){
        String scripts =    "\n<!-- Bootstrap -->\n" +
                            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' integrity='sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7' crossorigin='anonymous'>\n" +
                            "<!-- Semantic UI -->\n" +
                            "<link rel='stylesheet' href='https://rawgit.com/Semantic-Org/Semantic-UI/next/dist/semantic.css'>\n";
        return scripts;
    }

    /*********************************************************************************************************
                                            SCRIPTS
     *********************************************************************************************************/

    private String getScripts(){
        String scripts = "\n<script>" +
                         "var activeStyle=true;" +
                         "function changeActiveStyle(b){" +
                         "activeStyle = !activeStyle;" +
                         "var sL = document.getElementById('style-light');" +
                         "var sD = document.getElementById('style-dark');" +
                         "if (b) {sD.parentNode.removeChild(sD); var s = document.createElement('style'); s.id='style-light'; s.type = 'text/css'; s.appendChild(document.createTextNode('" + this.getStyleLight() + "')); document.head.appendChild(s);}" +
                         "else {sL.parentNode.removeChild(sL); var s = document.createElement('style'); s.id='style-dark'; s.type = 'text/css'; s.appendChild(document.createTextNode('" + this.getStyleDark() + "')); document.head.appendChild(s);}}" +
                         "</script>\n";
        return scripts;
    }
    private final String errorStyle = ".error{color: #db2828 !important;"
                                    +"background-color: #ffe8e6;"
                                    +"padding: 0.2em;"
                                    +"border-radius: .28571429rem;"
                                    +"box-shadow: 0 0 0 1px #e0b4b4 inset,0 0 0 0 transparent;"
                                +"}"
                                +"div.error * {"
                                +"color: #db2828;"
                                +"}"
                                +"span.error * {"
                                +"color: #db2828;"
                                +"}";

}
