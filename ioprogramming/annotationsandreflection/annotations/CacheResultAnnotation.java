/**
 * This program implements a custom annotation that allows storing information
 * thus avoiding expensive re-computing of already calculates results
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// 1. Define the Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {}

// 2. A Service with an expensive computation
class CalculatorService {

    @CacheResult
    public int expensiveSum(int a, int b) {
        // Simulating a very slow database or math operation
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        return a + b;
    }
}

// 3. The Caching Engine (The Interceptor)
class CacheManager {
    // Stores: "MethodName:Arg1,Arg2" -> Result
    private static final Map<String, Object> cache = new HashMap<>();

    public static Object invokeWithCache(Object target, String methodName, Object... args) throws Exception {
        // Find the method
        Class<?>[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass(); // Simplistic type checking
        }

        // In a real app, we'd handle primitive vs wrapper types more robustly
        Method method = target.getClass().getMethod(methodName, int.class, int.class);

        // Check if it's marked for caching
        if (method.isAnnotationPresent(CacheResult.class)) {
            // Create a unique key for this specific call
            String key = methodName + ":" + java.util.Arrays.toString(args);

            if (cache.containsKey(key)) {
                System.out.print("[CACHE HIT] ");
                return cache.get(key);
            }

            // Not in cache, so execute and save
            System.out.print("[CACHE MISS - Computing...] ");
            Object result = method.invoke(target, args);
            cache.put(key, result);
            return result;
        }

        return method.invoke(target, args);
    }
}

// 4. Testing the System
public class CacheResultAnnotation {
    public static void main(String[] args) throws Exception {
        CalculatorService service = new CalculatorService();

        System.out.println("First call (10, 20): " + CacheManager.invokeWithCache(service, "expensiveSum", 10, 20));
        System.out.println("Second call (10, 20): " + CacheManager.invokeWithCache(service, "expensiveSum", 10, 20));
        System.out.println("Third call (5, 5): " + CacheManager.invokeWithCache(service, "expensiveSum", 5, 5));
        System.out.println("Fourth call (10, 20): " + CacheManager.invokeWithCache(service, "expensiveSum", 10, 20));
    }
}
