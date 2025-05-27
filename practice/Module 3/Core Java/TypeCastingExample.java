public class TypeCastingExample {
    public static void main(String[] args) {
        // Declare a double variable
        double myDouble = 9.78;

        // Explicit type casting: double to int
        int myIntFromDouble = (int) myDouble;

        // Display the result of casting double to int
        System.out.println("Original double value: " + myDouble);
        System.out.println("After casting to int: " + myIntFromDouble);

        // Declare an int variable
        int myInt = 25;

        // Implicit type casting: int to double
        double myDoubleFromInt = myInt;

        // Display the result of casting int to double
        System.out.println("Original int value: " + myInt);
        System.out.println("After casting to double: " + myDoubleFromInt);
    }
}
