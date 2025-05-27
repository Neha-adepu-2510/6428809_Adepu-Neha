import java.util.HashMap;
import java.util.Scanner;

public class StudentMap {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        char choice;

        // Adding entries to the HashMap
        do {
            System.out.print("Enter student ID (integer): ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            studentMap.put(id, name);

            System.out.print("Do you want to add another entry? (y/n): ");
            choice = scanner.nextLine().toLowerCase().charAt(0);
        } while (choice == 'y');

        // Retrieving name based on student ID
        System.out.print("\nEnter a student ID to search: ");
        int searchId = Integer.parseInt(scanner.nextLine());

        if (studentMap.containsKey(searchId)) {
            System.out.println("Student name: " + studentMap.get(searchId));
        } else {
            System.out.println("Student ID not found.");
        }

        scanner.close();
    }
}
