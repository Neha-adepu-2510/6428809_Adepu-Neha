public class Example {
    public int square(int x) {
        return x * x;
    }

    public static void main(String[] args) {
        Example ex = new Example();
        System.out.println("Square of 5: " + ex.square(5));
    }
}
