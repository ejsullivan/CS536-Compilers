import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        testAllTokens();
        CharNum.num = 1;
    
        // ADD CALLS TO OTHER TEST METHODS HERE
        testPLUSPLUS();
        testDIVIDE();
        testEQUALS();
        testGREATER();
        testLPAREN();
        testINT();
        testLESS();
        testMINUS();
        testWRITE();
        testRPAREN();
        testNOT();
        testSEMICOLON();
        testAND();
        testOR();
        testBOOL();
        testCOMMA();
        testPLUS();
        testASSIGN();
        testIF();
        testDOT();
        testID();
        testMINUSMINUS();
        testRETURN();
        testTRUE();
        testLCURLY();
        testCIN();
        testVOID();
        testRCURLY();
        testINTLITERAL();
        testTIMES();
        testELSE();
        testCOUT();
        testREAD();
        testNOTEQUALS();
        testWHILE();
        testGREATEREQ();
        testSTRINGLITERAL();
        testFALSE();
        testSTRUCT();
        testLineNumbers();
        testCharNumber();
        testBADINTLIT();
        testUNTERMINATED();
        testBADESCAPE();
        testUNTERMINATEDBADESCAPE();
        testBADSTRING();
        testCOMMENT();
    }

    private static void testLineNumbers() throws IOException {
        // open input and output files
        FileReader inFile = null;
        try {
            inFile = new FileReader("files/tests/LINENUMBERS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        for (int i = 0; i < 5; i ++) {
            if (token.sym != sym.INTLITERAL || ((TokenVal)(token.value)).linenum != i + 1) {
                System.out.println("FAIL: Line Number Test");
                break;
            }
            token = scanner.next_token();
            if (token.sym != sym.SEMICOLON || ((TokenVal)(token.value)).linenum != i + 1) {
                System.out.println("FAIL: Line Number Test");
                break;
            }
            token = scanner.next_token();
        }
        System.out.println("PASS: Line Number Test");
    }

    private static void testCharNumber() throws IOException {
        // open input and output files
        FileReader inFile = null;
        int i;

        try {
            inFile = new FileReader("files/tests/CHARNUM.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        CharNum.num = 1;

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        for (i = 1; i <= 5; i++) {
            if (token.sym != sym.INTLITERAL || ((TokenVal) (token.value)).charnum != (i-1)*3 + (i-1) + 1) {
                System.out.println("CHAR NUM = " + ((TokenVal) (token.value)).charnum);
                System.out.println("FAIL Char Number Test");
                return;
            }
            token = scanner.next_token();
            if (token.sym != sym.SEMICOLON || ((TokenVal) (token.value)).charnum != i*3 + i) {
                System.out.println("CHAR NUM = " + ((TokenVal) (token.value)).charnum);
                System.out.println("FAIL Char Number Test");
                return;
            }
            token = scanner.next_token();
        }

        System.out.println("PASS: Char Number Test");
    }

    private static void testBADINTLIT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/BADINTLIT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (((IntLitTokenVal)(token.value)).intVal != Integer.MAX_VALUE) {
            System.out.println("FAIL: testBADINTLIT");
            return;
        }
        System.out.println("PASS: testBADINTLIT");
    }

    private static void testUNTERMINATED() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/UNTERMINATED.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.EOF) {
            System.out.println("FAIL: testUNTERMINATED");
            return;
        }
        System.out.println("PASS: testUNTERMINATED");
    }

    private static void testBADSTRING() throws IOException {
        // open input and output files
        FileReader inFile = null;
        try {
            inFile = new FileReader("files/tests/BADSTRING.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.EOF) {
            System.out.println("FAIL: testBADSTRING");
            return;
        }
        System.out.println("PASS: testBADSTRING");
    }

    private static void testBADESCAPE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/BADESCAPE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.EOF) {
            System.out.println("FAIL: testBADESCAPE");
            return;
        }
        System.out.println("PASS: testBADESCAPE");
    }

    private static void testUNTERMINATEDBADESCAPE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/UNTERMINATEDBADESCAPE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.EOF) {
            System.out.println("FAIL: testUNTERMINATEDBADESCAPE");
            return;
        }
        System.out.println("PASS: testUNTERMINATEDBADESCAPE");
    }

    private static void testCOMMENT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/COMMENT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.EOF) {
            System.out.println("FAIL: testCOMMENT");
            return;
        }
        System.out.println("PASS: testCOMMENT");
    }

    private static void testPLUSPLUS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/PLUSPLUS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        if (token.sym != sym.PLUSPLUS) {
            System.out.println("FAIL: PLUSPLUS test");
        }
        else {
            System.out.println("PASS: PLUSPLUS test");
        }
    }
    private static void testDIVIDE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/DIVIDE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.DIVIDE) {
            System.out.println("FAIL: DIVIDE test");
        }
        else {
            System.out.println("PASS: DIVIDE test");
        }
    }
    private static void testEQUALS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/EQUALS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.EQUALS) {
            System.out.println("FAIL: EQUALS test");
        }
        else {
            System.out.println("PASS: EQUALS test");
        }
    }
    private static void testGREATER() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/GREATER.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.GREATER) {
            System.out.println("FAIL: GREATER test");
        }
        else {
            System.out.println("PASS: GREATER test");
        }
    }
    private static void testLPAREN() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/LPAREN.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.LPAREN) {
            System.out.println("FAIL: LPAREN test");
        }
        else {
            System.out.println("PASS: LPAREN test");
        }
    }
    private static void testINT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/INT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.INT) {
            System.out.println("FAIL: INT test");
        }
        else {
            System.out.println("PASS: INT test");
        }
    }
    private static void testLESS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/LESS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.LESS) {
            System.out.println("FAIL: LESS test");
        }
        else {
            System.out.println("PASS: LESS test");
        }
    }
    private static void testMINUS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/MINUS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.MINUS) {
            System.out.println("FAIL: MINUS test");
        }
        else {
            System.out.println("PASS: MINUS test");
        }
    }
    private static void testWRITE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/WRITE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.WRITE) {
            System.out.println("FAIL: WRITE test");
        }
        else {
            System.out.println("PASS: WRITE test");
        }
    }
    private static void testRPAREN() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/RPAREN.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.RPAREN) {
            System.out.println("FAIL: RPAREN test");
        }
        else {
            System.out.println("PASS: RPAREN test");
        }
    }
    private static void testNOT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/NOT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.NOT) {
            System.out.println("FAIL: NOT test");
        }
        else {
            System.out.println("PASS: NOT test");
        }
    }
    private static void testSEMICOLON() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/SEMICOLON.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.SEMICOLON) {
            System.out.println("FAIL: SEMICOLON test");
        }
        else {
            System.out.println("PASS: SEMICOLON test");
        }
    }
    private static void testAND() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/AND.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.AND) {
            System.out.println("FAIL: AND test");
        }
        else {
            System.out.println("PASS: AND test");
        }
    }
    private static void testOR() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/OR.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.OR) {
            System.out.println("FAIL: OR test");
        }
        else {
            System.out.println("PASS: OR test");
        }
    }
    private static void testBOOL() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/BOOL.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.BOOL) {
            System.out.println("FAIL: BOOL test");
        }
        else {
            System.out.println("PASS: BOOL test");
        }
    }
    private static void testCOMMA() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/COMMA.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.COMMA) {
            System.out.println("FAIL: COMMA test");
        }
        else {
            System.out.println("PASS: COMMA test");
        }
    }
    private static void testPLUS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/PLUS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.PLUS) {
            System.out.println("FAIL: PLUS test");
        }
        else {
            System.out.println("PASS: PLUS test");
        }
    }
    private static void testASSIGN() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/ASSIGN.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.ASSIGN) {
            System.out.println("FAIL: ASSIGN test");
        }
        else {
            System.out.println("PASS: ASSIGN test");
        }
    }
    private static void LESSEQ() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/LESSEQ.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.LESSEQ) {
            System.out.println("FAIL: LESSEQ test");
        }
        else {
            System.out.println("PASS: LESSEQ test");
        }
    }
    private static void testIF() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/IF.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.IF) {
            System.out.println("FAIL: IF test");
        }
        else {
            System.out.println("PASS: IF test");
        }
    }
    private static void testDOT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/DOT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.DOT) {
            System.out.println("FAIL: DOT test");
        }
        else {
            System.out.println("PASS: DOT test");
        }
    }
    private static void testID() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/ID.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        if (token.sym != sym.ID) {
            System.out.println("FAIL: ID test");
        }
        else {
            System.out.println("PASS: ID test");
        }

        token = scanner.next_token();
        if (token.sym != sym.ID) {
            System.out.println("FAIL: ID test");
        }
        else {
            System.out.println("PASS: ID test");
        }
    }
    private static void testMINUSMINUS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/MINUSMINUS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.MINUSMINUS) {
            System.out.println("FAIL: MINUSMINUS test");
        }
        else {
            System.out.println("PASS: MINUSMINUS test");
        }
    }
    private static void testRETURN() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/RETURN.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.RETURN) {
            System.out.println("FAIL: RETURN test");
        }
        else {
            System.out.println("PASS: RETURN test");
        }
    }
    private static void testTRUE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/TRUE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.TRUE) {
            System.out.println("FAIL: TRUE test");
        }
        else {
            System.out.println("PASS: TRUE test");
        }
    }
    private static void testLCURLY() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/LCURLY.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.LCURLY) {
            System.out.println("FAIL: LCURLY test");
        }
        else {
            System.out.println("PASS: LCURLY test");
        }
    }
    private static void testCIN() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/CIN.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.CIN) {
            System.out.println("FAIL: CIN test");
        }
        else {
            System.out.println("PASS: CIN test");
        }
    }
    private static void testVOID() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/VOID.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.VOID) {
            System.out.println("FAIL: VOID test");
        }
        else {
            System.out.println("PASS: VOID test");
        }
    }
    private static void testRCURLY() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/RCURLY.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.RCURLY) {
            System.out.println("FAIL: RCURLY test");
        }
        else {
            System.out.println("PASS: RCURLY test");
        }
    }
    private static void testINTLITERAL() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/INTLITERAL.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        for (int i = 0; i < 5; i++) {
            if (token.sym != sym.INTLITERAL) {
                System.out.println("FAIL: INTLITERAL test");
            }
            else {
                System.out.println("PASS: INTLITERAL test");
            }
            token = scanner.next_token();
        }
    }
    private static void testTIMES() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/TIMES.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.TIMES) {
            System.out.println("FAIL: TIMES test");
        }
        else {
            System.out.println("PASS: TIMES test");
        }
    }
    private static void testELSE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/ELSE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.ELSE) {
            System.out.println("FAIL: ELSE test");
        }
        else {
            System.out.println("PASS: ELSE test");
        }
    }
    private static void testCOUT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/COUT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.COUT) {
            System.out.println("FAIL: COUT test");
        }
        else {
            System.out.println("PASS: COUT test");
        }
    }
    private static void testREAD() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/READ.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.READ) {
            System.out.println("FAIL: READ test");
        }
        else {
            System.out.println("PASS: READ test");
        }
    }
    private static void testNOTEQUALS() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/NOTEQUALS.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File NOTEQUALS.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.NOTEQUALS) {
            System.out.println("FAIL: NOTEQUALS test");
        }
        else {
            System.out.println("PASS: NOTEQUALS test");
        }
    }
    private static void testWHILE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/WHILE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File WHILE.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.WHILE) {
            System.out.println("FAIL: WHILE test");
        }
        else {
            System.out.println("PASS: WHILE test");
        }
    }
    private static void testGREATEREQ() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/GREATEREQ.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File GREATEREQ.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.GREATEREQ) {
            System.out.println("FAIL: GREATEREQ test");
        }
        else {
            System.out.println("PASS: GREATEREQ test");
        }
    }
    private static void testSTRINGLITERAL() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/STRINGLITERAL.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File STRINGLITERAL.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        for (int i = 0; i < 5; i++) {
            if (token.sym != sym.STRINGLITERAL) {
                System.out.println("FAIL: STRINGLITERAL test");
            } else {
                System.out.println("PASS: STRINLITERAL test");
            }
            token = scanner.next_token();
        }
    }
    private static void testFALSE() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/FALSE.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File FALSE.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.FALSE) {
            System.out.println("FAIL: FALSE test");
        }
        else {
            System.out.println("PASS: FALSE test");
        }
    }
    private static void testSTRUCT() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/tests/STRUCT.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File STRUCT.in not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        if (token.sym != sym.STRUCT) {
            System.out.println("FAIL: STRUCT test");
        }
        else {
            System.out.println("PASS: STRUCT test");
        }
    }
    
    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("files/allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                System.out.println("BOOL");
                outFile.println("bool"); 
                break;
			case sym.INT:
                System.out.println("INT");
                outFile.println("int");
                break;
            case sym.VOID:
                System.out.println("VOID");
                outFile.println("void");
                break;
            case sym.TRUE:
                System.out.println("TRUE");
                outFile.println("true"); 
                break;
            case sym.FALSE:
                System.out.println("FALSE");
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                System.out.println("STRUCT");
                outFile.println("struct"); 
                break;
            case sym.CIN:
                System.out.println("CIN");
                outFile.println("cin"); 
                break;
            case sym.COUT:
                System.out.println("COUT");
                outFile.println("cout");
                break;				
            case sym.IF:
                System.out.println("IF");
                outFile.println("if");
                break;
            case sym.ELSE:
                System.out.println("ELSE");
                outFile.println("else");
                break;
            case sym.WHILE:
                System.out.println("WHILE");
                outFile.println("while");
                break;
            case sym.RETURN:
                System.out.println("RETURN");
                outFile.println("return");
                break;
            case sym.ID:
                System.out.println("ID");
                outFile.println(((IdTokenVal)(token.value)).idVal);
                break;
            case sym.INTLITERAL:
                System.out.println("INTLITERAL");
                outFile.println(((IntLitTokenVal)(token.value)).intVal);
                break;
            case sym.STRINGLITERAL:
                System.out.println("STRINGLITERAL");
                outFile.println(((StrLitTokenVal)(token.value)).strVal);
                break;    
            case sym.LCURLY:
                System.out.println("LCURLY");
                outFile.println("{");
                break;
            case sym.RCURLY:
                System.out.println("RCURLY");
                outFile.println("}");
                break;
            case sym.LPAREN:
                System.out.println("LPAREN");
                outFile.println("(");
                break;
            case sym.RPAREN:
                System.out.println("RPAREN");
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                System.out.println("SEMICOLON");
                outFile.println(";");
                break;
            case sym.COMMA:
                System.out.println("COMMA");
                outFile.println(",");
                break;
            case sym.DOT:
                System.out.println("DOT");
                outFile.println(".");
                break;
            case sym.WRITE:
                System.out.println("WRITE");
                outFile.println("<<");
                break;
            case sym.READ:
                System.out.println("READ");
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                System.out.println("PLUSPLUS");
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                System.out.println("MINUSMINUS");
                outFile.println("--");
                break;	
            case sym.PLUS:
                System.out.println("PLUS");
                outFile.println("+");
                break;
            case sym.MINUS:
                System.out.println("MINUS");
                outFile.println("-");
                break;
            case sym.TIMES:
                System.out.println("TIMES");
                outFile.println("*");
                break;
            case sym.DIVIDE:
                System.out.println("DIVIDE");
                outFile.println("/");
                break;
            case sym.NOT:
                System.out.println("NOT");
                outFile.println("!");
                break;
            case sym.AND:
                System.out.println("AND");
                outFile.println("&&");
                break;
            case sym.OR:
                System.out.println("OR");
                outFile.println("||");
                break;
            case sym.EQUALS:
                System.out.println("EQUALS");
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                System.out.println("NOTEQUALS");
                outFile.println("!=");
                break;
            case sym.LESS:
                System.out.println("LESS");
                outFile.println("<");
                break;
            case sym.GREATER:
                System.out.println("GREATER");
                outFile.println(">");
                break;
            case sym.LESSEQ:
                System.out.println("LESSEQ");
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                System.out.println("GREATEREQ");
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                System.out.println("ASSIGN");
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
