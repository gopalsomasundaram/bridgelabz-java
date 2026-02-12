package csvhandling;

import com.opencsv.bean.CsvBindByName;

public class Employee {
    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "department")
    private String department;

    @CsvBindByName(column = "salary")
    private int salary;

    // Getters are required for sorting
    public int getSalary() { return salary; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("%s %d %s %d", name, id, department, salary);
    }
}