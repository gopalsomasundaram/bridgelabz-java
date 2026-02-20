/**
 * This program creates and uses a custom Annotation
 * to mark the importance of method
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// 1. Define the custom annotation
// We use @Retention(RUNTIME) so we can see it while the app is running
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH"; // Optional with a default value
}

// 2. Apply the annotation to a class
class BusinessService {

    @ImportantMethod(level = "CRITICAL")
    public void processPayment() {
        System.out.println("Processing payment...");
    }

    @ImportantMethod // Uses the default "HIGH" level
    public void sendEmailNotification() {
        System.out.println("Sending email...");
    }

    public void logActivity() {
        System.out.println("Logging basic activity (not important).");
    }
}

// 3. Use Reflection to find and print the methods
public class ImportanceTracker {
    public static void main(String[] args) {
        Class<BusinessService> serviceClass = BusinessService.class;

        System.out.println("Scanning " + serviceClass.getSimpleName() + " for important methods...\n");

        // Iterate through all methods in the class
        for (Method method : serviceClass.getDeclaredMethods()) {

            // Check if our custom annotation is present
            if (method.isAnnotationPresent(ImportantMethod.class)) {

                // Get the annotation object to read the 'level'
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);

                System.out.println(">> Found Important Method: " + method.getName());
                System.out.println("   Importance Level: " + annotation.level());
                System.out.println("------------------------------------");
            }
        }
    }
}