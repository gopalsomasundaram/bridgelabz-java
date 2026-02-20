/**
 * This program uses reflect API to implement custom logging
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.*;

interface Greeting { void sayHello(String name); }

class GreetingImpl implements Greeting {
    public void sayHello(String name) { System.out.println("Hello, " + name); }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;
    public LoggingHandler(Object target) { this.target = target; }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[LOG] Executing method: " + method.getName());
        return method.invoke(target, args);
    }
}

public class CustomLogging {
    public static void main(String[] args) {
        Greeting realService = new GreetingImpl();

        // Create the proxy
        Greeting proxy = (Greeting) Proxy.newProxyInstance(
                Greeting.class.getClassLoader(),
                new Class<?>[]{Greeting.class},
                new LoggingHandler(realService)
        );

        proxy.sayHello("User"); // This triggers the log!
    }
}