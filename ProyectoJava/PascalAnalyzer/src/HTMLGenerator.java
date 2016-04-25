import com.sun.org.apache.xpath.internal.SourceTree;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

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

    public HTMLGenerator (){
        this.innerHTML = "";
    }

    public void pushHTML (String... arrayHtml){
        innerHTML =  String.join(" ", arrayHtml) + innerHTML;
    }

    public String closeHTML (String... prgSentence){
        //push Lista de FUNCIONES
        pushHTML(prgSentence);
        System.out.println(cabeceraHastaBody + innerHTML + cierreHTML);
        createHtml(cabeceraHastaBody + innerHTML + cierreHTML);
        return cabeceraHastaBody + innerHTML + cierreHTML;
    }

    public String getIdent (String s){
        return "<span class='ident'>" + s + "</span>";
    }

    public String getSent (String s){
        return "<div style='text-indent: " + this.identLevel + "cm'>" + s + "</div>";
    }

    public String getFunc(String id, String formal_paramlist, String alltypes, String blq) {
        return "<a name='" + id + "'> <span class='palres'>function </span> " + id + " " + formal_paramlist + ":" + alltypes + "</br>" + blq;
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
