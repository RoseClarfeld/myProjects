package lexer;

public class Word extends Token {
    public final String lexeme;
    public final int tag;
    public Word(String s, int t) {
        super(t, s);
        lexeme = s;
        tag = t;
    }
    String toStringW() {
    return (char)tag + " "+lexeme;
    }


}
