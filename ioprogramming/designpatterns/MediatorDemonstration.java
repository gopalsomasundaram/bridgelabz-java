/**
 * This program is a demonstration of the Mediator design pattern.
 * It uses a ChatRoom to facilitate communication between Users.
 */

package ioprogramming.designpatterns;

interface ChatMediator { void sendMessage(String msg, User_M user); }

class ChatRoom implements ChatMediator {
    public void sendMessage(String msg, User_M user) {
        System.out.println("[" + user.getName() + "]: " + msg);
    }
}

class User_M {
    private String name;
    private ChatMediator mediator;
    public User_M(String name, ChatMediator m) { this.name = name; this.mediator = m; }
    public String getName() { return name; }
    public void send(String msg) { mediator.sendMessage(msg, this); }
}

public class MediatorDemonstration {
    public static void main(String[] args) {
        ChatRoom chat = new ChatRoom();
        User_M u1 = new User_M("User1", chat);
        User_M u2 = new User_M("User2", chat);

        u1.send("Hello everyone!");
        u2.send("Hey User1!");
    }
}
