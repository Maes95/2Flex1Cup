import com.sun.org.apache.xpath.internal.SourceTree;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

public class HTMLGenerator {

    public int identLevel = 0;

    static String cabeceraHastaBody =   "<!DOCTYPE html>\n" +
                                        "<html>\n" +
                                        "<head>\n" +
                                        "<title>EjemploHTML.pl</title>\n" +
                                        "<style>\n" +
                                        ".cte {color:rgb(19,189,72);}\n" +
                                        ".ident {color:rgb(55,40,244);}\n" +
                                        ".palres {color:rgb(0,0,0);font-weight:bold;}\n" +
                                        "</style>\n" +
                                        "</head>\n\n" +
                                        "<body>\n";

    static String cierreHTML =  "\n</body>\n" +
                                "</html>";

    String innerHTML;

    public String nameProgram;
    public String mainProgram;
    public String mainProgramDcl;

    ArrayList<String> methods;

    public HTMLGenerator (){
        this.methods = new ArrayList<>();
        this.innerHTML = "";
    }

    public void pushHTML (String... arrayHtml){
        innerHTML =  String.join(" ", arrayHtml) + innerHTML;
    }

    public String closeHTML (String... prgSentence){
        pushHTML(prgSentence);
        String s = cabeceraHastaBody + generateIndexPart() + generateMethodsPart() + generateMainProgramPart() + cierreHTML;
        System.out.println(s);
        createHtml(s);
        return s;
    }

    //AUXILIAR METHODS TO GET HTML ELEMENTS
    public String getIdent (String s){
        return "<span class='ident'>" + s + "</span>";
    }

    public String getSent (String s){
        return "<div style='text-indent: " + this.identLevel + "cm'>" + s + "</div>";
    }

    public String getFunc(String id, String formal_paramlist, String alltypes, String blq) {
        String f = "<a name='" + id + "'><span class='palres'>function </span>" + id + " " + formal_paramlist + ":" + alltypes + ";" + "</br>" + blq;
        this.methods.add(f);
        return f;
    }

    public String getProc(String id, String formal_paramlist, String blq) {
        String p = "<a name='" + id + "'><span class='palres'>procedure </span>" + id + " " + formal_paramlist + ";"  + "</br>" + blq;
        this.methods.add(p);
        return p;
    }

    public void getMainProgram(String s){
        this.mainProgram = s;
    }


    //METHODS TO GENERATE MAIN STRUCTURES IN HTML
    public String generateIndexPart(){
        String s = "<a name='inicio'>\n" +
                   "<h1>Programa: " + this.nameProgram + "</h1>\n" +
                   "<h2>Funciones y procedimientos</h2>\n" +
                   "<ul>\n";
        for (String m : this.methods){
            String simpleM = deleteTags(m);
            s += "<li><a href='#" + getMethodName(simpleM) + "'>" + getMethodHeader(simpleM) + "</a></li>\n";
        }
        s += "<li><a href='#ProgPpal'>Programa princial</a></li>\n" +
             "</ul>\n" +
             "<hr/>\n\n";
        return s;
    }

    public String generateMethodsPart(){
        String s = "";
        for (String m : this.methods){
            String tagsDeleted = deleteTags(m);
            s +=  m + "<br/>\n" +
                 "<a href='#" + getMethodName(tagsDeleted) + "'>Inicio de rutina</a><br/>\n" +
                 "<a href='#inicio'>Inicio de programa</a><br/>\n"+
                 "<hr/>\n\n";
        }
        return s;
    }

    public String generateMainProgramPart(){
        String s = "<a name='ProgPpal'>\n" +
                   "<h2>Programa Principal</h2>\n";
        s += this.mainProgramDcl;
        s += "<span class='palres'>begin</span>\n";
        s += this.mainProgram;
        s += "<span class='palres'>end</span>.<br/>\n";
        s += "<a href='#ProgPpal'>Inicio del programa principal</a><br/>\n" +
             "<a href='#inicio'>Inicio de programa</a>\n" +
             "<br/>\n\n";
        return s;
    }


    //ELIMINATE TAGS IN AN HTML ELEMENT (just plain text)
    public String deleteTags (String s){
        System.out.println(s);
        s = s.replaceAll("<[^>]*>", "");
        System.out.println(s);
        return s;
    }

    public String getMethodName (String s){
        return s.split("\\s+")[1];
    }

    public String getMethodHeader (String s){
        return s.split(";")[0];
    }

    public void updateLastDcl (String s){
        this.mainProgramDcl = s;
    }

    /**
     * Este método creara un archivo html listo para ser visualizado
     */
    public void createHtml(String html){
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("programa.html"), "UTF-8"));
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
