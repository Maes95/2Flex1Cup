/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Stack;
import java.util.regex.Pattern;


/**
 *
 * @author Michel
 */
public class Regla{

    String cadena;
    String antecedente;
    Stack<String> consecuentes;

    public Regla (String cadena){
      this.cadena = cadena;
      String[] array = cadena.split(" <");
      this.antecedente = array[0];
      this.consecuentes = new Stack<>();
      for (String word : array[1].split(">")[0].split(" ") ){
        consecuentes.push(word);
      }
    }

    public static boolean esTerminal(String cadena){
        return !Pattern.compile("[A-Z_]*").matcher(cadena).matches();
    }

    public static void printSyntacticTree(Stack<Regla> reglas, String s){
        if (reglas.empty()){return;}
        Regla r = reglas.pop();
        while(!r.consecuentes.empty()){
            String word = r.consecuentes.pop();
            if (esTerminal(word)){
                System.out.println(s + "   |_" + word);
            }else{
                System.out.println(s + "   |_" + word);
                printSyntacticTree(reglas, s + "   |");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Regla regla = new Regla("SENTLIST <SENTLIST SENT>");
        Regla regla2 = new Regla("BLQ <DCLLIST begin SENTLIST end>");
        Regla regla3 = new Regla("PRG <program EjemploAprobado ; BLQ .>");
        Stack<Regla> stack = new Stack<>();
        stack.push(regla);
        stack.push(regla2);
        stack.push(regla3);
        System.out.println("_" + regla3.antecedente);
        printSyntacticTree(stack, "");

    }
}
