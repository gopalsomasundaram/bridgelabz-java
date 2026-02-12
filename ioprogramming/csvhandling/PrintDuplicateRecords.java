/**
 * Detects and prints duplicate records from duplicates.csv.
 * Uses a HashMap to track seen IDs and identify recurring entries.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.HashMap;

public class PrintDuplicateRecords {
    public static void main(String[] args) {
        // HashMap stores the unique ID as the key to track occurrences
        HashMap<String, Integer> map = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader("duplicates.csv"))) {
            String[] line;

            // Skip the header row
            reader.readNext();

            System.out.println("--- Duplicate Records Found ---");

            // Iterate through the file row by row
            while ((line = reader.readNext()) != null) {
                // Check if the ID (Index 0) has been encountered before
                if (map.containsKey(line[0])) {
                    // If the key exists, this is a duplicate record
                    System.out.printf("%s %-15s %s\n", line[0], line[1], line[2]);
                } else {
                    // If it's the first time seeing this ID, add it to the map
                    map.put(line[0], 1);
                }
            }
        } catch (Exception e) {
            // Catches File IO issues or CSV parsing errors
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}