/**
 * This program demonstrates the use of Factory method
 * design pattern
 */
package ioprogramming.designpatterns;

interface Shape{void Draw();}

class Circle implements Shape{
    @Override
    public void Draw(){
        System.out.println("circle");
    }
}

class Rectangle implements Shape{
    @Override
    public void Draw(){
        System.out.println("rectangle");
    }
}
class ShapeFactory{
    public static Shape getShape(String type){
        if(type.equalsIgnoreCase("circle")){
            return new Circle();
        }else if(type.equalsIgnoreCase("rectangle")){
            return new Rectangle();
        }
        return null;
    }
}
public class FactoryMethodDemonstration {
    public static void main(String[] args){
        Shape firstShape = ShapeFactory.getShape("Circle");
        Shape secondShape = ShapeFactory.getShape("rectangle");
        if(firstShape instanceof Shape && secondShape instanceof Shape){
            System.out.println("Both of the shapes were generated successfully");
        }
    }
}
