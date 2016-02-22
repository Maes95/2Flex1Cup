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
  private static final String ZZ_NL = System.getProperty("line.separator");

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT_KEY = 2;
  public static final int COMMENT_BRACKET = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\44\0\1\6\3\0\1\46\1\47\1\50\1\4\1\44\1\4\1\45"+
    "\1\0\12\2\1\42\1\41\1\0\1\43\3\0\1\33\3\3\1\30"+
    "\1\3\1\31\1\1\1\25\2\1\1\34\1\1\1\26\3\1\1\32"+
    "\1\1\1\27\1\1\1\40\4\1\4\0\1\1\1\0\1\13\1\15"+
    "\1\36\1\20\1\5\1\24\1\12\1\22\1\16\2\1\1\23\1\14"+
    "\1\17\1\11\1\7\1\1\1\10\1\1\1\37\1\35\1\1\1\21"+
    "\3\1\57\0\1\51\12\0\1\51\4\0\1\51\5\0\27\51\1\0"+
    "\37\51\1\0\u01ca\51\4\0\14\51\16\0\5\51\7\0\1\51\1\0"+
    "\1\51\21\0\165\51\1\0\2\51\2\0\4\51\1\0\1\51\6\0"+
    "\1\51\1\0\3\51\1\0\1\51\1\0\24\51\1\0\123\51\1\0"+
    "\213\51\1\0\255\51\1\0\46\51\2\0\1\51\7\0\47\51\11\0"+
    "\55\51\1\0\1\51\1\0\2\51\1\0\2\51\1\0\1\51\10\0"+
    "\33\51\5\0\3\51\35\0\13\51\5\0\112\51\4\0\146\51\1\0"+
    "\10\51\2\0\12\51\1\0\23\51\2\0\1\51\20\0\73\51\2\0"+
    "\145\51\16\0\66\51\4\0\1\51\5\0\56\51\22\0\34\51\104\0"+
    "\23\51\61\0\200\51\2\0\12\51\1\0\23\51\1\0\10\51\2\0"+
    "\2\51\2\0\26\51\1\0\7\51\1\0\1\51\3\0\4\51\2\0"+
    "\11\51\2\0\2\51\2\0\4\51\10\0\1\51\4\0\2\51\1\0"+
    "\5\51\2\0\14\51\17\0\3\51\1\0\6\51\4\0\2\51\2\0"+
    "\26\51\1\0\7\51\1\0\2\51\1\0\2\51\1\0\2\51\2\0"+
    "\1\51\1\0\5\51\4\0\2\51\2\0\3\51\3\0\1\51\7\0"+
    "\4\51\1\0\1\51\7\0\20\51\13\0\3\51\1\0\11\51\1\0"+
    "\3\51\1\0\26\51\1\0\7\51\1\0\2\51\1\0\5\51\2\0"+
    "\12\51\1\0\3\51\1\0\3\51\2\0\1\51\17\0\4\51\2\0"+
    "\12\51\21\0\3\51\1\0\10\51\2\0\2\51\2\0\26\51\1\0"+
    "\7\51\1\0\2\51\1\0\5\51\2\0\11\51\2\0\2\51\2\0"+
    "\3\51\10\0\2\51\4\0\2\51\1\0\5\51\2\0\12\51\1\0"+
    "\1\51\20\0\2\51\1\0\6\51\3\0\3\51\1\0\4\51\3\0"+
    "\2\51\1\0\1\51\1\0\2\51\3\0\2\51\3\0\3\51\3\0"+
    "\14\51\4\0\5\51\3\0\3\51\1\0\4\51\2\0\1\51\6\0"+
    "\1\51\16\0\12\51\20\0\4\51\1\0\10\51\1\0\3\51\1\0"+
    "\27\51\1\0\20\51\3\0\10\51\1\0\3\51\1\0\4\51\7\0"+
    "\2\51\1\0\2\51\6\0\4\51\2\0\12\51\21\0\3\51\1\0"+
    "\10\51\1\0\3\51\1\0\27\51\1\0\12\51\1\0\5\51\2\0"+
    "\11\51\1\0\3\51\1\0\4\51\7\0\2\51\7\0\1\51\1\0"+
    "\4\51\2\0\12\51\1\0\2\51\16\0\3\51\1\0\10\51\1\0"+
    "\3\51\1\0\51\51\2\0\10\51\1\0\3\51\1\0\5\51\10\0"+
    "\1\51\10\0\4\51\2\0\12\51\12\0\6\51\2\0\2\51\1\0"+
    "\22\51\3\0\30\51\1\0\11\51\1\0\1\51\2\0\7\51\3\0"+
    "\1\51\4\0\6\51\1\0\1\51\1\0\10\51\6\0\12\51\2\0"+
    "\2\51\15\0\72\51\5\0\17\51\1\0\12\51\47\0\2\51\1\0"+
    "\1\51\2\0\2\51\1\0\1\51\2\0\1\51\6\0\4\51\1\0"+
    "\7\51\1\0\3\51\1\0\1\51\1\0\1\51\2\0\2\51\1\0"+
    "\15\51\1\0\3\51\2\0\5\51\1\0\1\51\1\0\6\51\2\0"+
    "\12\51\2\0\4\51\40\0\1\51\27\0\2\51\6\0\12\51\13\0"+
    "\1\51\1\0\1\51\1\0\1\51\4\0\12\51\1\0\44\51\4\0"+
    "\24\51\1\0\22\51\1\0\44\51\11\0\1\51\71\0\112\51\6\0"+
    "\116\51\2\0\46\51\1\0\1\51\5\0\1\51\2\0\53\51\1\0"+
    "\u014d\51\1\0\4\51\2\0\7\51\1\0\1\51\1\0\4\51\2\0"+
    "\51\51\1\0\4\51\2\0\41\51\1\0\4\51\2\0\7\51\1\0"+
    "\1\51\1\0\4\51\2\0\17\51\1\0\71\51\1\0\4\51\2\0"+
    "\103\51\2\0\3\51\40\0\20\51\20\0\125\51\14\0\u026c\51\2\0"+
    "\21\51\1\0\32\51\5\0\113\51\3\0\13\51\7\0\15\51\1\0"+
    "\7\51\13\0\25\51\13\0\24\51\14\0\15\51\1\0\3\51\1\0"+
    "\2\51\14\0\124\51\3\0\1\51\4\0\2\51\2\0\12\51\41\0"+
    "\3\51\2\0\12\51\6\0\130\51\10\0\53\51\5\0\106\51\12\0"+
    "\37\51\1\0\14\51\4\0\14\51\12\0\50\51\2\0\5\51\13\0"+
    "\54\51\4\0\32\51\6\0\12\51\46\0\34\51\4\0\77\51\1\0"+
    "\35\51\2\0\13\51\6\0\12\51\15\0\1\51\10\0\17\51\101\0"+
    "\114\51\4\0\12\51\21\0\11\51\14\0\164\51\14\0\70\51\10\0"+
    "\12\51\3\0\61\51\122\0\3\51\1\0\43\51\1\0\2\51\6\0"+
    "\366\51\6\0\u011a\51\2\0\6\51\2\0\46\51\2\0\6\51\2\0"+
    "\10\51\1\0\1\51\1\0\1\51\1\0\1\51\1\0\37\51\2\0"+
    "\65\51\1\0\7\51\1\0\1\51\3\0\3\51\1\0\7\51\3\0"+
    "\4\51\2\0\6\51\4\0\15\51\5\0\3\51\1\0\7\51\102\0"+
    "\2\51\23\0\1\51\34\0\1\51\15\0\1\51\20\0\15\51\63\0"+
    "\41\51\21\0\1\51\4\0\1\51\2\0\12\51\1\0\1\51\3\0"+
    "\5\51\6\0\1\51\1\0\1\51\1\0\1\51\1\0\4\51\1\0"+
    "\13\51\2\0\4\51\5\0\5\51\4\0\1\51\21\0\51\51\u032d\0"+
    "\64\51\u0716\0\57\51\1\0\57\51\1\0\205\51\6\0\11\51\14\0"+
    "\46\51\1\0\1\51\5\0\1\51\2\0\70\51\7\0\1\51\17\0"+
    "\30\51\11\0\7\51\1\0\7\51\1\0\7\51\1\0\7\51\1\0"+
    "\7\51\1\0\7\51\1\0\7\51\1\0\7\51\1\0\40\51\57\0"+
    "\1\51\u01d5\0\3\51\31\0\17\51\1\0\5\51\2\0\5\51\4\0"+
    "\126\51\2\0\2\51\2\0\3\51\1\0\132\51\1\0\4\51\5\0"+
    "\51\51\3\0\136\51\21\0\33\51\65\0\20\51\u0200\0\u19b6\51\112\0"+
    "\u51cd\51\63\0\u048d\51\103\0\56\51\2\0\u010d\51\3\0\34\51\24\0"+
    "\63\51\1\0\12\51\1\0\37\51\1\0\123\51\45\0\11\51\2\0"+
    "\147\51\2\0\4\51\1\0\36\51\2\0\2\51\105\0\61\51\30\0"+
    "\64\51\14\0\105\51\13\0\12\51\6\0\30\51\3\0\1\51\4\0"+
    "\56\51\2\0\44\51\14\0\35\51\3\0\101\51\16\0\13\51\6\0"+
    "\37\51\1\0\67\51\11\0\16\51\2\0\12\51\6\0\27\51\3\0"+
    "\111\51\30\0\3\51\2\0\20\51\2\0\5\51\12\0\6\51\2\0"+
    "\6\51\2\0\6\51\11\0\7\51\1\0\7\51\1\0\53\51\1\0"+
    "\4\51\4\0\2\51\132\0\53\51\1\0\2\51\2\0\12\51\6\0"+
    "\u2ba4\51\14\0\27\51\4\0\61\51\u2104\0\u016e\51\2\0\152\51\46\0"+
    "\7\51\14\0\5\51\5\0\14\51\1\0\15\51\1\0\5\51\1\0"+
    "\1\51\1\0\2\51\1\0\2\51\1\0\154\51\41\0\u016b\51\22\0"+
    "\100\51\2\0\66\51\50\0\14\51\4\0\20\51\20\0\16\51\5\0"+
    "\2\51\30\0\3\51\40\0\5\51\1\0\207\51\23\0\12\51\7\0"+
    "\32\51\4\0\1\51\1\0\32\51\13\0\131\51\3\0\6\51\2\0"+
    "\6\51\2\0\6\51\2\0\3\51\43\0\14\51\1\0\32\51\1\0"+
    "\23\51\1\0\2\51\1\0\17\51\2\0\16\51\42\0\173\51\105\0"+
    "\65\51\210\0\1\51\202\0\35\51\3\0\61\51\17\0\1\51\37\0"+
    "\40\51\20\0\33\51\5\0\53\51\5\0\36\51\2\0\44\51\4\0"+
    "\10\51\1\0\5\51\52\0\236\51\2\0\12\51\126\0\50\51\10\0"+
    "\64\51\234\0\u0137\51\11\0\26\51\12\0\10\51\230\0\6\51\2\0"+
    "\1\51\1\0\54\51\1\0\2\51\3\0\1\51\2\0\27\51\12\0"+
    "\27\51\11\0\37\51\141\0\26\51\12\0\32\51\106\0\70\51\6\0"+
    "\2\51\100\0\4\51\1\0\2\51\5\0\10\51\1\0\3\51\1\0"+
    "\33\51\4\0\3\51\4\0\1\51\40\0\35\51\3\0\35\51\43\0"+
    "\10\51\1\0\36\51\31\0\66\51\12\0\26\51\12\0\23\51\15\0"+
    "\22\51\156\0\111\51\u03b7\0\107\51\37\0\12\51\17\0\74\51\25\0"+
    "\31\51\7\0\12\51\6\0\65\51\1\0\12\51\20\0\44\51\2\0"+
    "\1\51\11\0\105\51\13\0\13\51\45\0\22\51\1\0\45\51\170\0"+
    "\73\51\5\0\12\51\7\0\3\51\1\0\10\51\2\0\2\51\2\0"+
    "\26\51\1\0\7\51\1\0\2\51\1\0\5\51\2\0\11\51\2\0"+
    "\2\51\2\0\3\51\11\0\1\51\5\0\7\51\2\0\7\51\3\0"+
    "\5\51\u010b\0\106\51\1\0\1\51\10\0\12\51\246\0\66\51\2\0"+
    "\11\51\77\0\101\51\3\0\1\51\13\0\12\51\46\0\70\51\10\0"+
    "\12\51\u01d6\0\112\51\25\0\1\51\u01c0\0\71\51\u0507\0\u0399\51\147\0"+
    "\157\51\u0b91\0\u042f\51\u33d1\0\u0239\51\7\0\37\51\1\0\12\51\146\0"+
    "\36\51\2\0\5\51\13\0\67\51\11\0\4\51\14\0\12\51\11\0"+
    "\25\51\5\0\23\51\u0370\0\105\51\13\0\57\51\20\0\21\51\u4060\0"+
    "\2\51\u0bfe\0\153\51\5\0\15\51\3\0\11\51\7\0\12\51\3\0"+
    "\2\51\u14c6\0\5\51\3\0\6\51\10\0\10\51\2\0\7\51\36\0"+
    "\4\51\224\0\3\51\u01bb\0\125\51\1\0\107\51\1\0\2\51\2\0"+
    "\1\51\2\0\2\51\2\0\4\51\1\0\14\51\1\0\1\51\1\0"+
    "\7\51\1\0\101\51\1\0\4\51\2\0\10\51\1\0\7\51\1\0"+
    "\34\51\1\0\4\51\1\0\5\51\1\0\1\51\3\0\7\51\1\0"+
    "\u0154\51\2\0\31\51\1\0\31\51\1\0\37\51\1\0\31\51\1\0"+
    "\37\51\1\0\31\51\1\0\37\51\1\0\31\51\1\0\37\51\1\0"+
    "\31\51\1\0\10\51\2\0\62\51\u1000\0\305\51\13\0\7\51\u0529\0"+
    "\4\51\1\0\33\51\1\0\2\51\1\0\1\51\2\0\1\51\1\0"+
    "\12\51\1\0\4\51\1\0\1\51\1\0\1\51\6\0\1\51\4\0"+
    "\1\51\1\0\1\51\1\0\1\51\1\0\3\51\1\0\2\51\1\0"+
    "\1\51\2\0\1\51\1\0\1\51\1\0\1\51\1\0\1\51\1\0"+
    "\1\51\1\0\2\51\1\0\1\51\2\0\4\51\1\0\7\51\1\0"+
    "\4\51\1\0\4\51\1\0\1\51\1\0\12\51\1\0\21\51\5\0"+
    "\3\51\1\0\5\51\1\0\21\51\u0274\0\32\51\6\0\32\51\6\0"+
    "\32\51\u0e76\0\ua6d7\51\51\0\u1035\51\13\0\336\51\u3fe2\0\u021e\51\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u06ed\0"+
    "\360\51\uffff\0\uffff\0\ufe12\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\2\1\1\2\3\1\2\0"+
    "\1\4\1\2\1\5\1\0\1\6\1\3\1\0\1\2"+
    "\1\4\65\2\12\0\1\7";

  private static int [] zzUnpackAction() {
    int [] result = new int[87];
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
    "\0\0\0\52\0\124\0\176\0\250\0\322\0\374\0\u0126"+
    "\0\u0150\0\u017a\0\u01a4\0\u01ce\0\u01f8\0\u0126\0\u0222\0\u024c"+
    "\0\176\0\u0276\0\176\0\u01f8\0\u02a0\0\u02ca\0\u02a0\0\u02f4"+
    "\0\u031e\0\u0348\0\u0372\0\u039c\0\u03c6\0\u03f0\0\u041a\0\u0444"+
    "\0\u046e\0\u0498\0\u04c2\0\u04ec\0\u0516\0\u0540\0\u056a\0\u0594"+
    "\0\u05be\0\u05e8\0\u0612\0\u063c\0\u0666\0\u0690\0\u06ba\0\u06e4"+
    "\0\u070e\0\u0738\0\u0762\0\u078c\0\u07b6\0\u07e0\0\u080a\0\u0834"+
    "\0\u085e\0\u0888\0\u08b2\0\u08dc\0\u0906\0\u0930\0\u095a\0\u0984"+
    "\0\u09ae\0\u09d8\0\u0a02\0\u0a2c\0\u0a56\0\u0a80\0\u0aaa\0\u0ad4"+
    "\0\u0afe\0\u0b28\0\u0b52\0\u0b7c\0\u0ba6\0\u0bd0\0\u0bfa\0\u0c24"+
    "\0\u0c4e\0\u0c78\0\u0ca2\0\u0ccc\0\u0cf6\0\u0d20\0\176";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[87];
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
    "\1\4\1\5\1\6\1\5\1\7\1\5\1\10\1\11"+
    "\31\5\5\4\1\12\41\4\1\13\63\4\1\14\1\4"+
    "\53\0\3\5\1\0\1\5\1\0\32\5\10\0\1\5"+
    "\2\0\1\6\42\0\1\15\6\0\1\6\3\0\1\16"+
    "\45\0\2\17\24\0\1\17\2\0\1\17\17\0\3\5"+
    "\1\0\1\5\1\0\1\5\1\20\30\5\10\0\1\5"+
    "\50\0\1\21\12\0\1\22\107\0\1\23\4\0\1\24"+
    "\51\0\2\17\24\0\1\17\2\0\1\17\11\0\1\25"+
    "\5\0\3\5\1\0\1\5\1\0\2\5\1\26\27\5"+
    "\10\0\1\5\14\0\1\23\37\0\2\27\24\0\1\27"+
    "\2\0\1\27\17\0\3\5\1\0\1\5\1\0\3\5"+
    "\1\30\26\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\1\5\1\31\30\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\4\5\1\32\25\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\5\5\1\33\24\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\6\5"+
    "\1\34\23\5\10\0\1\5\1\0\3\5\1\0\1\35"+
    "\1\0\32\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\3\5\1\36\26\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\7\5\1\37\22\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\10\5\1\40\21\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\41\1\0\32\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\10\5"+
    "\1\42\21\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\11\5\1\43\20\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\12\5\1\44\17\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\13\5\1\45\16\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\7\5"+
    "\1\46\22\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\14\5\1\47\15\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\50\1\0\32\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\15\5\1\51\14\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\2\5\1\52\27\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\1\5"+
    "\1\53\30\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\11\5\1\54\20\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\2\5\1\55\27\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\16\5\1\56\13\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\17\5"+
    "\1\57\12\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\20\5\1\60\11\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\21\5\1\61\10\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\22\5\1\62\7\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\21\5"+
    "\1\63\10\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\23\5\1\64\6\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\23\5\1\65\6\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\21\5\1\66\10\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\24\5"+
    "\1\67\5\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\25\5\1\70\4\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\15\5\1\71\14\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\26\5\1\72\3\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\10\5"+
    "\1\73\21\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\27\5\1\74\2\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\30\5\1\75\1\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\7\5\1\76\22\5"+
    "\10\0\1\5\1\0\3\5\1\0\1\5\1\0\2\5"+
    "\1\77\27\5\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\10\5\1\100\21\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\1\101\31\5\10\0\1\5\1\0"+
    "\3\5\1\0\1\5\1\0\1\5\1\102\30\5\10\0"+
    "\1\5\1\0\3\5\1\0\1\5\1\0\2\5\1\103"+
    "\27\5\10\0\1\5\1\0\3\5\1\0\1\5\1\0"+
    "\27\5\1\104\2\5\10\0\1\5\1\0\3\5\1\0"+
    "\1\105\1\0\32\5\10\0\1\5\1\0\3\5\1\0"+
    "\1\5\1\0\11\5\1\106\20\5\10\0\1\5\1\0"+
    "\3\5\1\0\1\5\1\0\26\5\1\107\3\5\10\0"+
    "\1\5\1\0\3\5\1\0\1\5\1\0\1\5\1\110"+
    "\30\5\10\0\1\5\1\0\3\5\1\0\1\111\1\0"+
    "\32\5\10\0\1\5\1\0\3\5\1\0\1\5\1\0"+
    "\31\5\1\112\10\0\1\5\1\0\3\5\1\0\1\5"+
    "\1\0\24\5\1\113\5\5\10\0\1\5\1\0\3\5"+
    "\1\0\1\5\1\0\23\5\1\114\6\5\10\0\1\5"+
    "\1\0\3\5\1\0\1\5\1\0\32\5\1\115\7\0"+
    "\1\5\42\0\1\116\51\0\1\117\52\0\1\120\52\0"+
    "\1\121\52\0\1\122\52\0\1\123\52\0\1\124\40\0"+
    "\1\125\24\0\1\126\54\0\1\127\35\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3402];
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
    "\3\0\1\11\10\1\2\0\2\1\1\11\1\0\1\11"+
    "\1\1\1\0\67\1\12\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[87];
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
    while (i < 2650) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  private static String zzToPrintable(String str) {
    StringBuilder builder = new StringBuilder();
    for (int n = 0 ; n < str.length() ; ) {
      int ch = str.codePointAt(n);
      int charCount = Character.charCount(ch);
      n += charCount;
      if (ch > 31 && ch < 127) {
        builder.append((char)ch);
      } else if (charCount == 1) {
        builder.append(String.format("\\u%04X", ch));
      } else {
        builder.append(String.format("\\U%06X", ch));
      }
    }
    return builder.toString();
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
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [2147483647] { System.out.print(yytext()); }");
            { System.out.print(yytext());
            }
          case 8: break;
          case 2: 
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [81] { System.out.println(\"IDENTIFICADOR: \" + yytext()); }");
            { System.out.println("IDENTIFICADOR: " + yytext());
            }
          case 9: break;
          case 3: 
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [71] { System.out.println(\"VALOR DECIMAL: \" + yytext()); }");
            { System.out.println("VALOR DECIMAL: " + yytext());
            }
          case 10: break;
          case 4: 
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [76] { System.out.println(\"VALOR HEXADECIMAL: \" + yytext()); }");
            { System.out.println("VALOR HEXADECIMAL: " + yytext());
            }
          case 11: break;
          case 5: 
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [66] { yybegin(COMMENT_BRACKET); }");
            { yybegin(COMMENT_BRACKET);
            }
          case 12: break;
          case 6: 
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [94] { yybegin(YYINITIAL); }");
            { yybegin(YYINITIAL);
            }
          case 13: break;
          case 7: 
            System.out.println("match: --"+zzToPrintable(yytext())+"--");
            System.out.println("action [61] { yybegin(COMMENT_KEY); }");
            { yybegin(COMMENT_KEY);
            }
          case 14: break;
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
