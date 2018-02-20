package lexer;
public class Tag {
    public static final int
    PROGRAM = 256,
    BEGIN = 257,
    END = 258,
    VAR = 259,
    FUNCTION = 260,
    PROCEDURE = 261,
    RESULT = 262,

    INTEGER = 263,
    REAL = 264,
    ARRAY = 265,

    OF = 266,
    IF = 267,
    THEN = 268,
    ELSE = 269,
    WHILE = 270,
    DO = 271,

    NOT = 272,

    IDENTIFIER = 273,
    INTCONSTANT = 274,
    REALCONSTANT = 275,

    RELOP =276,
    MULOP = 277,
    ADDOP = 278,
    ASSIGNOP = 279,

    COMMA = 280,
    SEMICOLON = 281,
    COLON = 282,
    RIGHTPAREN = 283,
    LEFTPAREN = 284,
    RIGHTBRACKET = 285,
    LEFTBRACKET =286,
    UNARYMINUS =287,
    UNARYPLUS = 288,
    DOUBLEDOT = 289,
    ENDMARKER = 290,
    ENDOFFILE = 291,
    SKIP = 292,
    COMMENT = 293;
}
