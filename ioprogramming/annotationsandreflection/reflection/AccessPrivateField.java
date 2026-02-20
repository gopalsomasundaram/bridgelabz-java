/**
 * This program uses reflect API to access private field of a class
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Field;

class Person {
    private int age = 20;
}

public class AccessPrivateField {
    public static void main(String[] args) throws Exception {
        Person person = new Person();

        Field ageField = Person.class.getDeclaredField("age");

        // Breaking the 'private' barrier
        ageField.setAccessible(true);

        // Retrieve value
        System.out.println("Original Age: " + ageField.get(person));

        // Modify value
        ageField.set(person, 30);
        System.out.println("Modified Age: " + ageField.get(person));
    }
}