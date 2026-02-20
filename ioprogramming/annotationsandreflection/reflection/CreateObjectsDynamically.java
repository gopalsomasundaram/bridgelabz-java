/**
 * This program creates an instance of a class using reflect API
 * without the use of new keyword
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Constructor;

class Student {
    private String name;

    // Default constructor for reflection
    public Student() {
        this.name = "Unknown Student";
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
}

public class CreateObjectsDynamically {
    public static void main(String[] args) throws Exception {
        // 1. Get the Class object
        Class<?> clazz = Student.class;

        // 2. Get the default constructor
        Constructor<?> constructor = clazz.getConstructor();

        // 3. Create the instance (replaces 'new Student()')
        Object studentInstance = constructor.newInstance();

        // 4. Verify by calling a method
        Student student = (Student) studentInstance;
        student.sayHello();
    }
}
