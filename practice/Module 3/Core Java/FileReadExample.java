import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadExample {
    public static void main(String[] args) {
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line;

            System.out.println("Contents of output.txt:");

            // Read and display each line
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();  // Close the reader
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
