/**
 * This program creates a custom annotation that maps field names to custom
 * Json keys
 */
package ioprogramming.annotationsandreflection.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 1. Define the Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name() default ""; // The custom JSON key name
}

// 2. The User Class with annotated fields
class UserTest {
    @JsonField(name = "user_id")
    private int id;

    @JsonField(name = "full_name")
    private String name;

    private String internalCode; // No annotation, should be ignored or used as is

    public UserTest(int id, String name, String internalCode) {
        this.id = id;
        this.name = name;
        this.internalCode = internalCode;
    }
}

// 3. The Serialization Engine
class JsonSerializer {
    public static String serialize(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Map<String, String> jsonElements = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // Bypass private access

            String key;
            if (field.isAnnotationPresent(JsonField.class)) {
                // Use the custom name from the annotation
                key = field.getAnnotation(JsonField.class).name();
            } else {
                // Fallback to the actual field name
                key = field.getName();
            }

            Object value = field.get(obj);
            jsonElements.put(key, value == null ? "null" : value.toString());
        }

        // Convert the Map into a JSON-like string format
        String jsonString = jsonElements.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"")
                .collect(Collectors.joining(", "));

        return "{ " + jsonString + " }";
    }
}

// 4. Testing the Serializer
public class JsonAnnotationDemo {
    public static void main(String[] args) throws Exception {
        UserTest userTest = new UserTest(101, "John Doe", "SECRET_99");

        String json = JsonSerializer.serialize(userTest);

        System.out.println("Generated JSON String:");
        System.out.println(json);
    }
}
