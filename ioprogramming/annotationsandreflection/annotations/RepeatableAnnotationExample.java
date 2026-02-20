/**
 * This program implements repeatable annotations using
 * BugReports example
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

// 1. The Container Annotation
// This "holds" multiple @BugReport annotations.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

// 2. The Repeatable Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class) // Links it to the container above
@interface BugReport {
    String description();
}

// 3. The Class using the annotations
class DevelopmentTracker {

    @BugReport(description = "NullPointerException on empty input")
    @BugReport(description = "Performance lag in large datasets")
    public void processData() {
        System.out.println("Processing data...");
    }
}

// 4. The Main class to reflect and read them
public class RepeatableAnnotationExample {
    public static void main(String[] args) throws Exception {
        Method method = DevelopmentTracker.class.getMethod("processData");

        // Use getAnnotationsByType to retrieve all instances automatically
        BugReport[] reports = method.getAnnotationsByType(BugReport.class);

        System.out.println("Bug Reports for method: " + method.getName());
        System.out.println("----------------------------------------");

        for (BugReport report : reports) {
            System.out.println("- " + report.description());
        }
    }
}
