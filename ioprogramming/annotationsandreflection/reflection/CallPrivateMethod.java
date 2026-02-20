/**
 * This program uses reflect API to access and call the
 * private method of a class
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Method;

class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}

public class CallPrivateMethod {
    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();

        // Find the private method by name and parameter types
        Method method = Calculator.class.getDeclaredMethod("multiply", int.class, int.class);

        method.setAccessible(true);

        // Invoke and capture result
        int result = (int) method.invoke(calc, 5, 4);
        System.out.println("Result of private multiply(5, 4): " + result);
    }
}

