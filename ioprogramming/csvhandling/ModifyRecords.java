/**
 * Modifies specific records in employees.csv based on department.
 * Increases the salary of all "IT" department employees by 10%.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class ModifyRecords {
    public static void main(String[] args) {
        // Try-with-resources handles both reading from the source and writing to the target simultaneously
        try (CSVReader reader = new CSVReader(new FileReader("employees.csv"));
             CSVWriter writer = new CSVWriter(new FileWriter("AdjustedEmployees.csv"))) {

            // Read the header row and write it immediately to the new file
            String[] line = reader.readNext();
            if (line != null) {
                writer.writeNext(line);
            }

            // Iterate through each employee record
            while ((line = reader.readNext()) != null) {
                // Check if the department (Index 2) is "IT"
                if (line[2].equals("IT")) {
                    // Convert salary string to double for calculation
                    double salary = Double.parseDouble(line[3]);

                    // Apply a 10% increase and cast back to integer for storage
                    int updatedSalary = (int) (salary + (salary * 0.10));
                    line[3] = String.valueOf(updatedSalary);
                }

                // Write the (possibly modified) record to the new file
                writer.writeNext(line);
            }

            System.out.println("Salary adjustment complete. Output saved to 'AdjustedEmployees.csv'.");

        } catch (IOException e) {
            // Handles missing files or read/write access errors
            System.out.println("There was an IO Exception. Please check the file path.");
        } catch (CsvValidationException e) {
            // Handles malformed CSV formatting
            System.out.println("The CSV structure is invalid.");
        } catch (NumberFormatException e) {
            // Handles cases where the salary column is not a valid number
            System.out.println("Error: Salary column contains non-numeric data.");
        }
    }
}