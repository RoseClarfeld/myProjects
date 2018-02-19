package lexer;

public class RelOp extends Token {
    public final String lexeme;
    public final int tag;
    public String val;

    public RelOp(String s, int t) {
        super( s, t);
        lexeme = s;
        tag = t;
    }
    public void calcValRO(RelOp ro){
        if(ro == equal) {
            val = "1";
        }
        if (ro==notEqual) {
            val = "2";
        }
        if (ro ==lessThan) {
            val = "3";
        }
        if (ro == greaterThan) {
            val = "4";
        }
        if(ro == lessThanOrEqual){
            val ="5";
        }
        if(ro == greaterThanOrEqual){
            val="6";
        }
    }
    String toStringRO() {
        return tag + " "+val;
    }
    public static final RelOp
            equal = new RelOp("=", Tag.RELOP),
            notEqual = new RelOp("<>", Tag.RELOP),
            lessThan = new RelOp("<", Tag.RELOP),
            greaterThan = new RelOp(">", Tag.RELOP),
            lessThanOrEqual = new RelOp("<=", Tag.RELOP),
            greaterThanOrEqual = new RelOp(">=", Tag.RELOP);

}
