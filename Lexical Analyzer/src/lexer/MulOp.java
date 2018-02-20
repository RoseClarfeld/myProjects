
package lexer;

public class MulOp extends Token {
    public final String lexeme;
    public final int tag;
    public String val;

    public MulOp(String s, int t) {
        super(s, t);
        lexeme = s;
        tag = t;
    }
    public void calcValMO(MulOp mo){
        if(mo == multiplication){
            val = "1";
        }
        if (mo == division) {
            val ="2";
        }
        if(mo ==intDivision){
            val ="3";
        }
        if(mo == modulo){
            val ="4";
        }
        if(mo == and){
            val="5";
        }
    }
    String toStringMO() {
        return (char)tag + " "+val;
    }
    public static final MulOp
            multiplication = new MulOp("*", Tag.MULOP),
            division = new MulOp("/", Tag.MULOP),
    //how to handle int division vs normal division
            intDivision = new MulOp("DIV", Tag.MULOP),
            modulo = new MulOp("MOD", Tag.MULOP),
            and = new MulOp("AND", Tag.MULOP);

}

