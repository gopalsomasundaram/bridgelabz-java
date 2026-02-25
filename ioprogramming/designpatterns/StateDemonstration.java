/**
 * This program is a demonstration of the State design pattern.
 * It changes the ATM's behavior based on whether a card is inserted or authorized.
 */

package ioprogramming.designpatterns;

interface State { void handle(); }

class NoCardState implements State {
    public void handle() { System.out.println("No Card. Please insert card."); }
}

class HasCardState implements State {
    public void handle() { System.out.println("Card Detected. Please enter PIN."); }
}

class AuthorizedState implements State {
    public void handle() { System.out.println("Authorized. Accessing account..."); }
}

class ATMContext {
    private State state;
    public void setState(State state) { this.state = state; }
    public void request() { state.handle(); }
}

public class StateDemonstration {
    public static void main(String[] args) {
        ATMContext atm = new ATMContext();

        atm.setState(new NoCardState());
        atm.request();

        atm.setState(new HasCardState());
        atm.request();
    }
}
