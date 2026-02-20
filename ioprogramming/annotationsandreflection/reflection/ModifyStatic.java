/**
 * This program uses reflect API to change static field of a class
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "DEFAULT_KEY_12345";
}

public class ModifyStatic {
    public static void main(String[] args) throws Exception {
        Field field = Configuration.class.getDeclaredField("API_KEY");

        // 1. Bypass private access
        field.setAccessible(true);

        // 2. Get static value (Pass 'null' because there is no instance)
        System.out.println("Old API Key: " + field.get(null));

        // 3. Modify static value
        field.set(null, "PROD_KEY_99999");

        System.out.println("New API Key: " + field.get(null));
    }
}