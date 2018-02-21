package lexer;

import java.io.*;
import java.util.*;



/**
 *
 */
public class Tokenizer {
    public Stack myStack = new Stack();
    //private static int line = 1;
    public char currentChar;
    public char charPlusOne;
    public char charPlusTwo;
    public int countE=0;
    //private char realPeek = ' ';
    public Token toke = new Token(0, " ");
    private static final String VALID_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890" +
                    ".,;:<>/*[]+-=()}{\t ";
    private String Id;
    private String num;
    private int countPoint =0;
    private int lastToken = 0;
    private static InputStream is;
    //Hashtable to store reserved keywords
    private Hashtable ids = new Hashtable<String, Integer>();

    private BufferedReader br;

    public Tokenizer(File file) throws IOException {

        // set up buffered reader

        //try{
            is = new FileInputStream(file);
            Reader reader = new InputStreamReader(is);
            br = new BufferedReader(reader);

        //}catch(FileNotFoundException ex) {
        //    LexicalError.FileError();
        //}
        currentChar = getNextChar();
        charPlusOne = getNextChar();
        charPlusTwo = getNextChar();
        getNextToken();
    }

    private char getNextChar() throws IOException {
        int read;
        char c = ' ';
        if((read =br.read()) != -1) {
            c = (char)read;
        }
        return c;
    }


    //reserving keywords in the hashtable
    private void reserve(Word t) {
        ids.put(t.lexeme, t);
    }

    //reserving keywords to put in the hashtable
    public void Lex() {
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
    }

    private Boolean isValid(char c) {
        String chars = VALID_CHARS;
        int i;
        for (i = 0; i < chars.length(); i++) {
            if (chars.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }
    private Boolean isLetter(char c){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int i;
        for(i=0; i<alphabet.length(); i++){
            if(c ==alphabet.charAt(i)){
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


public  boolean isWhiteSpace(char c){
    if(currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r'||currentChar=='\n') {
        return true;
    }
    return false;
}

    public Token getNextToken() throws IOException {
        //

        if (isValid(currentChar)) {
            if (!isWhiteSpace(currentChar)) {

                if (lastToken == Tag.RIGHTBRACKET) {
                    while (currentChar != '}' && currentChar != '\u0000') {
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (currentChar == '\u0000') {
                        LexicalError.BadComment();
                        setLastToken(Tag.ENDOFFILE);
                        return null;
                    }

                    setLastToken(Tag.LEFTBRACKET);
                    currentChar = charPlusOne;
                    charPlusOne = charPlusTwo;
                    charPlusTwo = getNextChar();
                    return (new Token(lastToken, ""));
                }
                switch (currentChar) {
                    case '=':
                        setLastToken(Tag.RELOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "1"));
                    case '<':
                        //<>
                        if (charPlusOne == '>') {
                            setLastToken(Tag.RELOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "2"));
                        }
                        //<=
                        if (charPlusOne == '=') {
                            setLastToken(Tag.RELOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "5"));
                        }
                        //<
                        setLastToken(Tag.RELOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "3"));

                    case '>':
                        //>=
                        if (charPlusOne == '=') {
                            setLastToken(Tag.RELOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "6"));
                        }
                        setLastToken(Tag.RELOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "4"));
                    case '+':
                        if (getLastToken() == Tag.RIGHTPAREN || getLastToken() == Tag.RIGHTBRACKET || getLastToken() == Tag.IDENTIFIER || getLastToken() == Tag.INTCONSTANT || getLastToken() == Tag.REALCONSTANT) {

                            setLastToken(Tag.ADDOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "1"));
                        } else {
                            setLastToken(Tag.UNARYPLUS);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }


                    case '-':
                        if (getLastToken() == Tag.RIGHTPAREN || getLastToken() == Tag.RIGHTBRACKET || getLastToken() == Tag.IDENTIFIER || getLastToken() == Tag.INTCONSTANT || getLastToken() == Tag.REALCONSTANT) {

                            setLastToken(Tag.ADDOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "2"));
                        } else {
                            setLastToken(Tag.UNARYMINUS);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }

                    case '*':

                        setLastToken(Tag.MULOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "1"));

                    case '/':

                        setLastToken(Tag.MULOP);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "2"));


                    case '{':
                        setLastToken(Tag.RIGHTBRACKET);
                        return (new Token(lastToken, ""));


                    case '}':

                        setLastToken(Tag.LEFTBRACKET);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case ',':

                        setLastToken(Tag.COMMA);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case ';':

                        setLastToken(Tag.SEMICOLON);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case ':':

                        setLastToken(Tag.COLON);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case '(':

                        setLastToken(Tag.RIGHTPAREN);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case ')':

                        setLastToken(Tag.LEFTPAREN);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    //need to deal with periods and stuff
                    case '.':
                        if (charPlusOne == '.') {

                            setLastToken(Tag.DOUBLEDOT);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }

                        setLastToken(Tag.ENDMARKER);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case '\u0000':

                        setLastToken(Tag.ENDOFFILE);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                }
                //if peek is an ID
                if (isLetter(currentChar)) {
                    Id = " ";
                    while (!isWhiteSpace(currentChar) && !isSymbol(currentChar) && Id.length() < 250) {
                        if (isNumber(currentChar)) {
                            Id = Id + currentChar;
                            Id = Id.toUpperCase();
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            int contender = 0;
                        }

                        Id = Id + currentChar;
                        Id = Id.toUpperCase();
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (ids.containsKey(Id)) {

                        if (Id == "OR") {
                            setLastToken(Tag.ADDOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "3"));
                        }
                        if (Id == "DIV") {
                            setLastToken(Tag.MULOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "3"));
                        }
                        if (Id == "MOD") {
                            setLastToken(Tag.MULOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "4"));
                        }
                        if (Id == "AND") {
                            setLastToken(Tag.MULOP);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "5"));
                        }
                        setLastToken(Tag.IDENTIFIER);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, Id));

                    } else if (Id.length() >= 250) {
                        LexicalError.TooLongID();
                        return null;
                    } else {
                        reserve(new Word(Id, ids.size() + 1));

                        setLastToken(Tag.IDENTIFIER);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, Id));
                    }
                }


                //if peek is a number
                if (isNumber(currentChar)) {
                    num = " ";
                    while (!isWhiteSpace(currentChar) && num.length() < 250) {
                        //if current char is a letter
                        if (isLetter(currentChar)) {
                            if (currentChar != 'e' && currentChar != 'E') {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }
                                setLastToken(Tag.INTCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));

                            }
                            //currchar = e
                            if (isLetter(charPlusOne)) {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }
                                setLastToken(Tag.INTCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));


                            }
                            if (countE > 1) {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }

                                setLastToken(Tag.INTCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }
                            countE++;
                            num = num + currentChar;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                        }
                        if (isSymbol(currentChar)) {
                            if (currentChar != '.') {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));

                                }

                                setLastToken(Tag.INTCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }
                            //so currChar=.
                            if (countPoint == 1) {

                                setLastToken(Tag.REALCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }
                            //not already a dot
                            if (charPlusOne == '.') {

                                setLastToken(Tag.DOUBLEDOT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, ""));
                            }
                            //so char is a dot but just normal
                            num = num + currentChar;
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                        }
                        //current char is a number
                        if (charPlusOne == '.' && !isNumber(charPlusTwo)) {
                            num = num + currentChar;
                            if (countPoint == 1) {

                                setLastToken(Tag.REALCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }

                            setLastToken(Tag.INTCONSTANT);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        if ((charPlusOne == 'e' || charPlusOne == 'E') && !isNumber(charPlusTwo)) {
                            num = num + currentChar;
                            if (countPoint == 1) {

                                setLastToken(Tag.REALCONSTANT);
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }

                            setLastToken(Tag.INTCONSTANT);
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        num = num + currentChar;
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (num.length() >= 250) {
                        LexicalError.TooLongID();
                        return null;
                    }
                    if (countPoint == 0) {

                        setLastToken(Tag.INTCONSTANT);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    } else {

                        setLastToken(Tag.INTCONSTANT);
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));

                    }
                }

            }currentChar = charPlusOne;
            charPlusOne = charPlusTwo;
            charPlusTwo = getNextChar();
        }

            //invalid character
            LexicalError.InvalidInput();

            currentChar = charPlusOne;
            charPlusOne = charPlusTwo;
            charPlusTwo = getNextChar();
            setLastToken(Tag.ENDOFFILE);
            return null;
        }




    public void setLastToken(int tag) {
        lastToken = tag;
    }
    public int getLastToken() {
        return lastToken;
    }
}
