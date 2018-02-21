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
    public static InputStream is;
    public static Token token;
    public static void main(String[] args) throws IOException {
        //intialize
        File file = new File("textfile.txt");
        Tokenizer tokenizer = new Tokenizer(file);
        while (tokenizer.getLastToken() != 291) {
            token = tokenizer.getNextToken();
          String result = tokenToString(token);
            System.out.println(result);
        }




       /* do {
            try {
                lexa.Lscan();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (lexa.toke.tag!= Tag.ENDOFFILE);*/
    }
}

/*public static void readFile(File file) throws IOException{
    try{
        is = new FileInputStream(file);
        Reader reader = new InputStreamReader(is);
        Reader buf = new BufferedReader(reader);
        readChar(buf);
    }

    }
    public static void readChar(Reader reader)throws IOException{
        int read;
        while((read=reader.read())!= -1){
            char c =(char)read;
            lscan(c);
        }
    }
}*/
/*
skipping white space
for ( ; ; peek = next input character ) {
if ( peek is a blank or a tab ) do nothing;
else if ( peek is a newline ) line = line+1;
else break;
}

if ( peek holds a digit ) {
v = 0;
do {
v = v * 10 + integer value of digit peek;
peek = next input character;
} whil e ( peek holds a digit );
return token (num, v);
}

Token scanQ {
skip white space, as in Section 2.6.1;
handle numbers, as in Section 2.6.3;
handle reserved words and identifiers, as in Section 2.6.4;
/* if we get here, treat read-ahead character peek as a token
Token t = ne w Token(peek);
peek = blank /* initialization, as discussed in Section 2.6.2  ;
        retur n i;
        }
        */
