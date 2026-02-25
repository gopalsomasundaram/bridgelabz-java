/**
 * This program is a demonstration of the Visitor design pattern.
 * It uses a PriceCalculatorVisitor to calculate totals for different Item types.
 */

package ioprogramming.designpatterns;

interface Item { void accept(Visitor v); }

class Book implements Item {
    public int getPrice() { return 20; }
    public void accept(Visitor v) { v.visit(this); }
}

class Electronics implements Item {
    public int getPrice() { return 200; }
    public void accept(Visitor v) { v.visit(this); }
}

interface Visitor {
    void visit(Book b);
    void visit(Electronics e);
}

class PriceCalculatorVisitor implements Visitor {
    private int total = 0;
    public void visit(Book b) { total += b.getPrice(); }
    public void visit(Electronics e) { total += e.getPrice(); }
    public int getTotal() { return total; }
}

public class VisitorDemonstration {
    public static void main(String[] args) {
        Item[] items = {new Book(), new Electronics(), new Book()};
        PriceCalculatorVisitor calculator = new PriceCalculatorVisitor();

        for (Item item : items) {
            item.accept(calculator);
        }

        System.out.println("Total Cart Value: $" + calculator.getTotal());
    }
}