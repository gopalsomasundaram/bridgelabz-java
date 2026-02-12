package csvhandling;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

public class Student {
    @CsvBindByName(column = "Id")
    @JsonProperty("id")
    private int id;

    @CsvBindByName(column = "Name")
    @JsonProperty("name")
    private String name;

    @CsvBindByName(column = "Age")
    @JsonProperty("age")
    private int age;

    // Standard getters, setters, and no-arg constructor are required
    public Student() {}
    public Student(int id, String name, int age) {
        this.id = id; this.name = name; this.age = age;
    }
}