/**
 * This program is a demonstration of the Chain of Responsibility design pattern.
 * It passes a leave request through a chain of authority: TeamLead -> Manager -> HR.
 */

package ioprogramming.designpatterns;

abstract class Handler {
    protected Handler nextHandler;
    public void setNext(Handler nextHandler) { this.nextHandler = nextHandler; }
    public abstract void handleRequest(int days);
}

class TeamLead extends Handler {
    public void handleRequest(int days) {
        if (days <= 2) System.out.println("TeamLead approved " + days + " day(s) leave.");
        else if (nextHandler != null) nextHandler.handleRequest(days);
    }
}

class Manager_COR extends Handler {
    public void handleRequest(int days) {
        if (days <= 5) System.out.println("Manager approved " + days + " days leave.");
        else if (nextHandler != null) nextHandler.handleRequest(days);
    }
}

class HR extends Handler {
    public void handleRequest(int days) {
        System.out.println("HR approved " + days + " days leave.");
    }
}

public class ChainOfResponsibilityDemonstration {
    public static void main(String[] args) {
        Handler teamLead = new TeamLead();
        Handler manager = new Manager_COR();
        Handler hr = new HR();

        teamLead.setNext(manager);
        manager.setNext(hr);

        teamLead.handleRequest(1);
        teamLead.handleRequest(4);
        teamLead.handleRequest(10);
    }
}
