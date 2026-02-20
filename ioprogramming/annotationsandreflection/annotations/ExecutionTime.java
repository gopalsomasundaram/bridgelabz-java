/**
 * This program uses custom annotations along with reflect apo
 * to mark methodss and calculate the execution time of a method
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// 1. Define the Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {}

// 2. A Class with methods to measure
class TaskRunner {

    @LogExecutionTime
    public void fastTask() {
        // Simulating a quick task
        System.out.println("Running fast task...");
    }

    @LogExecutionTime
    public void slowTask() throws InterruptedException {
        // Simulating a heavy task (1 second delay)
        System.out.println("Running slow task...");
        Thread.sleep(1000);
    }

    public void untrackedTask() {
        System.out.println("This task is not timed.");
    }
}

// 3. The Execution Engine (The "Magic" part)
public class ExecutionTime {
    public static void main(String[] args) throws Exception {
        TaskRunner runner = new TaskRunner();
        Class<?> clazz = runner.getClass();

        System.out.println("--- Starting Performance Profile ---");

        for (Method method : clazz.getDeclaredMethods()) {

            // Check if the method has our @LogExecutionTime label
            if (method.isAnnotationPresent(LogExecutionTime.class)) {

                long start = System.nanoTime(); // START TIMER

                // Use Reflection to actually execute (invoke) the method
                method.invoke(runner);

                long end = System.nanoTime();   // END TIMER

                long duration = (end - start) / 1_000_000; // Convert to milliseconds
                System.out.println("RESULT: [" + method.getName() + "] took " + duration + " ms");
                System.out.println("------------------------------------");
            }
        }
    }
}
