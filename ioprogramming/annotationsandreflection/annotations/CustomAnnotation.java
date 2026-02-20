/**
 * This program implements a custom interface and uses
 * reflection api to check if method has Annotations
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.reflect.*;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TaskInfo{
    String priority() default "LOW";
    String assignedTo();
}

class TaskManager{
    @TaskInfo(priority = "HIGH", assignedTo = "Balakumar")
    public void processingReq(){
        System.out.println("Processing request...");
    }
}

public class CustomAnnotation {
    public static void main(String[] args){
        // 1. Get the class object
        Class<TaskManager> clazz = TaskManager.class;

        // 2. Iterate through all methods
        for (Method method : clazz.getDeclaredMethods()) {

            // 3. Check if @TaskInfo is present
            if (method.isAnnotationPresent(TaskInfo.class)) {

                // 4. Extract the annotation object
                TaskInfo info = method.getAnnotation(TaskInfo.class);

                // 5. Use the data
                System.out.println("--- Task Found ---");
                System.out.println("Method:   " + method.getName());
                System.out.println("Priority: " + info.priority());
                System.out.println("Assigned: " + info.assignedTo());
            }
        }
    }
}
