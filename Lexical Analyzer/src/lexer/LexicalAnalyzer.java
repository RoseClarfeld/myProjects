package lexer;

import java.io.*;

/**
 * @author Rose Clarfeld
 */

public class LexicalAnalyzer {


    public static String tokenToString(Token token){
        String output = token.tokenType + "     "+ token.value;
        return output;
    }
   // public static InputStream is;
    public static Token token = new Token(0," ");
    public static void main(String[] args) throws IOException {
        //intialize
        File file = new File("textfile.txt");
        Tokenizer tokenizer = new Tokenizer(file);
        while (tokenizer.getLastToken() != 291) {
            token = tokenizer.getNextToken();
            String result = tokenToString(token);
            System.out.println(result);
        }
        if(tokenizer.getLastToken()==291){
            token=(new Token(Tag.ENDOFFILE, ""));
            String result = tokenToString(token);
            System.out.println(result);
        }
        Runtime.getRuntime().halt(0);

    }
}
