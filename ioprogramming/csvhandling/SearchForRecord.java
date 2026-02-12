/**
 * Searches for a specific record in employees.csv based on the employee's name.
 * Accepts user input from the console and displays matching records.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class SearchForRecord {
    public static void main(String[] args) {
        // Scanner is used to capture the name to search for from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name to search for in csv file: ");
        String searchName = scanner.nextLine();

        // Try-with-resources handles the automatic closing of the CSVReader
        try (CSVReader reader = new CSVReader(new FileReader("employees.csv"))) {

            // Read and display the header row for context
            String[] line = reader.readNext();
            if (line != null) {
                System.out.printf("%s %17s %15s %7s\n", line[0], line[1], line[2], line[3]);
                System.out.println("------------------------------------------------------------");
            }

            boolean found = false;
            // Iterate through the file to find name matches in the second column (index 1)
            while ((line = reader.readNext()) != null) {
                if (line[1].equalsIgnoreCase(searchName)) {
                    System.out.printf("%s %17s %15s %7s\n", line[0], line[1], line[2], line[3]);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No records found for: " + searchName);
            }

        } catch (IOException e) {
            System.out.println("There was an IO Exception. Please ensure 'employees.csv' exists.");
        } catch (CsvValidationException e) {
            // Handles structural errors in the CSV (e.g., mismatched delimiters)
            System.out.println("The CSV format is invalid.");
        } finally {
            // Close scanner to prevent resource leak
            scanner.close();
        }
    }
}