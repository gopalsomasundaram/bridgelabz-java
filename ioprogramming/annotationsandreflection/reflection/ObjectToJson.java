/**
 * This program uses reflect API to convert an object
 * into a json format
 */
package ioprogramming.annotationsandreflection.reflection;

import java.lang.reflect.Field;
import java.util.Collection;

// 1. A sample class to be converted to JSON
class Employee {
    private int id;
    private String name;
    private String department;
    private boolean isRemote;
    private double salary;

    public Employee(int id, String name, String department, boolean isRemote, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.isRemote = isRemote;
        this.salary = salary;
    }
}

// 2. The Reflection-based JSON Engine
class JsonConverter {
    public static String stringify(Object obj) throws Exception {
        if (obj == null) return "null";

        Class<?> clazz = obj.getClass();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true); // Access private fields

            // Add the key (field name)
            jsonBuilder.append("  \"").append(field.getName()).append("\": ");

            // Add the value based on its type
            Object value = field.get(obj);
            if (value instanceof String) {
                // Strings need quotes in JSON
                jsonBuilder.append("\"").append(value).append("\"");
            } else {
                // Numbers and booleans do not
                jsonBuilder.append(value);
            }

            // Add a comma if it's not the last field
            if (i < fields.length - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}

// 3. The Main Execution Class
public class ObjectToJson {
    public static void main(String[] args) {
        try {
            // Create an instance of our sample object
            Employee emp = new Employee(101, "Arjun Sharma", "Engineering", true, 95000.50);

            // Convert to JSON using Reflection
            System.out.println("--- Serializing Object to JSON ---");
            String jsonOutput = JsonConverter.stringify(emp);

            // Display result
            System.out.println(jsonOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}