/**
 * This program uses reflect to measure the execution
 * time of any given class's method
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Method;

// 1. A Sample Service with methods of varying "speed"
class HeavyProcessor {

    public void quickTask() {
        System.out.println("Executing quick task...");
        // Finishes almost instantly
    }

    public void mediumTask() throws InterruptedException {
        System.out.println("Executing medium task...");
        Thread.sleep(500); // 0.5 seconds
    }

    public void heavyTask() throws InterruptedException {
        System.out.println("Executing heavy task...");
        Thread.sleep(1200); // 1.2 seconds
    }
}

// 2. The Profiler Engine
class PerformanceProfiler {
    public static void profile(Object target) {
        Class<?> clazz = target.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        System.out.println("Starting performance profile for: " + clazz.getSimpleName());
        System.out.println("====================================================");

        for (Method method : methods) {
            try {
                // Record the start time in nanoseconds
                long startTime = System.nanoTime();

                // Dynamically invoke the method
                // Note: We assume these methods have no parameters for this example
                method.invoke(target);

                // Record the end time
                long endTime = System.nanoTime();

                // Calculate duration (convert nanos to milliseconds)
                long durationMs = (endTime - startTime) / 1_000_000;

                System.out.println("Method: " + method.getName() + " | Execution Time: " + durationMs + " ms");
                System.out.println("----------------------------------------------------");

            } catch (Exception e) {
                System.err.println("Could not profile method: " + method.getName() + " - " + e.getMessage());
            }
        }
    }
}

// 3. The Main Class
public class MeasureMethodTime {
    public static void main(String[] args) {
        // Instantiate the service we want to profile
        HeavyProcessor processor = new HeavyProcessor();

        // Run the profiler
        PerformanceProfiler.profile(processor);
    }
}