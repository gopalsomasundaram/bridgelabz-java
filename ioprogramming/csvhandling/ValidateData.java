/**
 * Validates customer data in customer.csv using Regular Expressions (Regex).
 * Identifies and prints records with invalid email addresses or phone numbers.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
    public static void main(String[] args) {
        String sourcefile = "customer.csv";

        // Regex for standard email validation
        String mailRegex = "^[\\w._+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";

        // Regex for various phone formats (e.g., +1 (123) 456-7890, 123-456-7890)
        String phoneRegex = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";

        // Pre-compiling patterns for better performance during loop execution
        Pattern emailPattern = Pattern.compile(mailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (CSVReader reader = new CSVReader(new FileReader(sourcefile))) {
            // Read and skip the header row
            String[] line = reader.readNext();

            System.out.println("--- Invalid Records Found ---");

            while ((line = reader.readNext()) != null) {
                // Applying regex to Email (Index 2) and Phone (Index 3)
                Matcher emailMatch = emailPattern.matcher(line[2]);
                Matcher phoneMatch = phonePattern.matcher(line[3]);

                // If either the email or the phone number is invalid, print the record
                if (!emailMatch.matches() || !phoneMatch.matches()) {
                    System.out.printf("ID: %s | Name: %s | Email: %s | Phone: %s\n",
                            line[0], line[1], line[2], line[3]);
                }
            }
        } catch (Exception e) {
            // Catches File IO or CSV parsing exceptions
            System.err.println("Error processing data: " + e.getMessage());
        }
    }
}