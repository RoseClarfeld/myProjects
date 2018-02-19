package lexer;

/**
 * @author Rose Clarfeld
 */

public class Token {
    public final int tag;
    public final String lexeme;


    public Token(String l, int t) {
        tag = t;
        lexeme = l;
    }
    public String toString(){
        // return TYPE VALUE
        return (char)tag+ " "+lexeme;
    }
}

