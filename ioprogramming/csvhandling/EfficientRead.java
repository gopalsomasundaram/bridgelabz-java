/**
 * Efficiently reads a large CSV file using BufferedReader and batch processing.
 * Processes data in chunks of 100 to maintain a low memory footprint.
 * * @author gopal
 */
package csvhandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EfficientRead {
    public static void main(String[] args){
        final String LARGE_CSV = "MOCK_DATA.csv";
        final int BATCH_SIZE = 100;

        // Internal buffer to store current batch of rows as raw strings
        List<String> batch = new ArrayList<>(BATCH_SIZE);
        int totalCount = 0;

        // Using BufferedReader for sequential access to minimize RAM usage
        try(BufferedReader reader = new BufferedReader(new FileReader(LARGE_CSV))){

            // Skip the header row
            String line = reader.readLine();

            // Read line-by-line to handle files larger than available memory
            while((line = reader.readLine()) != null){
                batch.add(line);
                totalCount++;

                // Trigger processing once the batch is full
                if(batch.size() == BATCH_SIZE){
                    System.out.println("Processed 100 records. Total count: " + totalCount);

                    // Clear the list to free up memory for the next batch
                    batch.clear();
                }
            }

            // Final cleanup for records that didn't form a full batch of 100
            if (!batch.isEmpty()) {
                System.out.println("Processed final batch. Size: " + batch.size());
            }

            System.out.println("Operation complete. Total records handled: " + totalCount);

        } catch (Exception e){
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}