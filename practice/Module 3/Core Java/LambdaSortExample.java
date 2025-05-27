import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaSortExample {
    public static void main(String[] args) {
        // Create a list of strings
        List<String> names = new ArrayList<>();
        names.add("Rahul");
        names.add("Anita");
        names.add("Sunil");
        names.add("Priya");

        System.out.println("Before sorting: " + names);

        // Sort the list using lambda expression
        Collections.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));

        System.out.println("After sorting: " + names);
    }
}
