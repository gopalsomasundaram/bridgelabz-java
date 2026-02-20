/**
 * This program implements a custom annotation for managing role based
 * access for users, denying access to used without appropriate roles
 * and generating alert messages for when certain ops are performed
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// 1. Define the Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value(); // This will store the required role (e.g., "ADMIN")
}

// 2. The Service with Restricted Methods
class SecretService {

    @RoleAllowed("ADMIN")
    public void deleteDatabase() {
        System.out.println("CRITICAL: Database has been deleted!");
    }

    @RoleAllowed("USER")
    public void viewDashboard() {
        System.out.println("Displaying user dashboard...");
    }

    public void publicInfo() {
        System.out.println("This info is available to everyone.");
    }
}

// 3. The Security Engine
class SecurityProxy {
    public static void execute(Object target, String methodName, String currentUserRole) throws Exception {
        Method method = target.getClass().getMethod(methodName);

        // Check if the method is restricted by a role
        if (method.isAnnotationPresent(RoleAllowed.class)) {
            String requiredRole = method.getAnnotation(RoleAllowed.class).value();

            // Logic Gate: Compare current user role to required role
            if (requiredRole.equalsIgnoreCase(currentUserRole)) {
                method.invoke(target);
            } else {
                System.out.println("ACCESS DENIED! Role '" + currentUserRole +
                        "' is not authorized to call " + methodName);
            }
        } else {
            // No annotation means the method is public
            method.invoke(target);
        }
    }
}

// 4. Testing the Role-Based Access
public class RoleBasedAccess {
    public static void main(String[] args) throws Exception {
        SecretService service = new SecretService();

        System.out.println("--- Scenario 1: Admin attempts to delete database ---");
        SecurityProxy.execute(service, "deleteDatabase", "ADMIN");

        System.out.println("\n--- Scenario 2: Regular user attempts to delete database ---");
        SecurityProxy.execute(service, "deleteDatabase", "USER");

        System.out.println("\n--- Scenario 3: Regular user views dashboard ---");
        SecurityProxy.execute(service, "viewDashboard", "USER");
    }
}