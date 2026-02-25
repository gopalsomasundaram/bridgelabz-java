/**
 * This program is a demonstration of the Strategy design pattern.
 * It allows switching between different payment methods at runtime.
 */

package ioprogramming.designpatterns;

interface PaymentStrategy { void pay(int amount); }

class CashPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " using Cash."); }
}

class CardPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " using Card."); }
}

class PaymentContext {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void executePayment(int amount) { strategy.pay(amount); }
}

public class StrategyDemonstration {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setStrategy(new CardPayment());
        context.executePayment(100);

        context.setStrategy(new CashPayment());
        context.executePayment(50);
    }
}