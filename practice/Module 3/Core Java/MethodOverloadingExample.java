public class MethodOverloadingExample {

    // Method 1: Adds two integers
    public static int add(int a, int b) {
        return a + b;
    }

    // Method 2: Adds two doubles
    public static double add(double a, double b) {
        return a + b;
    }

    // Method 3: Adds three integers
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        // Calling each overloaded method and displaying results
        int sum1 = add(5, 10);            // Two integers
        double sum2 = add(5.5, 4.5);      // Two doubles
        int sum3 = add(1, 2, 3);          // Three integers

        System.out.println("Sum of two integers: " + sum1);
        System.out.println("Sum of two doubles: " + sum2);
        System.out.println("Sum of three integers: " + sum3);
    }
}
