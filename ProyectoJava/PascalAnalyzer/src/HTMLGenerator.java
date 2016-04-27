import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

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
    private ArrayList<String> methods;

    // Variables de estado

    public boolean sentCond;        // Indica si se esta dentro de una sentencia de control de flujo
    public boolean inProcFun;       // Variable que indica si estamos dentro de un procedimiento o funcion
    public int indentLevel;         // Nivel de identación actual
    private boolean openDiv;

    // Constructor

    public HTMLGenerator (String fileName){
        this.fileName = fileName;
        this.mainProgramDcl = "";
        this.methods = new ArrayList<>();
        this.sentCond = false;
        this.inProcFun = false;
        this.indentLevel = 0;
        this.openDiv = false;
        this.fileName = this.fileName.replaceAll("(src/|.txt)", "");

        this.CABECERA_HASTA_BODY =  "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<head>\n" +
                                    "<title>"+this.fileName+"</title>\n" +
                                    this.getStyleAndLibrariesDark() +
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

    public String getFunc(String id, String formal_paramlist, String alltypes, String blq) {
        String f = "<a name='" + id + "'>" + this.getReservedWord("function ") + id + " " + formal_paramlist + ":" + alltypes + ";" + "</br>" + blq + ";\n";
        this.methods.add(f);
        return f;
    }

    public String getProc(String id, String formal_paramlist, String blq) {
        String p = "<a name='" + id + "'>" + this.getReservedWord("procedure ") + id + " " + formal_paramlist + ";"  + "</br>" + blq + ";\n";
        this.methods.add(p);
        return p;
    }

    /**
     * Actualiza la lista de declaración del programa principal (declaraciones que no estan incluidas en funciones)
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
        return "<a href='#" + s +"'>" + s + "</a>";
    }

    public String getConst (String s){
        return "<span class='cte'>" + s + "</span>";
    }

    public String getSent (String s){
        return "<div style='text-indent: " + this.indentLevel + "cm'>" + s + "</div>\n";
    }

    public String getSent (String s, boolean sentCond){
        if(sentCond){
          this.sentCond = false;
          return "<div style='text-indent: " + (this.indentLevel + 1) + "cm'>" + s + "</div>\n";
        }
        return getSent(s);
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
        return "<a name='" + s + "'>\n<span class='ident'>" + s + "</span>";
    }

    public String getReservedWord (String t){
        return "<span class='palres'>" + t + "</span>";
    }

    public String getReservedWordIdent (String t){
        return getSent(getReservedWord(t));
    }

    /*********************************************************************************************************
                                                METODOS AUXILIARES
     *********************************************************************************************************/

   /**
    * Elimina etiquetas HTML
    */

    public String deleteTags (String s){
        System.out.println(s);
        s = s.replaceAll("<[^>]*>", "");
        return s;
    }

    /**
     *  Obtiene el nombre de un método
     */
    public String getMethodName (String s){
        return s.split("\\s+")[1];
    }
    /**
     *  Obtiene la cabecera de un método
     */

    public String getMethodHeader (String s){
        return s.split(";")[0];
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
        String s = "<div class='ui center aligned segment secondary'>" +
                   "<a name='inicio'>\n" +
                   "<h1>Programa: " + nameProgram + "</h1>\n" +
                   "</div>" +
                   "<div class='ui segment'>" +
                   "<h2>Funciones y procedimientos</h2>\n" +
                   "<ul>\n";
        s += "<li><a href='#ProgPpal'>Programa principal</a></li>\n";
        for (String m : this.methods) {
            String simpleM = deleteTags(m);
            s += "<li><a href='#" + getMethodName(simpleM) + "'>" + getMethodHeader(simpleM) + "</a></li>\n";
        }
        s+=  "</ul>\n" +
             "</div>\n\n";
        return s;
    }

    public String generateMethodsPart(){
        String s = "";
        for (String m : this.methods){
            s += "<div class='ui segment'>";
            String tagsDeleted = deleteTags(m);
            s +=  m + "<br/>\n" +
                 "<div style='text-align: center;'>\n" +
                 "<a class='mini ui secondary button' style='display: inline-block;' href='#" + getMethodName(tagsDeleted) + "'>Inicio de rutina</a>\n" +
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

    private String getStyleAndLibraries(){
        String style =  "<!-- Bootstrap -->" +
                        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">\n" +
                        "<!-- Semantic UI -->\n" +
                        "<link rel=\"stylesheet\" href=\"https://rawgit.com/Semantic-Org/Semantic-UI/next/dist/semantic.css\">\n" +
                        "<style>\n" +
                        "body {height: 100%;}\n" +
                        ".cte {color:rgb(19,189,72);}\n" +
                        ".ident {color:rgb(55,40,244);}\n" +
                        ".palres {color:rgb(0,0,0);font-weight:bold;}\n" +
                        "a[name] {text-decoration: none !important;}" +
                        "</style>\n";
        return style;
    }

    private String getStyleAndLibrariesDark(){
        String style =  "<!-- Bootstrap -->" +
                        "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">\n" +
                        "<!-- Semantic UI -->\n" +
                        "<link rel=\"stylesheet\" href=\"https://rawgit.com/Semantic-Org/Semantic-UI/next/dist/semantic.css\">\n" +
                        "<style>\n" +
                        "body {height: 100%; color: white;}\n" +
                        ".ui.segments .segment, .ui.segment {font-size: 15px;}"+
                        "a {color:hsl(220, 14%, 71%);}\n" +
                        "a:hover {color:hsl(220, 14%, 71%);}\n" +
                        ".ui.segments > .segment {border-top: 1px solid hsla(220, 14%, 71%, 0.18);}"+
                        ".cte {color:hsl( 29, 54%, 61%);}\n" +
                        ".ident {color: hsl(207, 82%, 66%);}\n" +
                        ".palres {color:hsl(286, 60%, 67%);}\n" +
                        "body {background-color: hsl(222, 11%, 12%);}"+
                        ".ui.center.aligned.segment.secondary {background-color: hsl(222, 11%, 15%);}"+
                        ".ui.segment {background-color: hsl(222, 11%, 18%);}"+
                        ".ui.segments .segment, .ui.segment {padding-left: 4em;}"+
                        "a[name] {text-decoration: none !important;}" +
                        "</style>\n";
        return style;
    }
}
