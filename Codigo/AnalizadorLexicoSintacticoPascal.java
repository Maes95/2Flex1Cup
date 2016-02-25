/* The following code was generated by JFlex 1.6.1 */

// {Código de usuario}

// IMPORTS
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.io.PrintWriter;
import java.io.FileWriter;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>Gramatica.flex</tt>
 */
class AnalizadorLexicoSintacticoPascal {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT_KEY = 2;
  public static final int COMMENT_BRACKET = 4;
  public static final int LITERAL_CONST = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\12\0\1\0\1\0\1\0\1\0\22\0\1\61\3\0\1\6\2\0"+
    "\1\57\1\54\1\55\1\7\1\4\1\51\1\4\1\53\1\0\12\2"+
    "\1\52\1\51\1\22\1\23\1\21\2\0\1\45\1\3\1\47\1\3"+
    "\1\42\1\3\1\43\1\50\1\37\2\1\1\46\1\1\1\40\3\1"+
    "\1\44\1\1\1\41\6\1\4\0\1\1\1\0\1\16\1\26\1\27"+
    "\1\10\1\5\1\31\1\25\1\32\1\11\2\1\1\34\1\13\1\17"+
    "\1\14\1\24\1\1\1\15\1\30\1\20\1\35\1\12\1\33\1\1"+
    "\1\36\1\1\1\56\1\0\1\62\7\0\1\0\44\0\1\60\12\0"+
    "\1\60\4\0\1\60\5\0\27\60\1\0\37\60\1\0\u01ca\60\4\0"+
    "\14\60\16\0\5\60\7\0\1\60\1\0\1\60\21\0\165\60\1\0"+
    "\2\60\2\0\4\60\1\0\1\60\6\0\1\60\1\0\3\60\1\0"+
    "\1\60\1\0\24\60\1\0\123\60\1\0\213\60\1\0\255\60\1\0"+
    "\46\60\2\0\1\60\7\0\47\60\11\0\55\60\1\0\1\60\1\0"+
    "\2\60\1\0\2\60\1\0\1\60\10\0\33\60\5\0\3\60\35\0"+
    "\13\60\5\0\112\60\4\0\146\60\1\0\10\60\2\0\12\60\1\0"+
    "\23\60\2\0\1\60\20\0\73\60\2\0\145\60\16\0\66\60\4\0"+
    "\1\60\5\0\56\60\22\0\34\60\104\0\23\60\61\0\200\60\2\0"+
    "\12\60\1\0\23\60\1\0\10\60\2\0\2\60\2\0\26\60\1\0"+
    "\7\60\1\0\1\60\3\0\4\60\2\0\11\60\2\0\2\60\2\0"+
    "\4\60\10\0\1\60\4\0\2\60\1\0\5\60\2\0\14\60\17\0"+
    "\3\60\1\0\6\60\4\0\2\60\2\0\26\60\1\0\7\60\1\0"+
    "\2\60\1\0\2\60\1\0\2\60\2\0\1\60\1\0\5\60\4\0"+
    "\2\60\2\0\3\60\3\0\1\60\7\0\4\60\1\0\1\60\7\0"+
    "\20\60\13\0\3\60\1\0\11\60\1\0\3\60\1\0\26\60\1\0"+
    "\7\60\1\0\2\60\1\0\5\60\2\0\12\60\1\0\3\60\1\0"+
    "\3\60\2\0\1\60\17\0\4\60\2\0\12\60\21\0\3\60\1\0"+
    "\10\60\2\0\2\60\2\0\26\60\1\0\7\60\1\0\2\60\1\0"+
    "\5\60\2\0\11\60\2\0\2\60\2\0\3\60\10\0\2\60\4\0"+
    "\2\60\1\0\5\60\2\0\12\60\1\0\1\60\20\0\2\60\1\0"+
    "\6\60\3\0\3\60\1\0\4\60\3\0\2\60\1\0\1\60\1\0"+
    "\2\60\3\0\2\60\3\0\3\60\3\0\14\60\4\0\5\60\3\0"+
    "\3\60\1\0\4\60\2\0\1\60\6\0\1\60\16\0\12\60\20\0"+
    "\4\60\1\0\10\60\1\0\3\60\1\0\27\60\1\0\20\60\3\0"+
    "\10\60\1\0\3\60\1\0\4\60\7\0\2\60\1\0\2\60\6\0"+
    "\4\60\2\0\12\60\21\0\3\60\1\0\10\60\1\0\3\60\1\0"+
    "\27\60\1\0\12\60\1\0\5\60\2\0\11\60\1\0\3\60\1\0"+
    "\4\60\7\0\2\60\7\0\1\60\1\0\4\60\2\0\12\60\1\0"+
    "\2\60\16\0\3\60\1\0\10\60\1\0\3\60\1\0\51\60\2\0"+
    "\10\60\1\0\3\60\1\0\5\60\10\0\1\60\10\0\4\60\2\0"+
    "\12\60\12\0\6\60\2\0\2\60\1\0\22\60\3\0\30\60\1\0"+
    "\11\60\1\0\1\60\2\0\7\60\3\0\1\60\4\0\6\60\1\0"+
    "\1\60\1\0\10\60\6\0\12\60\2\0\2\60\15\0\72\60\5\0"+
    "\17\60\1\0\12\60\47\0\2\60\1\0\1\60\2\0\2\60\1\0"+
    "\1\60\2\0\1\60\6\0\4\60\1\0\7\60\1\0\3\60\1\0"+
    "\1\60\1\0\1\60\2\0\2\60\1\0\15\60\1\0\3\60\2\0"+
    "\5\60\1\0\1\60\1\0\6\60\2\0\12\60\2\0\4\60\40\0"+
    "\1\60\27\0\2\60\6\0\12\60\13\0\1\60\1\0\1\60\1\0"+
    "\1\60\4\0\12\60\1\0\44\60\4\0\24\60\1\0\22\60\1\0"+
    "\44\60\11\0\1\60\71\0\112\60\6\0\116\60\2\0\46\60\1\0"+
    "\1\60\5\0\1\60\2\0\53\60\1\0\u014d\60\1\0\4\60\2\0"+
    "\7\60\1\0\1\60\1\0\4\60\2\0\51\60\1\0\4\60\2\0"+
    "\41\60\1\0\4\60\2\0\7\60\1\0\1\60\1\0\4\60\2\0"+
    "\17\60\1\0\71\60\1\0\4\60\2\0\103\60\2\0\3\60\40\0"+
    "\20\60\20\0\125\60\14\0\u026c\60\2\0\21\60\1\0\32\60\5\0"+
    "\113\60\3\0\13\60\7\0\15\60\1\0\7\60\13\0\25\60\13\0"+
    "\24\60\14\0\15\60\1\0\3\60\1\0\2\60\14\0\124\60\3\0"+
    "\1\60\4\0\2\60\2\0\12\60\41\0\3\60\2\0\12\60\6\0"+
    "\130\60\10\0\53\60\5\0\106\60\12\0\37\60\1\0\14\60\4\0"+
    "\14\60\12\0\50\60\2\0\5\60\13\0\54\60\4\0\32\60\6\0"+
    "\12\60\46\0\34\60\4\0\77\60\1\0\35\60\2\0\13\60\6\0"+
    "\12\60\15\0\1\60\10\0\17\60\101\0\114\60\4\0\12\60\21\0"+
    "\11\60\14\0\164\60\14\0\70\60\10\0\12\60\3\0\61\60\122\0"+
    "\3\60\1\0\43\60\1\0\2\60\6\0\366\60\6\0\u011a\60\2\0"+
    "\6\60\2\0\46\60\2\0\6\60\2\0\10\60\1\0\1\60\1\0"+
    "\1\60\1\0\1\60\1\0\37\60\2\0\65\60\1\0\7\60\1\0"+
    "\1\60\3\0\3\60\1\0\7\60\3\0\4\60\2\0\6\60\4\0"+
    "\15\60\5\0\3\60\1\0\7\60\53\0\1\0\1\0\25\0\2\60"+
    "\23\0\1\60\34\0\1\60\15\0\1\60\20\0\15\60\63\0\41\60"+
    "\21\0\1\60\4\0\1\60\2\0\12\60\1\0\1\60\3\0\5\60"+
    "\6\0\1\60\1\0\1\60\1\0\1\60\1\0\4\60\1\0\13\60"+
    "\2\0\4\60\5\0\5\60\4\0\1\60\21\0\51\60\u032d\0\64\60"+
    "\u0716\0\57\60\1\0\57\60\1\0\205\60\6\0\11\60\14\0\46\60"+
    "\1\0\1\60\5\0\1\60\2\0\70\60\7\0\1\60\17\0\30\60"+
    "\11\0\7\60\1\0\7\60\1\0\7\60\1\0\7\60\1\0\7\60"+
    "\1\0\7\60\1\0\7\60\1\0\7\60\1\0\40\60\57\0\1\60"+
    "\u01d5\0\3\60\31\0\17\60\1\0\5\60\2\0\5\60\4\0\126\60"+
    "\2\0\2\60\2\0\3\60\1\0\132\60\1\0\4\60\5\0\51\60"+
    "\3\0\136\60\21\0\33\60\65\0\20\60\u0200\0\u19b6\60\112\0\u51cd\60"+
    "\63\0\u048d\60\103\0\56\60\2\0\u010d\60\3\0\34\60\24\0\63\60"+
    "\1\0\12\60\1\0\37\60\1\0\123\60\45\0\11\60\2\0\147\60"+
    "\2\0\4\60\1\0\36\60\2\0\2\60\105\0\61\60\30\0\64\60"+
    "\14\0\105\60\13\0\12\60\6\0\30\60\3\0\1\60\4\0\56\60"+
    "\2\0\44\60\14\0\35\60\3\0\101\60\16\0\13\60\6\0\37\60"+
    "\1\0\67\60\11\0\16\60\2\0\12\60\6\0\27\60\3\0\111\60"+
    "\30\0\3\60\2\0\20\60\2\0\5\60\12\0\6\60\2\0\6\60"+
    "\2\0\6\60\11\0\7\60\1\0\7\60\1\0\53\60\1\0\4\60"+
    "\4\0\2\60\132\0\53\60\1\0\2\60\2\0\12\60\6\0\u2ba4\60"+
    "\14\0\27\60\4\0\61\60\u2104\0\u016e\60\2\0\152\60\46\0\7\60"+
    "\14\0\5\60\5\0\14\60\1\0\15\60\1\0\5\60\1\0\1\60"+
    "\1\0\2\60\1\0\2\60\1\0\154\60\41\0\u016b\60\22\0\100\60"+
    "\2\0\66\60\50\0\14\60\4\0\20\60\20\0\16\60\5\0\2\60"+
    "\30\0\3\60\40\0\5\60\1\0\207\60\23\0\12\60\7\0\32\60"+
    "\4\0\1\60\1\0\32\60\13\0\131\60\3\0\6\60\2\0\6\60"+
    "\2\0\6\60\2\0\3\60\43\0\14\60\1\0\32\60\1\0\23\60"+
    "\1\0\2\60\1\0\17\60\2\0\16\60\42\0\173\60\105\0\65\60"+
    "\210\0\1\60\202\0\35\60\3\0\61\60\17\0\1\60\37\0\40\60"+
    "\20\0\33\60\5\0\53\60\5\0\36\60\2\0\44\60\4\0\10\60"+
    "\1\0\5\60\52\0\236\60\2\0\12\60\126\0\50\60\10\0\64\60"+
    "\234\0\u0137\60\11\0\26\60\12\0\10\60\230\0\6\60\2\0\1\60"+
    "\1\0\54\60\1\0\2\60\3\0\1\60\2\0\27\60\12\0\27\60"+
    "\11\0\37\60\141\0\26\60\12\0\32\60\106\0\70\60\6\0\2\60"+
    "\100\0\4\60\1\0\2\60\5\0\10\60\1\0\3\60\1\0\33\60"+
    "\4\0\3\60\4\0\1\60\40\0\35\60\3\0\35\60\43\0\10\60"+
    "\1\0\36\60\31\0\66\60\12\0\26\60\12\0\23\60\15\0\22\60"+
    "\156\0\111\60\u03b7\0\107\60\37\0\12\60\17\0\74\60\25\0\31\60"+
    "\7\0\12\60\6\0\65\60\1\0\12\60\20\0\44\60\2\0\1\60"+
    "\11\0\105\60\13\0\13\60\45\0\22\60\1\0\45\60\170\0\73\60"+
    "\5\0\12\60\7\0\3\60\1\0\10\60\2\0\2\60\2\0\26\60"+
    "\1\0\7\60\1\0\2\60\1\0\5\60\2\0\11\60\2\0\2\60"+
    "\2\0\3\60\11\0\1\60\5\0\7\60\2\0\7\60\3\0\5\60"+
    "\u010b\0\106\60\1\0\1\60\10\0\12\60\246\0\66\60\2\0\11\60"+
    "\77\0\101\60\3\0\1\60\13\0\12\60\46\0\70\60\10\0\12\60"+
    "\u01d6\0\112\60\25\0\1\60\u01c0\0\71\60\u0507\0\u0399\60\147\0\157\60"+
    "\u0b91\0\u042f\60\u33d1\0\u0239\60\7\0\37\60\1\0\12\60\146\0\36\60"+
    "\2\0\5\60\13\0\67\60\11\0\4\60\14\0\12\60\11\0\25\60"+
    "\5\0\23\60\u0370\0\105\60\13\0\57\60\20\0\21\60\u4060\0\2\60"+
    "\u0bfe\0\153\60\5\0\15\60\3\0\11\60\7\0\12\60\3\0\2\60"+
    "\u14c6\0\5\60\3\0\6\60\10\0\10\60\2\0\7\60\36\0\4\60"+
    "\224\0\3\60\u01bb\0\125\60\1\0\107\60\1\0\2\60\2\0\1\60"+
    "\2\0\2\60\2\0\4\60\1\0\14\60\1\0\1\60\1\0\7\60"+
    "\1\0\101\60\1\0\4\60\2\0\10\60\1\0\7\60\1\0\34\60"+
    "\1\0\4\60\1\0\5\60\1\0\1\60\3\0\7\60\1\0\u0154\60"+
    "\2\0\31\60\1\0\31\60\1\0\37\60\1\0\31\60\1\0\37\60"+
    "\1\0\31\60\1\0\37\60\1\0\31\60\1\0\37\60\1\0\31\60"+
    "\1\0\10\60\2\0\62\60\u1000\0\305\60\13\0\7\60\u0529\0\4\60"+
    "\1\0\33\60\1\0\2\60\1\0\1\60\2\0\1\60\1\0\12\60"+
    "\1\0\4\60\1\0\1\60\1\0\1\60\6\0\1\60\4\0\1\60"+
    "\1\0\1\60\1\0\1\60\1\0\3\60\1\0\2\60\1\0\1\60"+
    "\2\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60\1\0\1\60"+
    "\1\0\2\60\1\0\1\60\2\0\4\60\1\0\7\60\1\0\4\60"+
    "\1\0\4\60\1\0\1\60\1\0\12\60\1\0\21\60\5\0\3\60"+
    "\1\0\5\60\1\0\21\60\u0274\0\32\60\6\0\32\60\6\0\32\60"+
    "\u0e76\0\ua6d7\60\51\0\u1035\60\13\0\336\60\u3fe2\0\u021e\60\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u06ed\0"+
    "\360\60\uffff\0\uffff\0\ufe12\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\1\4\1\2\1\1\1\4"+
    "\10\2\3\5\10\2\3\6\1\7\1\10\1\11\1\12"+
    "\1\1\1\13\1\0\1\2\1\14\1\0\1\2\1\6"+
    "\2\2\1\15\15\2\1\16\1\17\1\3\1\0\1\4"+
    "\12\2\1\14\5\2\1\20\14\2";

  private static int [] zzUnpackAction() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\63\0\146\0\231\0\314\0\377\0\u0132\0\u0165"+
    "\0\u0198\0\u01cb\0\314\0\u01fe\0\u0231\0\u0264\0\u0297\0\u02ca"+
    "\0\u02fd\0\u0330\0\u0363\0\u0396\0\u03c9\0\314\0\u03fc\0\u042f"+
    "\0\u0462\0\u0495\0\u04c8\0\u04fb\0\u052e\0\u0561\0\314\0\u0594"+
    "\0\u05c7\0\314\0\314\0\314\0\314\0\u05fa\0\u062d\0\u0660"+
    "\0\u0693\0\u06c6\0\u06f9\0\u072c\0\377\0\u075f\0\u0792\0\377"+
    "\0\u07c5\0\u07f8\0\u082b\0\u085e\0\u0891\0\u08c4\0\u08f7\0\u092a"+
    "\0\u095d\0\u0990\0\u09c3\0\u09f6\0\u0a29\0\314\0\314\0\u0660"+
    "\0\u0a5c\0\377\0\u0a8f\0\u0ac2\0\u0af5\0\u0b28\0\u0b5b\0\u0b8e"+
    "\0\u0bc1\0\u0bf4\0\u0c27\0\u0c5a\0\u0a5c\0\u0c8d\0\u0cc0\0\u0cf3"+
    "\0\u0d26\0\u0d59\0\377\0\u0d8c\0\u0dbf\0\u0df2\0\u0e25\0\u0e58"+
    "\0\u0e8b\0\u0ebe\0\u0ef1\0\u0f24\0\u0f57\0\u0f8a\0\u0fbd";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\6\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\6\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\6\1\30\1\31"+
    "\1\6\1\32\1\6\1\33\3\6\1\34\4\6\1\35"+
    "\2\6\1\36\1\6\1\37\1\40\1\37\1\41\1\37"+
    "\1\42\1\43\1\5\1\44\63\5\1\45\7\5\1\46"+
    "\132\5\1\47\3\5\64\0\3\6\1\0\1\6\2\0"+
    "\11\6\3\0\25\6\7\0\1\6\4\0\1\7\50\0"+
    "\1\50\11\0\1\7\61\0\3\6\1\0\1\6\2\0"+
    "\7\6\1\51\1\6\3\0\25\6\7\0\1\6\4\0"+
    "\2\52\1\53\35\0\1\52\2\0\1\52\1\0\1\52"+
    "\14\0\3\6\1\0\1\6\2\0\1\6\1\54\2\6"+
    "\1\55\4\6\3\0\25\6\7\0\1\6\3\0\3\6"+
    "\1\0\1\6\2\0\11\6\3\0\5\6\1\55\17\6"+
    "\7\0\1\6\3\0\3\6\1\0\1\6\2\0\6\6"+
    "\1\56\2\6\3\0\25\6\7\0\1\6\3\0\3\6"+
    "\1\0\1\6\2\0\4\6\1\57\4\6\3\0\25\6"+
    "\7\0\1\6\3\0\3\6\1\0\1\6\2\0\5\6"+
    "\1\60\3\6\3\0\5\6\1\55\17\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\7\6\1\61\1\6"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\4\6\1\62\4\6\3\0\25\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\4\6\1\55\4\6"+
    "\3\0\6\6\1\63\3\6\1\64\12\6\7\0\1\6"+
    "\25\0\1\26\60\0\1\26\1\0\1\26\40\0\3\6"+
    "\1\0\1\6\2\0\5\6\1\65\3\6\3\0\25\6"+
    "\7\0\1\6\3\0\3\6\1\0\1\66\2\0\11\6"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\4\6\1\67\1\6\1\70\2\6\3\0\25\6"+
    "\7\0\1\6\3\0\3\6\1\0\1\6\2\0\4\6"+
    "\1\56\4\6\3\0\11\6\1\71\13\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\6\6"+
    "\1\72\16\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\14\6\1\73\10\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\16\6"+
    "\1\74\6\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\24\6\1\75\7\0\1\6\25\0"+
    "\1\37\46\0\1\76\130\0\1\45\64\0\1\77\5\0"+
    "\1\100\61\0\3\6\1\0\1\6\2\0\1\55\10\6"+
    "\3\0\25\6\7\0\1\6\4\0\2\52\36\0\1\52"+
    "\2\0\1\52\1\0\1\52\3\0\1\101\11\0\2\52"+
    "\36\0\1\52\2\0\1\52\1\0\1\52\14\0\3\6"+
    "\1\0\1\6\2\0\2\6\1\102\6\6\3\0\25\6"+
    "\7\0\1\6\3\0\3\6\1\0\1\6\2\0\5\6"+
    "\1\55\3\6\3\0\25\6\7\0\1\6\3\0\3\6"+
    "\1\0\1\6\2\0\1\102\10\6\3\0\25\6\7\0"+
    "\1\6\3\0\3\6\1\0\1\6\2\0\1\60\10\6"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\10\6\1\60\3\0\25\6\7\0\1\6\3\0"+
    "\3\6\1\0\1\103\2\0\11\6\3\0\25\6\7\0"+
    "\1\6\3\0\3\6\1\0\1\6\2\0\11\6\3\0"+
    "\1\104\24\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\4\6\1\105\4\6\3\0\25\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\1\6"+
    "\1\106\23\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\7\6\1\107\1\6\3\0\25\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\4\6"+
    "\1\104\20\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\7\6\1\110\1\6\3\0\25\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\1\6\1\111\7\6"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\15\6\1\112\7\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\21\6"+
    "\1\113\3\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\21\6\1\114\3\6\7\0\1\6"+
    "\4\0\2\115\36\0\1\115\2\0\1\115\1\0\1\115"+
    "\14\0\3\6\1\0\1\6\2\0\7\6\1\55\1\6"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\55"+
    "\2\0\11\6\3\0\25\6\7\0\1\6\3\0\3\6"+
    "\1\0\1\6\2\0\11\6\3\0\1\6\1\116\1\6"+
    "\1\117\21\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\1\6\1\103\7\6\3\0\25\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\4\6"+
    "\1\120\20\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\3\6\1\121\21\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\10\6"+
    "\1\104\14\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\16\6\1\122\6\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\22\6"+
    "\1\123\2\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\20\6\1\124\4\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\5\6\1\125\3\6"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\126"+
    "\2\0\11\6\3\0\25\6\7\0\1\6\3\0\3\6"+
    "\1\0\1\6\2\0\10\6\1\55\3\0\25\6\7\0"+
    "\1\6\3\0\3\6\1\0\1\6\2\0\10\6\1\127"+
    "\3\0\25\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\11\6\3\0\17\6\1\130\5\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\11\6\3\0\21\6"+
    "\1\131\3\6\7\0\1\6\3\0\3\6\1\0\1\6"+
    "\2\0\6\6\1\132\2\6\3\0\25\6\7\0\1\6"+
    "\3\0\3\6\1\0\1\6\2\0\1\133\10\6\3\0"+
    "\25\6\7\0\1\6\3\0\3\6\1\0\1\6\2\0"+
    "\1\6\1\134\7\6\3\0\25\6\7\0\1\6\3\0"+
    "\3\6\1\0\1\6\2\0\11\6\3\0\16\6\1\135"+
    "\6\6\7\0\1\6\3\0\3\6\1\0\1\6\2\0"+
    "\11\6\3\0\23\6\1\136\1\6\7\0\1\6\3\0"+
    "\3\6\1\0\1\6\2\0\3\6\1\55\5\6\3\0"+
    "\25\6\7\0\1\6\3\0\3\6\1\0\1\6\2\0"+
    "\11\6\3\0\11\6\1\137\13\6\7\0\1\6\3\0"+
    "\3\6\1\0\1\6\2\0\4\6\1\103\4\6\3\0"+
    "\25\6\7\0\1\6\3\0\3\6\1\0\1\6\2\0"+
    "\11\6\3\0\20\6\1\123\4\6\7\0\1\6\3\0"+
    "\3\6\1\0\1\6\2\0\11\6\3\0\15\6\1\130"+
    "\7\6\7\0\1\6\3\0\3\6\1\0\1\6\2\0"+
    "\5\6\1\104\3\6\3\0\25\6\7\0\1\6\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4080];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\11\5\1\1\11\12\1\1\11\10\1\1\11"+
    "\2\1\4\11\2\1\1\0\2\1\1\0\22\1\2\11"+
    "\1\1\1\0\36\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  AnalizadorLexicoSintacticoPascal(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2688) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
          { 	return(0);
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.out.print(yytext());
            }
          case 17: break;
          case 2: 
            { System.out.println("IDENTIFICADOR: " + yytext());
            }
          case 18: break;
          case 3: 
            { System.out.println("VALOR DECIMAL: " + yytext());
            }
          case 19: break;
          case 4: 
            { System.out.println("OPERADOR ARITMETICO: " + yytext());
            }
          case 20: break;
          case 5: 
            { System.out.println("OPERADOR DE COMPARACION: " + yytext());
            }
          case 21: break;
          case 6: 
            { System.out.println("RESERVADA: " + yytext());
            }
          case 22: break;
          case 7: 
            { yybegin(COMMENT_KEY);
            }
          case 23: break;
          case 8: 
            { System.out.println("COMIENZA STRING");
			yybegin(LITERAL_CONST);
            }
          case 24: break;
          case 9: 
            { 
            }
          case 25: break;
          case 10: 
            { yybegin(YYINITIAL);
            }
          case 26: break;
          case 11: 
            { System.out.println("\nTERMINA STRING");
			yybegin(YYINITIAL);
            }
          case 27: break;
          case 12: 
            { System.out.println("VALOR HEXADECIMAL: " + yytext());
            }
          case 28: break;
          case 13: 
            { System.out.println("OPERADOR LÓGICO: " + yytext());
            }
          case 29: break;
          case 14: 
            { yybegin(COMMENT_BRACKET);
            }
          case 30: break;
          case 15: 
            { System.out.print("'");
            }
          case 31: break;
          case 16: 
            { System.out.println("TBAS: " + yytext());
            }
          case 32: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java AnalizadorLexicoSintacticoPascal [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        AnalizadorLexicoSintacticoPascal scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new AnalizadorLexicoSintacticoPascal(reader);
          while ( !scanner.zzAtEOF ) scanner.yylex();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
