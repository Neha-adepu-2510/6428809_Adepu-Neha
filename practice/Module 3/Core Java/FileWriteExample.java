import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriteExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter a string to write to the file: ");
        String userInput = scanner.nextLine();

        // File writing block
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write(userInput);
            writer.close();  // Always close the writer
            System.out.println("Data has been written to output.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        scanner.close();
    }
}
