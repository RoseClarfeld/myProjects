/**
 * @author Rose Clarfeld
 */
package lexer;


public class LexicalError extends Exception {
    public static int lineNum = 0;
    public static char lastCharRead;


    public static void FileError() {
        System.out.println("Error: \nInvalid file input, please input a valid txt file");
        Runtime.getRuntime().halt(0);
    }

    public static void BadComment(int line, char c) {
        lineNum = line;
        lastCharRead = c;
        System.out.println("Error: \nThere was an open comment within the file, of the format {txt... \n Please close the comment so that it has the format {txt...txt}");
        System.out.println("This Error occured at line " + lineNum + ". The last character read was " + lastCharRead + ".");
        Runtime.getRuntime().halt(0);

    }

    public static void TooLongID(int line, char c) {
        lineNum = line;
        lastCharRead = c;
        System.out.println("Error: \n Inside the input file there exists an Identifier which is too long to be identified. \n Please only input files with identifiers that are less than 250 characters.");
        System.out.println("This Error occured at line " + lineNum + ". The last character read was " + lastCharRead + ".");
        Runtime.getRuntime().halt(0);
    }

    public static void InvalidInput(int line, char c) {
        lineNum = line;
        lastCharRead = c;
        System.out.println("Error: \n Inside the input file there exists an invalid character. \n Please only input files with the following characters:");
        System.out.println("\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\" +\n" +
                "                    \".,;:<>/*[]+-=()}{\\t \"");
        System.out.println("This Error occured at line " + lineNum + ". The last character read was " + lastCharRead + ".");
        Runtime.getRuntime().halt(0);


    }
}
