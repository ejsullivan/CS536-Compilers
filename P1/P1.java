// Programing Assignment 1
// UW-Madison CS 536 Programming Languages and Compilers
// Fall 2016
// Eric Sullivan

public class P1 {

    public static void main(String[] args) {
        TestEmptySymTableException();
        TestDuplicateSymException();
        TestNullPointerException();
        SymTableTest();
        TestSym();
    }

    public static void TestSym() {
        Sym testSym = new Sym("test");
        if (!testSym.getType().equals("test")) {
            System.out.println("TestSym: FAIL (type did not match passed string");
        }
        System.out.println("TestSym: PASS");
    }

    public static void TestEmptySymTableException() {
        SymTable symTable = new SymTable();
        Sym symbol = new Sym("TestEmptySymTableException");
        try {
            symTable.removeScope();
            symTable.addDecl("testSym", symbol);
        }
        catch (EmptySymTableException e) {
            System.out.println("TestEmptySymTableException: PASS");
            return;
        }
        catch (DuplicateSymException e) {
            System.out.println("TestEmptySymTableException: FAIL (DuplicateSymException Raised)");
            return;
        }
        System.out.println("TestEmptySymTableException: FAIL (EmptySymTableException Not Raised)");
    }

    public static void TestDuplicateSymException() {
        SymTable symTable = new SymTable();
        Sym symbol = new Sym("TestDuplicateSymException");
        Sym duplicateSym = new Sym("TestDuplicateSymException");
        try {
            symTable.addDecl("test", symbol);
            symTable.addDecl("test", duplicateSym);
        }
        catch (EmptySymTableException e) {
            System.out.println("TestEmptySymTableException: FAIL (EmptySymTableException Raised)");
            return;
        }
        catch (DuplicateSymException e) {
            System.out.println("TestEmptySymTableException: PASS");
            return;
        }
        System.out.println("TestEmptySymTableException: FAIL (EmptySymTableException Not Raised)");
    }

    public static void TestNullPointerException() {
        SymTable symTable = new SymTable();
        Sym symbol = new Sym("TestDuplicateSymException");
        Sym duplicateSym = new Sym("TestDuplicateSymException");
        try {
            symTable.addDecl(null, null);
        }
        catch (NullPointerException e) {
            System.out.println("TestNullPointerException: PASS");
            return;
        }
        catch (EmptySymTableException e) {
            System.out.println("TestEmptySymTableException: FAIL (EmptySymTableException Raised)");
            return;
        }
        catch (DuplicateSymException e) {
            System.out.println("TestEmptySymTableException: FAIL (DuplicateSymException Raised");
            return;
        }
        System.out.println("TestNullPointerException: FAIL");
    }

    public static void SymTableTest() {
        SymTable symTable = new SymTable();
        try {

            // Add some symbols to the current scope
            for (int i = 0; i < 10; i++) {
                symTable.addDecl(Integer.toString(i), new Sym("test"));
            }

            // Make sure the syms are now in local/global scope
            for (int i = 0; i < 10; i++) {
                if (null == symTable.lookupLocal(Integer.toString(i)) ||
                        null == symTable.lookupLocal(Integer.toString(i))) {
                    System.out.println("SymTableTest: FAIL (Could not find symbol in local scope)");
                    return;
                }
            }

            System.out.println("SymTableTest (Add symbols to scope): PASS");

            // Check that if the key is not in the table null is returned
            if (null != symTable.lookupLocal("THISSHOULDNOTEXIST")) {
                System.out.println("SymTableTest (Symbol should not exist): FAIL");
                return;
            }

            System.out.println("SymTableTest (Symbol should not exist): PASS");

            // Add a new local scope
            symTable.addScope();

            // Make sure the old syms are not in local scope
            for (int i = 0; i < 10; i++) {
                if (null != symTable.lookupLocal(Integer.toString(i))) {
                    System.out.println("SymTableTest: FAIL (Found global symbol in local scope)");
                    return;
                }
            }

            // Look through the global scope for symbols
            for (int i = 0; i < 10; i++) {
                if (null == symTable.lookupGlobal(Integer.toString(i))) {
                    System.out.println("SymTableTest: FAIL (Could not find global symbol in global scope)");
                    return;
                }
            }

            // Add some symbols to the 2nd scope of the table
            for (int i = 10; i < 20; i++) {
                symTable.addDecl(Integer.toString(i), new Sym("test"));
            }

            // Check that these symbols exist in both the local and global scope
            for (int i = 10; i < 20; i++) {
                if (null == symTable.lookupLocal(Integer.toString(i)) ||
                        null == symTable.lookupLocal(Integer.toString(i))) {
                    System.out.println("SymTableTest: FAIL (Could not find symbol in local scope)");
                    return;
                }
            }

            System.out.println("SymTableTest (Check local/global scope separation): PASS");

            // Remove the current local scope
            symTable.removeScope();

            // Check that these symbols do not exist in both the local and global scope
            for (int i = 10; i < 20; i++) {
                if (null != symTable.lookupLocal(Integer.toString(i)) ||
                        null != symTable.lookupLocal(Integer.toString(i))) {
                    System.out.println("SymTableTest: FAIL (Found removed symbol in local or global scope)");
                    return;
                }
            }

            // Check that the only symbols left in the global and local scope are the original ones
            for (int i = 0; i < 10; i++) {
                if (null == symTable.lookupLocal(Integer.toString(i)) ||
                        null == symTable.lookupLocal(Integer.toString(i))) {
                    System.out.println("SymTableTest: FAIL (Could not find symbol in local scope)");
                    return;
                }
            }

            System.out.println("SymTableTest (Remove local scope): PASS");

        }
        catch (NullPointerException e) {
            System.out.println("SymTableTest: FAIL (NullPointerException Thrown)");
            return;
        }
        catch (DuplicateSymException e) {
            System.out.println("SymTableTest: FAIL (DuplicateSymException Thrown)");
            return;
        }
        catch (EmptySymTableException e) {
            System.out.println("SymTableTest: FAIL (EmptySymTableException Thrown)");
            return;
        }

        System.out.println("SymTableTest: PASS");

    }

    public static void TestSymTablePrint() {
        SymTable symTable = new SymTable();
        Sym symbol = new Sym("TestDuplicateSymException");
        Sym duplicateSym = new Sym("TestDuplicateSymException");
    }

}
