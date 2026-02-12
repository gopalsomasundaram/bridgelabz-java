/**
 * Sorts employee records from employees.csv based on their salary.
 * Uses Java Streams to find and display the top 5 highest-paid employees.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;

public class SortRecords {
    public static void main(String[] args) {
        String filename = "employees.csv";

        // Try-with-resources handles the file reader closure automatically
        try (FileReader reader = new FileReader(filename)) {

            // Parsing the CSV directly into a list of Employee objects using OpenCSV
            List<Employee> employeeList = new CsvToBeanBuilder<Employee>(reader)
                    .withType(Employee.class)
                    .build()
                    .parse();

            // Using Java Streams to process the list:
            // 1. sorted: Sorts by Salary in descending order (reversed)
            // 2. limit: Picks only the first 5 records
            // 3. toList: Collects the result back into a List
            List<Employee> topPaidEmployees = employeeList.stream()
                    .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                    .limit(5)
                    .toList();

            System.out.println("--- Top 5 Highest Paid Employees ---");

            // Iterating through the top-paid list using an enhanced for-loop
            for (var emp : topPaidEmployees) {
                System.out.println(emp.toString());
            }

        } catch (Exception e) {
            // Catches File IO, CSV Parsing, or Data Conversion errors
            System.err.println("There was an Exception: " + e.getMessage());
        }
    }
}