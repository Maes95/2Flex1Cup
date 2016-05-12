/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michel
 */
public final class APIFrontEnd {
    
    /*********************************************************************************************************
                                           ESTILOS DEL HTML (CSS)
     *********************************************************************************************************/

    public final static String getInitialStyle(){ //Por defecto a 'style-light'
        return "\n<style id='style-light'>" + APIFrontEnd.getStyleLight() + "</style>\n";
    }

    private final static String getStyleLight(){
        String style =  "body {height: 100%;}" +
                        ".ui.segments .segment, .ui.segment {font-size: 15px;}"+
                        ".cte {color:rgb(19,189,72);}" +
                        ".ident {color:rgb(55,40,244);}" +
                        ".palres {color:rgb(0,0,0);font-weight:bold;}" +
                        errorStyle +
                        selectionStyle +
                        tooltipStyle +
                        ".ui.segments .segment, .ui.segment {padding-left: 2em;}"+
                        "a[name] {text-decoration: none !important;}" +
                        ".selected {background-color: gray;}" +
                        ".git-button {margin-left: 10px; margin-top: 3px;}";
        return style;
    }

    private final static String getStyleDark(){
        String style =  "body {height: 100%; color: white;}" +
                        ".ui.segments .segment, .ui.segment {font-size: 15px;}"+
                        "a {color:hsl(220, 14%, 71%);}" +
                        "a:hover {color:hsl(220, 14%, 71%);}" +
                        ".ui.segments > .segment {border-top: 1px solid hsla(220, 14%, 71%, 0.18);}"+
                        ".cte {color:hsl( 29, 54%, 61%);}" +
                        ".ident {color: hsl(207, 82%, 66%);}" +
                        ".palres {color:hsl(286, 60%, 67%);}" +
                        errorStyle +
                        selectionStyle +
                        tooltipStyle +
                        "body {background-color: hsl(222, 11%, 12%);}"+
                        ".ui.center.aligned.segment.secondary {background-color: hsl(222, 11%, 15%);}"+
                        ".ui.segment {background-color: hsl(222, 11%, 18%) !important;}"+
                        ".ui.segments .segment, .ui.segment {padding-left: 2em;}"+
                        ".ui.raised.segments {border: 1px solid rgb(54, 57, 65);}" +
                        "a[name] {text-decoration: none !important;}" +
                        ".git-button {margin-left: 10px; margin-top: 3px;}" +
                        "#img-git {-webkit-filter: brightness(7); filter: brightness(7);}";
        return style;
    }

    private final static String errorStyle = ".error {color: #db2828 !important;"
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

    private final static String selectionStyle = ".selected {color: #FFFFFF !important;"
            +"background-color: #828282;"
            +"padding: 0.2em;"
            +"border-radius: .28571429rem;"
            +"box-shadow: 0 0 0 1px #8C8C8C inset,0 0 0 0 transparent;"
            +"}"
            +"div.error * {"
            +"color: #db2828;"
            +"}"
            +"span.error * {"
            +"color: #db2828;"
            +"}";

    private final static String tooltipStyle = " .tooltipSP {" +
            "    position: relative;" +
            "    cursor: pointer;"+
            "}" +
            ".noIndent{text-indent: 0cm !important;}"+
            ".tooltipSP .tooltiptextSP {" +
            "    visibility: hidden;" +
            "    width: 120px;" +
            "    background-color: black;" +
            "    color: #fff;" +
            "    text-align: center;" +
            "    border-radius: 6px;" +
            "    padding: 5px 0;" +
            "    position: absolute;" +
            "    z-index: 1;" +
            "    bottom: 100%;" +
            "    left: 50%;" +
            "    margin-left: -60px;" +
            "    opacity:0;" +
            "    transition:opacity 0.3s ease-in;" +
            "    -webkit-transition: opacity 0.3s ease-in;" +
            "    -moz-transition: opacity 0.3s ease-in;;"+
            "}" +
            ".tooltipSP:hover .tooltiptextSP {" +
            "    visibility: visible;"+
            "    opacity: 0.9;"+
            "}";

    /*********************************************************************************************************
                                            LIBRERIAS
     *********************************************************************************************************/

    public final static String getLibraries(){
        String scripts =    "\n<!-- Bootstrap -->\n" +
                            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css' integrity='sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7' crossorigin='anonymous'>\n" +
                            "<!-- Semantic UI -->\n" +
                            "<link rel='stylesheet' href='https://rawgit.com/Semantic-Org/Semantic-UI/next/dist/semantic.css'>\n"+
                            "\n<!-- jQuery -->\n" +
                            "<script src='https://code.jquery.com/jquery-2.2.3.min.js' integrity='sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo=' crossorigin='anonymous'></script>";
        return scripts;
    }

    /*********************************************************************************************************
                                            SCRIPTS
     *********************************************************************************************************/

    public final static String getScripts(){
        String scripts = "\n<script>" +
                         "var activeStyle=true;" +
                         "function changeActiveStyle(b){" +
                         "activeStyle = !activeStyle;" +
                         "var sL = document.getElementById('style-light');" +
                         "var sD = document.getElementById('style-dark');" +
                         "if (b) {sD.parentNode.removeChild(sD); var s = document.createElement('style'); s.id='style-light'; s.type = 'text/css'; s.appendChild(document.createTextNode('" + APIFrontEnd.getStyleLight() + "')); document.head.appendChild(s);}" +
                         "else {sL.parentNode.removeChild(sL); var s = document.createElement('style'); s.id='style-dark'; s.type = 'text/css'; s.appendChild(document.createTextNode('" + APIFrontEnd.getStyleDark() + "')); document.head.appendChild(s);}}" +
                         "</script>\n";
        return scripts;
    }
}
