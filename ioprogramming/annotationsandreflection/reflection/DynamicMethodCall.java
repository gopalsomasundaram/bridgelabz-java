/**
 * This program uses reflect to dynamically call methods of a class
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Method;
import java.util.Scanner;

class MathOperations {
    public void add(int a, int b) { System.out.println("Sum: " + (a + b)); }
    public void subtract(int a, int b) { System.out.println("Difference: " + (a - b)); }
    public void multiply(int a, int b) { System.out.println("Product: " + (a * b)); }
}

public class DynamicMethodCall {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MathOperations math = new MathOperations();

        System.out.print("Enter method name (add/subtract/multiply): ");
        String methodName = sc.next();

        // 1. Find the method by name and parameter types
        try {
            Method method = MathOperations.class.getMethod(methodName, int.class, int.class);

            // 2. Invoke the method on the 'math' instance with arguments
            method.invoke(math, 10, 5);
        } catch (NoSuchMethodException e) {
            System.out.println("Error: Method '" + methodName + "' does not exist.");
        }
    }
}