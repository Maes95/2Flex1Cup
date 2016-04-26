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

    static String cabeceraHastaBody =   "<html>\n" +
                                        "<head>\n" +
                                        "<title>EjemploHTML.pl</title>\n" +
                                        "<style>\n" +
                                        ".cte {color:rgb(19,189,72);}\n" +
                                        ".ident {color:rgb(55,40,244);}\n" +
                                        ".palres {color:rgb(0,0,0);font-weight:bold;}\n" +
                                        "</style>\n" +
                                        "<style type=\"text/css\"></style></head>\n" +
                                        "<body>\n";

    static String cierreHTML =  "\n</body>\n" +
                                "</html>";

    String innerHTML;

    public String nameProgram;

    ArrayList<String> methods;

    public HTMLGenerator (){
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
        String f = "<a name='" + id + "'> <span class='palres'>function </span> " + id + " " + formal_paramlist + ":" + alltypes + "</br>" + blq;
        this.methods.add(f);
        return f;
    }

    public String getProc(String id, String formal_paramlist, String blq) {
        String p = "<a name='" + id + "'> <span class='palres'>procedure </span> " + id + " " + formal_paramlist + ";"  + "</br>" + blq;
        this.methods.add(p);
        return p;
    }



    //METHODS TO GENERATE MAIN STRUCTURES IN HTML
    public String generateIndexPart(){
        String s = "<a name='inicio'>\n" +
                    "<h1>Programa: " + this.nameProgram + "</h1>\n" +
        <H2>Funciones y procedimientos</H2>
        <UL>
        <LI><A HREF="#areaCuadrado">function areaCuadrado ( lado: REAL ) : REAL </A></LI>
        <LI><A HREF="#intercambioEntero">procedure intercambio ( v1, v2: INTEGER ) </A></LI>
        <LI><A HREF="#ProgPpal">Programa princial</A></LI>
        </UL>
        <HR/>

        return s;
    }

    public String generateMethodsPart(){
        return null;
    }

    public String generateMainProgramPart(){
        return null;
    }


    //ELIMINATE TAGS IN AN HTML ELEMENT (just plain text)
    public String deleteTags (String s){
        s = s.replaceAll("<[^>]*>", "");
        return s;
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
