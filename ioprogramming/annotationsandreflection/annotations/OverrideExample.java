/**
 * This program Demonstrates the use of Override annotation
 * using an example
 */
package ioprogramming.annotationsandreflection.annotations;

class Animal{
    void makeSound(){
        System.out.println("Animal Makes Noise X");
    }
}
class Dog extends Animal{
    @Override
    void makeSound(){
        System.out.println("Bark");
    }
}

public class OverrideExample {
    public static void main(String[] args){
        Animal obj = new Dog();
        obj.makeSound();
    }
}
