package lexer;

import java.io.*;
import java.util.*;
import java.lang.Object.*;


/**
 *
 */
public class Tokenizer {
    public char currentChar;
    public char charPlusOne;
    public char charPlusTwo;
    public static String[] currLine;
    public int countE = 0;
    public static int stringLength = 0;
    public int numLines = 0;
    public static String cLine = " ";
    public static int indexOfLineArray = 0;
    private static final String VALID_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890" +
                    ".,;:<>/*[]+-=()}{\t ";
    private String Id;
    private String num;
    private int countPoint = 0;
    private int lastToken = 0;
    private static InputStream is;
    //Hashtable to store reserved keywords
    private Hashtable ids = new Hashtable<String, Integer>();

    private BufferedReader br;
    public void calcNumLines(File file)throws IOException{
        // getting the number of lines to put into the array
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while ((line = br.readLine())!=null) {
            numLines++;
        }
        br.close();
    }
    public Tokenizer(File file) throws IOException {
        calcNumLines(file);
        //new buffered reader
        currLine = new String[numLines];
        FileReader fRead = new FileReader(file);
        BufferedReader bRead = new BufferedReader(fRead);
        //entering all the lines into an array
        String lineRead;
        for (indexOfLineArray = 0; indexOfLineArray < numLines; indexOfLineArray++) {
            lineRead = bRead.readLine();
            currLine[indexOfLineArray] = lineRead;
        }
        indexOfLineArray = 0;
        stringLength = 0;
        currentChar = getNextChar();
        if (stringLength == (cLine.length())) {
            indexOfLineArray++;
            cLine = currLine[indexOfLineArray];
            stringLength = 0;
        }
        charPlusOne = getNextChar();
        if (stringLength == (cLine.length())) {
            indexOfLineArray++;
            cLine = currLine[indexOfLineArray];
            stringLength = 0;
        }
        charPlusTwo = getNextChar();
    }

    private char getNextChar() throws IOException {
        //cLine is a string that the current line goes into
        cLine = currLine[indexOfLineArray];
        char c = ' ';
        if (cLine.isEmpty()) {
            indexOfLineArray++;
            cLine = currLine[indexOfLineArray];
            stringLength = 0;
            c = cLine.charAt(0);
            // while (c == '\n' || c == '\r') {
            //   indexOfLineArray++;
            // cLine = currLine[indexOfLineArray];
            // stringLength = 0;
            // c = cLine.charAt(0);
            return c;
        } else {
            c = cLine.charAt(stringLength);
            stringLength++;
            /*if(cLine.charAt((stringLength))=='\n'){
                indexOfLineArray++;
                cLine=currLine[indexOfLineArray];
                stringLength=0;
                c=cLine.charAt(0);*/

            return c;
        }
    }

    /*
        c =cLine.charAt((stringLength));
    //if cLine is empty
        if (c=='\n'||c=='\r'){
            //go to the next line
            indexOfLineArray++;
            //cline is updated
            cLine = currLine[indexOfLineArray];
            //
            stringLength=0;
            c=cLine.charAt((stringLength));
        } else {
            stringLength++;
        }
        return c;
    }*/

    //reserving keywords in the hashtable
    private void reserve(Word t) {
        ids.put(t.lexeme, t);
    }

    //reserving keywords to put in the hashtable
    public void Lex() {
        reserve(new Word("PROGRAM", Tag.PROGRAM));
        reserve(new Word("BEGIN", Tag.BEGIN));
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
        reserve(new Word("IDENTIFIER", Tag.IDENTIFIER));
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

    private Boolean isLetter(char c) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int i;
        for (i = 0; i < alphabet.length(); i++) {
            if (c == alphabet.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isNumber(char c) {
        String numbers = "1234567890";
        int i;
        for (i = 0; i < numbers.length(); i++) {
            char a = numbers.charAt(i);
            if (c == a) {
                return true;
            }
        }
        return false;
    }

    private Boolean isSymbol(char c) {
        String symbols = ".,;:<>/*[]+-=()}{\t ";
        int i;
        for (i = 0; i < symbols.length(); i++) {
            char a = symbols.charAt(i);
            if (c == a) {
                return true;
            }
        }
        return false;
    }


    public boolean isWhiteSpace(char c) {
        if (Character.isWhitespace(c)) {
            return true;
        }
        return false;
    }

    public Token getNextToken() throws IOException {
        String str = Character.toString(currentChar);
        if (isValid(currentChar)) {
            if (!isWhiteSpace(currentChar)) {
                //{comment
                if (lastToken == Tag.RIGHTBRACKET) {

                    while (currentChar != '}' && (!str.isEmpty())) {
                        //if reached end of string
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (str.isEmpty()) {
                        setLastToken(Tag.ENDOFFILE);
                        LexicalError.BadComment(indexOfLineArray, currentChar);
                        return (new Token(lastToken, ""));
                    }
                    //must equal leftbracket
                    setLastToken(Tag.LEFTBRACKET);
                    if (stringLength == (cLine.length())) {
                        indexOfLineArray++;
                        cLine = currLine[indexOfLineArray];
                        stringLength = 0;
                    }
                    currentChar = charPlusOne;
                    charPlusOne = charPlusTwo;
                    charPlusTwo = getNextChar();
                    return (new Token(lastToken, ""));
                }
                switch (currentChar) {
                    case '=':
                        setLastToken(Tag.RELOP);
                       if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();

                        return (new Token(lastToken, "1"));
                    case '<':
                        //<>
                        if (charPlusOne == '>') {
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "2"));
                        }
                        //<=
                        if (charPlusOne == '=') {
                            setLastToken(Tag.RELOP);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "5"));
                        }
                        //<
                        setLastToken(Tag.RELOP);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "3"));

                    case '>':
                        //>=
                        if (charPlusOne == '=') {
                            setLastToken(Tag.RELOP);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "6"));
                        }
                        setLastToken(Tag.RELOP);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "4"));
                    case '+':
                        if (getLastToken() == Tag.RIGHTPAREN || getLastToken() == Tag.RIGHTBRACKET || getLastToken() == Tag.IDENTIFIER || getLastToken() == Tag.INTCONSTANT || getLastToken() == Tag.REALCONSTANT) {

                            setLastToken(Tag.ADDOP);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "1"));
                        } else {
                            setLastToken(Tag.UNARYPLUS);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }

                    case '-':
                        if (getLastToken() == Tag.RIGHTPAREN || getLastToken() == Tag.RIGHTBRACKET || getLastToken() == Tag.IDENTIFIER || getLastToken() == Tag.INTCONSTANT || getLastToken() == Tag.REALCONSTANT) {

                            setLastToken(Tag.ADDOP);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, "2"));
                        } else {
                            setLastToken(Tag.UNARYMINUS);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }

                    case '*':

                        setLastToken(Tag.MULOP);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "1"));

                    case '/':

                        setLastToken(Tag.MULOP);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, "2"));


                    case '{':
                        setLastToken(Tag.RIGHTBRACKET);
                        return (new Token(lastToken, ""));


                    case '}':

                        setLastToken(Tag.LEFTBRACKET);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case ',':

                        setLastToken(Tag.COMMA);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case ';':

                        setLastToken(Tag.SEMICOLON);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));

                    case ':':

                        setLastToken(Tag.COLON);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case '(':

                        setLastToken(Tag.RIGHTPAREN);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case ')':

                        setLastToken(Tag.LEFTPAREN);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                    case '.':
                        if (charPlusOne == '.') {
                            setLastToken(Tag.DOUBLEDOT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }
                        setLastToken(Tag.ENDMARKER);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, ""));
                }


                if (isLetter(currentChar)) {
                    Id = " ";
                    while (!isWhiteSpace(currentChar) && !isSymbol(currentChar) && Id.length() < 250) {
                        //either number or letter
                        if (isNumber(currentChar)) {
                            Id = Id + currentChar;
                            Id = Id.toUpperCase();
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            //current char is not a number
                        }
                        Id = Id + currentChar;
                        Id = Id.toUpperCase();
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                            //must have read a full id
                            if (ids.containsKey(Id)) {
                                if (Id == "OR") {
                                    setLastToken(Tag.ADDOP);
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, "3"));
                                }
                                if (Id == "DIV") {
                                    setLastToken(Tag.MULOP);
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, "3"));
                                }
                                if (Id == "MOD") {
                                    setLastToken(Tag.MULOP);
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, "4"));
                                }
                                if (Id == "AND") {
                                    setLastToken(Tag.MULOP);
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, "5"));
                                }
                                setLastToken(Tag.IDENTIFIER);
                                if (stringLength == (cLine.length())) {
                                    indexOfLineArray++;
                                    cLine = currLine[indexOfLineArray];
                                    stringLength = 0;
                                }
                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, Id));

                            }
                            if (Id.length() >= 250) {
                                LexicalError.TooLongID(indexOfLineArray, currentChar);
                                return (new Token(lastToken, ""));
                            } else {
                                reserve(new Word(Id, ids.size() + 1));

                                setLastToken(Tag.IDENTIFIER);
                                if (stringLength == (cLine.length())) {
                                    indexOfLineArray++;
                                    cLine = currLine[indexOfLineArray];
                                    stringLength = 0;
                                }
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
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }
                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }
                                setLastToken(Tag.INTCONSTANT);
                                if (stringLength == (cLine.length())) {
                                    indexOfLineArray++;
                                    cLine = currLine[indexOfLineArray];
                                    stringLength = 0;
                                }

                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));

                            }
                            //currchar = e
                            if (isLetter(charPlusOne)) {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }

                                    currentChar = charPlusOne;
                                    charPlusOne = charPlusTwo;
                                    charPlusTwo = getNextChar();
                                    return (new Token(lastToken, num));
                                }
                                setLastToken(Tag.INTCONSTANT);
                                if (stringLength == (cLine.length())) {
                                    indexOfLineArray++;
                                    cLine = currLine[indexOfLineArray];
                                    stringLength = 0;
                                }

                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));


                            }
                            if (countE > 1) {
                                if (countPoint == 1) {

                                    setLastToken(Tag.REALCONSTANT);
                                    if (stringLength == (cLine.length())) {
                                        indexOfLineArray++;
                                        cLine = currLine[indexOfLineArray];
                                        stringLength = 0;
                                    }
                                }

                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));
                            }

                            setLastToken(Tag.INTCONSTANT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }
                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        countE++;
                        num = num + currentChar;
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    if (isSymbol(currentChar)) {
                        if (currentChar != '.') {
                            if (countPoint == 1) {

                                setLastToken(Tag.REALCONSTANT);
                                if (stringLength == (cLine.length())) {
                                    indexOfLineArray++;
                                    cLine = currLine[indexOfLineArray];
                                    stringLength = 0;
                                }

                                currentChar = charPlusOne;
                                charPlusOne = charPlusTwo;
                                charPlusTwo = getNextChar();
                                return (new Token(lastToken, num));

                            }

                            setLastToken(Tag.INTCONSTANT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }

                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        //so currChar=.
                        if (countPoint == 1) {

                            setLastToken(Tag.REALCONSTANT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }

                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }
                        //not already a dot
                        if (charPlusOne == '.') {

                            setLastToken(Tag.DOUBLEDOT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }

                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, ""));
                        }
                        //so char is a dot but just normal
                        num = num + currentChar;
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }

                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                    }
                    //current char is a number
                    if (charPlusOne == '.' && !isNumber(charPlusTwo)) {
                        num = num + currentChar;
                        if (countPoint == 1) {

                            setLastToken(Tag.REALCONSTANT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }

                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }

                        setLastToken(Tag.INTCONSTANT);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }

                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    }
                    if ((charPlusOne == 'e' || charPlusOne == 'E') && !isNumber(charPlusTwo)) {
                        num = num + currentChar;
                        if (countPoint == 1) {

                            setLastToken(Tag.REALCONSTANT);
                            if (stringLength == (cLine.length())) {
                                indexOfLineArray++;
                                cLine = currLine[indexOfLineArray];
                                stringLength = 0;
                            }

                            currentChar = charPlusOne;
                            charPlusOne = charPlusTwo;
                            charPlusTwo = getNextChar();
                            return (new Token(lastToken, num));
                        }

                        setLastToken(Tag.INTCONSTANT);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    }
                    num = num + currentChar;
                    if (stringLength == (cLine.length())) {
                        indexOfLineArray++;
                        cLine = currLine[indexOfLineArray];
                        stringLength = 0;
                    }
                    currentChar = charPlusOne;
                    charPlusOne = charPlusTwo;
                    charPlusTwo = getNextChar();

                    if (num.length() >= 250) {
                        LexicalError.TooLongID(indexOfLineArray, currentChar);
                        //
                        return (new Token(lastToken, ""));
                    }
                    if (countPoint == 0) {

                        setLastToken(Tag.INTCONSTANT);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));
                    } else {

                        setLastToken(Tag.INTCONSTANT);
                        if (stringLength == (cLine.length())) {
                            indexOfLineArray++;
                            cLine = currLine[indexOfLineArray];
                            stringLength = 0;
                        }
                        currentChar = charPlusOne;
                        charPlusOne = charPlusTwo;
                        charPlusTwo = getNextChar();
                        return (new Token(lastToken, num));

                    }


                }
                if (stringLength == (cLine.length())) {
                    indexOfLineArray++;
                    cLine = currLine[indexOfLineArray];
                    stringLength = 0;
                }
                    currentChar = charPlusOne;
                    charPlusOne = charPlusTwo;
                    charPlusTwo = getNextChar();
                }
                    /*
            if(str.isEmpty()){
                setLastToken(Tag.ENDOFFILE);
                return(new Token(lastToken, ""));
            }
            if(currentChar =='\n') {
                indexOfLineArray++;
                cLine = currLine[indexOfLineArray];
                stringLength = 0;
                currentChar = charPlusOne;
                charPlusOne = getNextChar();
                charPlusTwo = getNextChar();

            } else{
                if (stringLength == (cLine.length())) {
                    indexOfLineArray++;
                    cLine = currLine[indexOfLineArray];
                    stringLength = 0;
                }
                currentChar=charPlusOne;
                charPlusOne=charPlusTwo;
                charPlusTwo=getNextChar();
            }*/
            if (stringLength == (cLine.length())) {
                indexOfLineArray++;
                cLine = currLine[indexOfLineArray];
                stringLength = 0;
            }currentChar = charPlusOne;
            charPlusOne = charPlusTwo;
            charPlusTwo = getNextChar();
                return (new Token(lastToken, ""));

        }
            //  indexOfLineArray++;
            // cLine = currLine[indexOfLineArray];
            //stringLength = 0;
            //currentChar = charPlusOne;
            //charPlusOne = getNextChar();
            //charPlusTwo = getNextChar();
            LexicalError.InvalidInput(indexOfLineArray, currentChar);
            return (new Token(lastToken, ""));



    }


    public void setLastToken(int tag) {

        lastToken = tag;
    }

    public int getLastToken() {

        return lastToken;
    }
}
