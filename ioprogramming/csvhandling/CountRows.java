/**
 * Counts records in student.csv using OpenCSV.
 * Excludes the header row from the total count.
 * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CountRows {
    public static void main(String[] args) {
        // Try-with-resources ensures the reader closes automatically to prevent memory leaks
        try (CSVReader reader = new CSVReader(new FileReader("student.csv"))) {
            int count = 0;
            String[] line;

            // readNext() processes the file row by row; returns null at the end of the file
            while ((line = reader.readNext()) != null) {
                count++;
            }

            // Displays the result, subtracting 1 to exclude the header from the data count
            System.out.println("Total number of records in csv: " + (count - 1));

        } catch (IOException e) {
            // Handles missing files or permission issues
            System.out.println("An IOException has occurred! Please check if 'student.csv' exists.");
        } catch (CsvValidationException e) {
            // Handles structural errors within the CSV (e.g., mismatched quotes)
            System.out.println("The CSV format is invalid.");
        }
    }
}