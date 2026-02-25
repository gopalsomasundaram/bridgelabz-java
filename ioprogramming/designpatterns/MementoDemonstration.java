/**
 * This program is a demonstration of the Memento design pattern.
 * It saves and restores the state of a simple text editor.
 */

package ioprogramming.designpatterns;

class Memento {
    private final String state;
    public Memento(String state) { this.state = state; }
    public String getState() { return state; }
}

class Editor {
    private String content;
    public void write(String text) { content = text; }
    public String getContent() { return content; }
    public Memento save() { return new Memento(content); }
    public void restore(Memento m) { content = m.getState(); }
}

class Caretaker {
    private Memento memento;
    public void keep(Memento m) { this.memento = m; }
    public Memento get() { return memento; }
}

public class MementoDemonstration {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Caretaker caretaker = new Caretaker();

        editor.write("Version 1");
        caretaker.keep(editor.save());

        editor.write("Version 2");
        System.out.println("Current: " + editor.getContent());

        editor.restore(caretaker.get());
        System.out.println("Restored: " + editor.getContent());
    }
}
