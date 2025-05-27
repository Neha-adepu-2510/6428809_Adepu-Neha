public class PatternMatchingSwitchExample {

    public static void printObjectType(Object obj) {
        // Pattern matching in enhanced switch
        switch (obj) {
            case Integer i -> System.out.println("It's an Integer: " + i);
            case String s -> System.out.println("It's a String: " + s);
            case Double d -> System.out.println("It's a Double: " + d);
            case null -> System.out.println("It's null.");
            default -> System.out.println("Unknown type: " + obj);
        }
    }

    public static void main(String[] args) {
        printObjectType(42);
        printObjectType("Hello, Java 21!");
        printObjectType(3.14);
        printObjectType(true);
        printObjectType(null);
    }
}
