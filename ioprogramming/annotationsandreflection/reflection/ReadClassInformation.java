/**
 * This program accepts class information as input and
 * displays its methods, fields and constructors using
 * reflect API
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.*;
import java.util.Scanner;

class test{
    String alsoTest;
    void testMethod(){
        //Nothing Happens
    }
}

public class ReadClassInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter full class name (e.g., java.util.ArrayList): ");
        String className = scanner.nextLine();

        try {
            Class<?> clazz = Class.forName(className);

            System.out.println("\n--- Fields ---");
            for (Field f : clazz.getDeclaredFields()) System.out.println(f.getName());

            System.out.println("\n--- Constructors ---");
            for (Constructor<?> c : clazz.getDeclaredConstructors()) System.out.println(c);

            System.out.println("\n--- Methods ---");
            for (Method m : clazz.getDeclaredMethods()) System.out.println(m.getName());

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        }
    }
}