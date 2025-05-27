public class Sample {
    public Sample() {
    }

    private String greet(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        Sample s = new Sample();
        System.out.println(s.greet("World"));
    }
}
