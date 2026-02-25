/**
 * This program is a demonstration of the Iterator design pattern.
 * It allows sequential traversal of a custom collection of names.
 */

package ioprogramming.designpatterns;

interface Iterator {
    boolean hasNext();
    Object next();
}

class NameRepository {
    public String names[] = {"Alice", "Bob", "Charlie", "Diana"};

    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {
        int index;
        public boolean hasNext() { return index < names.length; }
        public Object next() { return hasNext() ? names[index++] : null; }
    }
}

public class IteratorDemonstration {
    public static void main(String[] args) {
        NameRepository repo = new NameRepository();
        for (Iterator iter = repo.getIterator(); iter.hasNext();) {
            System.out.println("Name: " + iter.next());
        }
    }
}
