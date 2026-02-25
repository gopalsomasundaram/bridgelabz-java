/**
 * This program is a demonstration of the Template Method design pattern.
 * It defines a fixed order process while letting subclasses define specific steps.
 */

package ioprogramming.designpatterns;

abstract class OrderProcess {
    public final void processOrder() { // Template Method
        selectItem();
        makePayment();
        deliver();
    }
    abstract void selectItem();
    abstract void makePayment();
    void deliver() { System.out.println("Item delivered to address."); }
}

class OnlineOrder extends OrderProcess {
    void selectItem() { System.out.println("Item added to digital cart."); }
    void makePayment() { System.out.println("Payment via Online Gateway."); }
}

class InStoreOrder extends OrderProcess {
    void selectItem() { System.out.println("Item picked from shelf."); }
    void makePayment() { System.out.println("Payment at counter."); }
}

public class TemplateDemonstration {
    public static void main(String[] args) {
        OrderProcess online = new OnlineOrder();
        online.processOrder();

        System.out.println("---");

        OrderProcess store = new InStoreOrder();
        store.processOrder();
    }
}
