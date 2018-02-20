package lexer;

public class Word extends Token {
    public final String lexeme;
    public final int tag;
    public Word(String s, int t) {
        super(s, t);
        lexeme = s;
        tag = t;
    }
    String toStringW() {
    return (char)tag + " "+lexeme;
    }


}

