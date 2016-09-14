/**
 * Created by esullivan on 9/6/16.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class SymTable {

    private LinkedList<HashMap<String, Sym>> table;

    /*
     * CONSTRUCTOR: Creates the symbol table and adds the first scope
     */
    public SymTable() {
        table = new LinkedList<HashMap<String, Sym>>();
        table.addFirst(new HashMap<String, Sym>());
    }

    /*
     * addDecl adds a new Sym to the current scope
     */
    public void addDecl(String name, Sym sym) throws EmptySymTableException, DuplicateSymException, NullPointerException {

        if (name == null || sym == null)
            throw new NullPointerException();

        if (table.isEmpty())
            throw new EmptySymTableException();

        if (table.get(0).containsKey(name))
            throw new DuplicateSymException();

        table.get(0).put(name, sym);

    }

    /*
     * Pushes a new scope on to the symbol table
     */
    public void addScope() {
        table.addFirst(new HashMap<String, Sym>());
    }

    public Sym lookupLocal(String name) throws EmptySymTableException {

        if (table.isEmpty())
            throw new EmptySymTableException();

        if (name == null)
            return null;

        return table.getFirst().get(name);

    }

    /*
     * Checks to see if a symbol exists with the given name
     */
    public Sym lookupGlobal(String name) throws EmptySymTableException {

        ListIterator<HashMap<String, Sym>> itr = table.listIterator(0);
        HashMap<String, Sym> curr;

        if (table.isEmpty())
            throw new EmptySymTableException();

        if (name == null)
            return null;

        while (itr.hasNext()) {
            curr = itr.next();
            if (curr.containsKey(name))
                    return curr.get(name);
        }

        return null;

    }

    /*
     * Pops the current scope off of the symbol table
     */
    public void removeScope() throws EmptySymTableException {

        if (table.isEmpty())
            throw new EmptySymTableException();

        table.removeFirst();

    }

    /*
     * DEBUG ONLY: Prints out the all of the symbols for each scope
     * in the symbol table
     */
    public void print() {

        ListIterator<HashMap<String, Sym>> itr = table.listIterator(0);

        System.out.print("\nSym Table\n");

        while (itr.hasNext()) {
            System.out.println(itr.next().toString());
        }

	System.out.println("");

    }

}
