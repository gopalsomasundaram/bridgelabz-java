/**
 * Merges two separate CSV files (student1.csv and student2.csv) into a single file.
 * Links records based on a matching ID and combines personal and academic data.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class mergeCsv {
    public static void main(String[] args) {
        // Try-with-resources handles multiple readers and a writer simultaneously
        try (CSVReader student1Reader = new CSVReader(new FileReader("student1.csv"));
             CSVReader student2Reader = new CSVReader(new FileReader("student2.csv"));
             CSVWriter writer = new CSVWriter(new FileWriter("mergedStudent.csv"))) {

            // Read and discard headers from both input files
            String[] line1 = student1Reader.readNext();
            String[] line2 = student2Reader.readNext();

            // Write a new combined header to the output file
            String[] combinedHeader = {"Id", "Name", "Age", "Marks", "Grade"};
            writer.writeNext(combinedHeader);

            // Iterate through both files row by row
            // This logic assumes both files are sorted by ID and have a 1:1 mapping
            while ((line1 = student1Reader.readNext()) != null && (line2 = student2Reader.readNext()) != null) {
                String[] combined = new String[5];

                // Check if the IDs match before merging the data
                if (line1[0].equals(line2[0])) {
                    combined[0] = line1[0]; // Id
                    combined[1] = line1[1]; // Name
                    combined[2] = line1[2]; // Age
                    combined[3] = line2[1]; // Marks
                    combined[4] = line2[2]; // Grade
                }

                // Write the merged record to the output file
                writer.writeNext(combined);
            }

            System.out.println("Merge complete! Output saved to 'mergedStudent.csv'.");

        } catch (Exception e) {
            // Catches File IO issues or CSV parsing errors
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}