package lexer;

public class AddOp extends Token{
    public String val;
    public final int tag;
    public final String lexeme;

    public AddOp(String s, int t) {
        super(s, t);
        lexeme = s;
        tag = t;
    }
    public void calcValAO(AddOp ao){
        if(ao== addition) {
            val = "1";
        }
        if(ao == subtraction){
            val = "2";
        }
        if(ao == boolOr){
            val = "3";
        }
    }
    String toStringAO() {
        return (char)tag + " "+val;
    }
    public static final AddOp
            addition = new AddOp("+", Tag.ADDOP),
            subtraction = new AddOp("-", Tag.ADDOP),
            boolOr = new AddOp("OR", Tag.ADDOP);

}

