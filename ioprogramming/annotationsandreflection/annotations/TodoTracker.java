/**
 * This program implements a custom annotation that holds
 * data regarding who the task is assigned to and its priority
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// 1. Define the @Todo annotation
@Retention(RetentionPolicy.RUNTIME) // Must be RUNTIME for Reflection to work
@Target(ElementType.METHOD)         // We are marking methods
@interface Todo {
    String task();                  // The task description
    String assignedTo();            // The developer name
    String priority() default "MEDIUM";
}

// 2. Class containing methods with pending tasks
class ProjectModule {

    @Todo(task = "Implement OAuth2 login", assignedTo = "John Doe", priority = "HIGH")
    public void loginFeature() {
        // Code to be written...
    }

    @Todo(task = "Add dark mode toggle", assignedTo = "Jane Smith") // Uses default MEDIUM priority
    public void uiSettings() {
        // Code to be written...
    }

    @Todo(task = "Optimize SQL queries", assignedTo = "John Doe", priority = "LOW")
    public void databaseCleanup() {
        // Code to be written...
    }

    public void completedTask() {
        System.out.println("This feature is already finished!");
    }
}

// 3. The Processor that reads the annotations
public class TodoTracker {
    public static void main(String[] args) {
        Class<ProjectModule> obj = ProjectModule.class;

        System.out.println("=== PENDING TASKS REPORT ===");

        // Use Reflection to inspect methods
        for (Method method : obj.getDeclaredMethods()) {

            // Filter: Only process methods with the @Todo annotation
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);

                System.out.println("Method:   " + method.getName());
                System.out.println("Task:     " + todo.task());
                System.out.println("Assigned: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
                System.out.println("----------------------------");
            }
        }
    }
}