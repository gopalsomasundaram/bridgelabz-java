/**
 * This program implements ObjectMapper that uses reflect API to set
 * field values frm a Map
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectMapper {
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        // 1. Create a new instance using the default constructor
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            try {
                // 2. Find the field matching the Map key
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);

                // 3. Set the value from the Map into the object
                field.set(instance, entry.getValue());
            } catch (NoSuchFieldException e) {
                System.out.println("Skipping field: " + entry.getKey() + " (not found in class)");
            }
        }
        return instance;
    }

    // Testing
    public static void main(String[] args) throws Exception {
        Map<String, Object> data = Map.of("name", "Arjun", "age", 25);
        User user = toObject(User.class, data);
        System.out.println("Mapped User: " + user.name + ", " + user.age);
    }
}

class User { String name; int age; public User() {} }
