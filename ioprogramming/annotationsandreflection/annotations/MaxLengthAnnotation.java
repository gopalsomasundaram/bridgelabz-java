/**
 * This program creates a custom annotation that can
 * check the length of a field and throw an exception
 * if it is longer than a specified length
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// 1. Define the Field-Level Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) // Specifically for variables/fields
@interface MaxLength {
    int value();
}

// 2. The User class that uses the annotation
class User {
    @MaxLength(10) // Username cannot be longer than 10 chars
    private String username;

    public User(String username) throws IllegalAccessException {
        this.username = username;
        // Trigger validation upon creation
        Validator.validate(this);
    }

    public String getUsername() {
        return username;
    }
}

// 3. The Validation Engine (The Reflection part)
class Validator {
    public static void validate(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            // Check if @MaxLength is present on this field
            if (field.isAnnotationPresent(MaxLength.class)) {

                // Since 'username' is private, we must bypass the access check
                field.setAccessible(true);

                // Get the limit from the annotation
                int limit = field.getAnnotation(MaxLength.class).value();

                // Get the actual value of the field from the object instance
                String fieldValue = (String) field.get(obj);

                if (fieldValue != null && fieldValue.length() > limit) {
                    throw new IllegalArgumentException(
                            "Field '" + field.getName() + "' exceeds max length of " + limit
                    );
                }
            }
        }
    }
}

// 4. Testing the logic
public class MaxLengthAnnotation {
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to create valid user...");
            new User("Alice"); // This should work
            System.out.println("Success!");

            System.out.println("\nAttempting to create invalid user...");
            new User("SuperLongUsername123"); // This should fail
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }
}