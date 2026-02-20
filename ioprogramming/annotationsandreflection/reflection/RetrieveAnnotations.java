/**
 * This program uses reflect API to retrieve the annotations
 * applied to a class
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.annotation.*;

// 1. Define the Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // Class-level target
@interface Author {
    String name();
}

// 2. Apply it to a class
@Author(name = "Gopal Somasundaram")
class LibrarySystem {
    public void startup() { System.out.println("System starting..."); }
}

// 3. Reflect and Retrieve
public class RetrieveAnnotations {
    public static void main(String[] args) {
        Class<LibrarySystem> clazz = LibrarySystem.class;

        // Check if the annotation is present on the class
        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Class: " + clazz.getSimpleName());
            System.out.println("Created by: " + author.name());
        }
    }
}