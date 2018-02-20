package lexer;

import java.io.*;
import java.util.*;



/**
 *
 */
public class lexo {
    private static int line = 1;
    private char peek = ' ';
    private char realPeek = ' ';
    public Token toke = new Token(" ", 0);
    private static final String VALID_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890" +
                    ".,;:<>/*[]+-=()}{\t ";
    private String Id;
    private String num;
    private int countPoint =0;
    private int lastToken = 0;
    //Hashtable to store reserved keywords
    private Hashtable ids = new Hashtable<String, Integer>();

    //reserving keywords in the hashtable
    private void reserve(Word t) {
        ids.put(t.lexeme, t);
    }

    //reserving keywords to put in the hashtable
    public void Lexer() {
        reserve(new Word("PROGRAM", Tag.PROGRAM));
        reserve(new Word("BEGIN", Tag.BEGIN ));
        reserve(new Word("END", Tag.END));
        reserve(new Word("VAR", Tag.VAR));
        reserve(new Word("FUNCTION", Tag.FUNCTION));
        reserve(new Word("PROCEDURE", Tag.PROCEDURE));
        reserve(new Word("RESULT", Tag.RESULT));
        reserve(new Word("INTEGER", Tag.INTEGER));
        reserve(new Word("REAL", Tag.REAL));
        reserve(new Word("ARRAY", Tag.ARRAY));
        reserve(new Word("OF", Tag.OF));
        reserve(new Word("IF", Tag.IF));
        reserve(new Word("THEN", Tag.THEN));
        reserve(new Word("ELSE", Tag.ELSE));
        reserve(new Word("WHILE", Tag.WHILE));
        reserve(new Word("DO", Tag.DO));
        reserve(new Word("NOT", Tag.NOT));
        reserve(new Word( "IDENTIFIER", Tag.IDENTIFIER));
        reserve(new Word("INTCONSTANT", Tag.INTCONSTANT));
        reserve(new Word("REALCONSTANT", Tag.REALCONSTANT));
        reserve(new Word("RELOP", Tag.RELOP));
        reserve(new Word("MULOP", Tag.MULOP));
        reserve(new Word("ADDOP", Tag.ADDOP));
        reserve(new Word("OR", Tag.ADDOP));
        reserve(new Word("DIV", Tag.MULOP));
        reserve(new Word("MOD", Tag.MULOP));
        reserve(new Word("AND", Tag.MULOP));
        reserve(new Word("ASSIGNOP", Tag.ASSIGNOP));
        reserve(new Word("COMMA", Tag.COMMA));
        reserve(new Word("SEMICOLON", Tag.SEMICOLON));
        reserve(new Word("COLON", Tag.COLON));
        reserve(new Word("RIGHTPAREN", Tag.RIGHTPAREN));
        reserve(new Word("LEFTPAREN", Tag.LEFTPAREN));
        reserve(new Word("RIGHTBRACKET", Tag.RIGHTBRACKET));
        reserve(new Word("LEFTBRACKET", Tag.LEFTBRACKET));
        reserve(new Word("UNARYMINUS", Tag.UNARYMINUS));
        reserve(new Word("UNARYPLUS", Tag.UNARYPLUS));
        reserve(new Word("DOUBLEDOT", Tag.DOUBLEDOT));
        reserve(new Word("ENDMARKER", Tag.ENDMARKER));
        reserve(new Word("ENDOFFILE", Tag.ENDOFFILE));
        /*

        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Float);
        reserve(Type.Bool);
        reserve(Type.Char);
    */ }

    private Boolean isValid(char c) {
        String chars = VALID_CHARS;
        int i;
        for (i = 0; i < chars.length(); i++) {
            char a = chars.charAt(i);
            if (c == a) {
                return true;
            }
        }
        return false;
    }
    private Boolean isLetter(char c){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int i;
        for(i=0; i<alphabet.length(); i++){
            char a= alphabet.charAt(i);
            if(c ==a){
                return true;
            }
        }
        return false;
    }
    private Boolean hexOrEx(char c){
        String valiD = "abcdefABCDEF";
        int i;
        for(i=0; i<valiD.length(); i++){
            char a=valiD.charAt(i);
            if(c==a){
                return true;
            }
        }
    return false;
    }

    private Boolean isNumber(char c){
        String numbers = "1234567890";
        int i;
        for(i=0; i<numbers.length(); i++){
            char a = numbers.charAt(i);
            if (c==a){
                return true;
            }
        }
        return false;
    }
    private Boolean isSymbol(char c){
        String symbols =".,;:<>/*[]+-=()}{\t ";
        int i;
        for(i=0; i<symbols.length(); i++){
            char a = symbols.charAt(i);
            if (c==a){
                return true;
            }
        }
        return false;
    }
    public BufferedReader readFile(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader;
        } catch (FileNotFoundException ex) {
            System.out.println("Error: unable to access file");
        }

        return null;
    }

    public void readAhead() throws IOException{
        BufferedReader br = readFile(new File("textfile.txt"));
        peek = (char)br.read();
        realPeek =(char)br.read();

    }
/*
    boolean readAhead(char c) throws IOException {
        readAhead();
        if (peek != c) {
            return false
        }
        peek = ' ';
        return true;
    }*/

    public void Lscan() throws IOException {
        //if peek is a valid character continue
        readAhead();
        if (isValid(peek)) {
            //skipping white space
            while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
                if (peek == '\n') {
                    line++;
                }
                readAhead();
            }
            if (peek == '{') {
                System.out.println("RIGHTBRACKET");
                lastToken = Tag.RIGHTBRACKET;
                while (peek != '}') {
                    if(peek=='\u0000'){
                    System.out.println("Error: Ill-formed comment");
                    return;
                    }
                    readAhead();
                }
            }
            if (peek == '}') {
                System.out.println("LEFTBRACKET");
                lastToken = Tag.LEFTBRACKET;
                readAhead();
            }
            if (peek == ',') {
                System.out.println("COMMA");
                lastToken=Tag.COMMA;
                readAhead();
            }
            if (peek == ';') {
                System.out.println("SEMICOLON");
                lastToken=Tag.SEMICOLON;
                readAhead();
            }
            if (peek == ':') {
                System.out.println("COLON");
                lastToken=Tag.COLON;
                readAhead();
            }
            if (peek == '(') {
                System.out.println("RIGHTPAREN");
                lastToken=Tag.RIGHTPAREN;
                readAhead();
            }
            if (peek == ')') {
                System.out.println("LEFTPAREN");
                lastToken=Tag.LEFTPAREN;
                readAhead();
            }
            if (peek == '.') {
                if(System.in.read()=='.'){
                    System.out.println("DOUBLEDOT");
                    lastToken=Tag.DOUBLEDOT;
                }
                System.out.println("ENDMARKER");
                lastToken=Tag.ENDMARKER;
                readAhead();
            }
            if (peek == '\u0000') {
                System.out.println("ENDOFFILE");
                lastToken=Tag.ENDOFFILE;
                return;
            }

            //if we are only dealing with a single character
            if (realPeek == ' ' || realPeek == '\t' || realPeek == '\n' || realPeek == '\r') {

                switch (peek) {
                    case '=':
                        System.out.println(RelOp.equal.toStringRO());
                        lastToken=Tag.RELOP;
                        readAhead();
                        break;
                    case '<':
                        if ((char) System.in.read() == '>') {
                            System.out.println(RelOp.notEqual.toStringRO());
                            lastToken=Tag.RELOP;
                            peek = (char) System.in.read();
                            readAhead();
                            break;
                        }
                        if ((char) System.in.read() == '=') {
                            System.out.println(RelOp.lessThanOrEqual.toStringRO());
                            lastToken=Tag.RELOP;
                            peek = (char) System.in.read();
                            readAhead();
                            break;
                        }
                        System.out.println(RelOp.lessThan.toStringRO());
                        lastToken=Tag.RELOP;
                        readAhead();
                        break;
                    case '>':
                        if ((char) System.in.read() == '=') {
                            System.out.println(RelOp.greaterThanOrEqual.toStringRO());
                            lastToken=Tag.RELOP;
                            peek = (char) System.in.read();
                            readAhead();
                            break;
                        }
                        System.out.println(RelOp.greaterThan.toStringRO());
                        lastToken=Tag.RELOP;
                        readAhead();
                        break;
                    case '+':
                        if(lastToken==Tag.RIGHTPAREN||lastToken==Tag.RIGHTBRACKET||lastToken==Tag.IDENTIFIER||lastToken==Tag.INTCONSTANT||lastToken==Tag.REALCONSTANT) {

                            System.out.println(AddOp.addition.toStringAO());
                            lastToken = Tag.ADDOP;
                            readAhead();
                        }else{
                            System.out.println("UNARYPLUS");
                        }
                        break;
                    case '-':
                        if(lastToken==Tag.RIGHTPAREN||lastToken==Tag.RIGHTBRACKET||lastToken==Tag.IDENTIFIER||lastToken==Tag.INTCONSTANT||lastToken==Tag.REALCONSTANT) {

                            System.out.println(AddOp.subtraction.toStringAO());
                            lastToken = Tag.ADDOP;
                            readAhead();
                        }else{
                            System.out.println("UNARYMINUS");
                        }
                        break;
                    case '*':
                        System.out.println(MulOp.multiplication.toStringMO());
                        lastToken=Tag.MULOP;
                        readAhead();
                        break;
                    case '/':
                        System.out.println(MulOp.division.toStringMO());
                        lastToken=Tag.MULOP;
                }

                //if peek is an ID
                if (isLetter(peek)) {
                    Id = "";
                    while (peek != ' ' || peek != '\t' || peek != '\n' || peek != '\r' || !isSymbol(peek)||Id.length()<250) {
                        if (isNumber(peek)) {
                            Id = Id + peek;
                            Id = Id.toUpperCase();
                            readAhead();
                        } else {
                            Id = Id + peek;
                            Id = Id.toUpperCase();
                            if (ids.containsKey(Id)) {
                                System.out.println(Id + " " + Id);
                                readAhead();
                            }
                        }
                    }
                    if(Id.length()>=250){
                        System.out.println("Error: Identifier too long");
                    }
                    if (ids.containsKey(Id)) {
                        System.out.println(Id.toUpperCase() + " " + Id.toUpperCase());
                        readAhead();
                    }

                    reserve(new Word(Id, ids.size() + 1));
                    System.out.println("IDENTIFIER" + " " + Id);
                    lastToken= Tag.IDENTIFIER;
                    readAhead();
                }


                //if peek is a number
                if (isNumber(peek)) {
                    num = "";
                    while (peek != ' ' || peek != '\t' || peek != '\n' || peek != '\r' || num.length()<250) {
                        if(isLetter(peek)){
                            if(hexOrEx(peek)){
                                if(!hexOrEx((char)System.in.read()) && !isNumber((char)System.in.read())) {
                                    if (countPoint == 1) {
                                        System.out.println("REALCONSTANT " + num);
                                        lastToken=Tag.REALCONSTANT;
                                        readAhead();
                                    }
                                    System.out.println("INTCONSTANT " + num);
                                    lastToken=Tag.INTCONSTANT;
                                    readAhead();
                                }
                                num = num + peek;
                                readAhead();
                            }
                            if (countPoint == 1) {
                                System.out.println("REALCONSTANT " + num);
                                lastToken=Tag.REALCONSTANT;
                                readAhead();
                            }
                            System.out.println("INTCONSTANT " + num);
                            lastToken=Tag.INTCONSTANT;
                            readAhead();
                        }
                        if (isSymbol(peek)) {
                            if (peek != '.') {
                                if (countPoint == 1) {
                                    System.out.println("REALCONSTANT " + num);
                                    lastToken=Tag.REALCONSTANT;
                                    readAhead();
                                }
                                System.out.println("INTCONSTANT " + num);
                                lastToken=Tag.INTCONSTANT;
                                readAhead();
                            }
                            countPoint++;
                            if (countPoint > 1) {
                                System.out.println("REALCONSTANT " + num);
                                lastToken=Tag.REALCONSTANT;
                                readAhead();
                            }
                            if(System.in.read()=='.'){
                                System.out.println("DOUBLEDOT");
                                lastToken=Tag.DOUBLEDOT;
                                peek= (char)System.in.read();
                            }
                            num = num + peek;
                            readAhead();
                        }
                        num = num + peek;
                        readAhead();
                    }
                    if(num.length()>=250){
                        System.out.println("Error: Identifier too long");
                        return;
                    }
                    if (countPoint == 0) {
                        System.out.println("INTCONSTANT" + " " + num);
                        lastToken=Tag.INTCONSTANT;
                        readAhead();
                    } else {
                        System.out.println("REALCONSTANT" + " " + num);
                        lastToken=Tag.INTCONSTANT;
                        readAhead();

                    }
                }
            }

        }
        //invalid character
        System.out.println("Error: Invalid character in the input");
        readAhead();
    }

    }
