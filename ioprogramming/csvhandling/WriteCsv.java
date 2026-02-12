/**
 * Creates a new CSV file and writes multiple employee records to it.
 * Demonstrates the use of CSVWriter for basic file generation.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCsv {
    public static void main(String[] args) {
        // Try-with-resources handles the automatic closing of the CSVWriter and FileWriter
        try (CSVWriter writer = new CSVWriter(new FileWriter("writtenStudents.csv"))) {

            // Writing the header row
            // Note: Each element in the array represents one column
            writer.writeNext(new String[]{"ID", "Name", "Department", "Salary"});

            // Writing individual data records
            writer.writeNext(new String[]{"101", "Liam Chen", "Design", "25000"});
            writer.writeNext(new String[]{"102", "Sophia Rodriguez", "Testing", "20000"});
            writer.writeNext(new String[]{"103", "Marcus Thorne", "Human Resources", "55000"});
            writer.writeNext(new String[]{"104", "Isabella Rossi", "Design", "35000"});
            writer.writeNext(new String[]{"105", "Ethan Smith", "Design", "25000"});

            System.out.println("writtenStudents.csv has been written successfully.");

        } catch (IOException e) {
            // Handles cases like disk full, file locked, or invalid path
            System.out.println("There was an IO Exception! " + e.getMessage());
        }
    }
}