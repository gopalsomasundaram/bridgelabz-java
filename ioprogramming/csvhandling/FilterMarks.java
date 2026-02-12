/**
 * Filters student records from student.csv based on marks.
 * Displays students who have scored more than 80 marks.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class FilterMarks {
    public static void main(String[] args) {
        // Try-with-resources handles the automatic closing of the CSVReader and FileReader
        try (CSVReader reader = new CSVReader(new FileReader("student.csv"))) {

            // Read and skip the header row to prevent parsing errors in the loop
            String[] line = reader.readNext();

            System.out.println("ID  | Name              | Age | Marks");
            System.out.println("-------------------------------------");

            // Iterate through the file row by row
            while ((line = reader.readNext()) != null) {
                // Assuming column 3 contains the Marks (index starts at 0)
                int tempMarks = Integer.parseInt(line[3]);

                // Filtering condition: only display students with marks > 80
                if (tempMarks > 80) {
                    // Using printf for formatted and aligned column output
                    System.out.printf("%s | %-17s | %3s | %3s\n", line[0], line[1], line[2], line[3]);
                }
            }
        } catch (IOException e) {
            // Handles missing files or read/write permission issues
            System.out.println("There was an IO Exception. Please check the file path.");
        } catch (CsvValidationException e) {
            // Handles structural errors in the CSV file
            System.out.println("The CSV structure is invalid.");
        } catch (NumberFormatException e) {
            // Handles cases where the marks column does not contain a valid integer
            System.out.println("Error: Marks column contains non-numeric data.");
        }
    }
}