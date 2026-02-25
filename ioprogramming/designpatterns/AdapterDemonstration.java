/**
 * This program is a demonstration of the Adapter design pattern.
 * It allows the OldPrinter's specific interface to be used as a modern NewPrinter.
 */

package ioprogramming.designpatterns;

class OldPrinter {
    void oldPrint() {
        System.out.println("Printing using the legacy system...");
    }
}

interface NewPrinter {
    void print();
}

class PrinterAdapter implements NewPrinter {
    private OldPrinter oldPrinter;

    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print() {
        // Mapping the new method to the old functionality
        oldPrinter.oldPrint();
    }
}

public class AdapterDemonstration {
    public static void main(String[] args) {
        OldPrinter legacyPrinter = new OldPrinter();
        NewPrinter adapter = new PrinterAdapter(legacyPrinter);

        System.out.println("Client calling print() on Adapter:");
        adapter.print();
    }
}