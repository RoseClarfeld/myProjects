package lexer;
import java.io.*;
import java.util.Scanner;

/**
 * @author Rose Clarfeld
 */

public class LexicalAnalyzer {

    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {
        //intialize
        lexer lexa = new lexer();
        do {
            try {
                lexa.Lscan();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (lexa.toke.tag!= Tag.ENDOFFILE);
    }
}
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
