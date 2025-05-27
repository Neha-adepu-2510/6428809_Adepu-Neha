import java.util.ArrayList;
import java.util.Scanner;

public class StudentList {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String name;
        char choice;

        // Allow user to add names
        do {
            System.out.print("Enter student name: ");
            name = scanner.nextLine();
            studentNames.add(name);

            System.out.print("Do you want to add another name? (y/n): ");
            choice = scanner.nextLine().toLowerCase().charAt(0);
        } while (choice == 'y');

        // Display all names
        System.out.println("\nList of student names:");
        for (String student : studentNames) {
            System.out.println(student);
        }

        scanner.close();
    }
}
