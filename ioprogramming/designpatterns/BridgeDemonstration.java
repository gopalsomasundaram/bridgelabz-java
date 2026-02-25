/**
 * This program is a demonstration of the Bridge design pattern.
 * It separates the Shape abstraction from the Color implementation.
 */

package ioprogramming.designpatterns;

interface Color {
    String fill();
}

class Red implements Color {
    public String fill() { return "Red"; }
}

class Blue implements Color {
    public String fill() { return "Blue"; }
}

abstract class Shape_Bridge {
    protected Color color;
    protected Shape_Bridge(Color color) { this.color = color; }
    abstract void draw();
}

class Circle_Bridge extends Shape_Bridge {
    public Circle_Bridge(Color color) { super(color); }
    void draw() { System.out.println("Drawing Circle in " + color.fill()); }
}

class Square extends Shape_Bridge {
    public Square(Color color) { super(color); }
    void draw() { System.out.println("Drawing Square in " + color.fill()); }
}

public class BridgeDemonstration {
    public static void main(String[] args) {
        Shape_Bridge redCircle = new Circle_Bridge(new Red());
        Shape_Bridge blueSquare = new Square(new Blue());

        redCircle.draw();
        blueSquare.draw();
    }
}
