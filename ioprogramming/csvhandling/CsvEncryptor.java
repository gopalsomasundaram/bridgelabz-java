/**
 * Encrypts specific columns in employees.csv using the AES algorithm.
 * Saves the scrambled data into employees_encrypted.csv.
 * * @author gopal
 */
package csvhandling;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;

public class CsvEncryptor {

    // AES is a symmetric encryption standard; KEY must be exactly 16 characters for AES-128
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1234567812345678";

    /**
     * Encrypts a plain text string into a Base64 encoded AES ciphertext.
     * * @param value The raw string to encrypt
     * @return Encrypted string in Base64 format
     * @throws Exception If encryption fails
     */
    public static String encrypt(String value) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encrypted = cipher.doFinal(value.getBytes());
        // Base64 encoding ensures binary encrypted data can be stored as plain text in the CSV
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static void main(String[] args) {
        String inputPath = "employees.csv";
        String outputPath = "employees_encrypted.csv";

        // Try-with-resources handles the automatic closing of file readers and writers
        try (CSVReader reader = new CSVReader(new FileReader(inputPath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputPath))) {

            String[] line;
            String[] header = reader.readNext(); // Read the header row first

            if (header != null) {
                writer.writeNext(header); // Preserve the header in the output file
            }

            // Iterate through the CSV records
            while ((line = reader.readNext()) != null) {
                // Expected format: ID(0), Name(1), Dept(2), Salary(3)
                // Encrypting sensitive fields (Department and Salary)
                if (line.length >= 4) {
                    line[2] = encrypt(line[2]);
                    line[3] = encrypt(line[3]);
                }

                writer.writeNext(line);
            }

            System.out.println("Encryption complete! Check " + outputPath);

        } catch (Exception e) {
            // Catches File IO issues or encryption logic errors
            System.err.println("Encryption failed. Ensure 'employees.csv' exists and the key is 16 bytes.");
            e.printStackTrace();
        }
    }
}