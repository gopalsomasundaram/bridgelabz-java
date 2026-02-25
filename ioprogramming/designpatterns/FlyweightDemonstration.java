/**
 * This program is a demonstration of the Flyweight design pattern.
 * It reuses Font objects to save memory when rendering multiple characters.
 */

package ioprogramming.designpatterns;

import java.util.*;

class Font {
    private String name;
    public Font(String name) { this.name = name; }
    public void printInfo() { System.out.print("Font: " + name); }
}

class FontFactory {
    private static Map<String, Font> fonts = new HashMap<>();
    public static Font getFont(String name) {
        if (!fonts.containsKey(name)) {
            fonts.put(name, new Font(name));
        }
        return fonts.get(name);
    }
}

class Character {
    private char val;
    private int x, y;
    private Font font; // The flyweight object

    public Character(char val, int x, int y, Font font) {
        this.val = val; this.x = x; this.y = y; this.font = font;
    }

    public void display() {
        System.out.print("Char: " + val + " at (" + x + "," + y + ") ");
        font.printInfo();
        System.out.println();
    }
}

public class FlyweightDemonstration {
    public static void main(String[] args) {
        Font arial = FontFactory.getFont("Arial");

        Character c1 = new Character('A', 10, 20, arial);
        Character c2 = new Character('B', 15, 20, arial); // Reuses the same Arial object

        c1.display();
        c2.display();
    }
}
