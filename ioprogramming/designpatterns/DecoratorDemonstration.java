/**
 * This program is a demonstration of the Decorator design pattern.
 * It adds ingredients (Milk, Sugar) to Coffee dynamically without changing the base class.
 */

package ioprogramming.designpatterns;

interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    public double cost() { return 5.0; }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
    public double cost() { return decoratedCoffee.cost(); }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    public double cost() { return super.cost() + 2.0; }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    public double cost() { return super.cost() + 1.0; }
}

public class DecoratorDemonstration {
    public static void main(String[] args) {
        Coffee myCoffee = new SimpleCoffee();
        myCoffee = new MilkDecorator(myCoffee);
        myCoffee = new SugarDecorator(myCoffee);

        System.out.println("Total Cost: $" + myCoffee.cost());
    }
}
