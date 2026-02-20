/**
 * This program adds dependency to classes with certain custom Annotation
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {}

class Engine { public void start() { System.out.println("Engine Vroom!"); } }

class Car {
    @Inject
    private Engine engine;

    public void drive() { engine.start(); }
}

public class DependencyInjection {
    public static void injectDependencies(Object obj) throws Exception {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                // Create the dependency dynamically
                Object dependency = field.getType().getDeclaredConstructor().newInstance();
                field.set(obj, dependency);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Car myCar = new Car();
        injectDependencies(myCar);
        myCar.drive(); // Works without manual 'new Engine()'
    }
}