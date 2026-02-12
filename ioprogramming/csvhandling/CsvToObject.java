/**
 * Reads student data from a CSV file and maps it to a list of Java objects.
 * Uses OpenCSV's Bean Mapping for automatic data binding and type conversion.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.util.List;

public class CsvToObject {
    public static void main(String[] args) {
        // Try-with-resources ensures the FileReader is closed automatically after use
        try (FileReader reader = new FileReader("student.csv")) {

            // CsvToBeanBuilder converts the CSV rows into a List of Student objects
            List<Student> students = new CsvToBeanBuilder<Student>(reader)
                    .withType(Student.class) // Specifies the target class
                    .withIgnoreLeadingWhiteSpace(true) // Trims spaces around column values
                    .build()
                    .parse();

            // Displaying the successfully mapped objects
            for (Student s : students) {
                System.out.println(s);
            }

        } catch (Exception e) {
            // Catches File IO issues or column mapping errors
            System.out.println("Error processing CSV: " + e.getMessage());
        }
    }
}