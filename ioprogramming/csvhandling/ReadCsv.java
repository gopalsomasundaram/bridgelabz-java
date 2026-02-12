/**
 * Reads and prints the contents of student.csv using OpenCSV.
 * Uses formatted output to align columns for better readability.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCsv {
    public static void main(String[] args) {
        // Try-with-resources ensures the CSVReader is closed automatically
        try (CSVReader csvreader = new CSVReader(new FileReader("student.csv"))) {
            String[] line;

            // readNext() parses one line at a time into a String array
            while ((line = csvreader.readNext()) != null) {
                // Using printf to format columns:
                // %s for strings, %17s for 17-character width alignment
                System.out.printf("%s %17s %4s %4s\n", line[0], line[1], line[2], line[3]);
            }
        } catch (IOException e) {
            // Catches file access issues like "File Not Found"
            System.out.println("There was an IO exception. Check if student.csv exists.");
        } catch (CsvValidationException e) {
            // Catches formatting errors within the CSV file itself
            System.err.println("The CSV structure is malformed: " + e.getMessage());
        }
    }
}