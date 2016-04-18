import com.sun.org.apache.xpath.internal.SourceTree;

public class HTMLGenerator {

    static String cabeceraHastaBody =   "<html>\n" +
                                        "<head>\n" +
                                        "<title>EjemploHTML.pl</title>\n" +
                                        "<style>\n" +
                                        ".cte {color:rgb(19,189,72);}\n" +
                                        ".ident {color:rgb(55,40,244);}\n" +
                                        ".palres {color:rgb(0,0,0);font-weight:bold;}\n" +
                                        "</style>\n" +
                                        "<style type=\"text/css\"></style></head>;}\n" +
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
        pushHTML(prgSentence);
        System.out.println(cabeceraHastaBody + innerHTML + cierreHTML);
        return cabeceraHastaBody + innerHTML + cierreHTML;
    }
}
