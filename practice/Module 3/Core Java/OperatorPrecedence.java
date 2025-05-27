public class OperatorPrecedence {
    public static void main(String[] args) {
        // Expression 1
        int result1 = 10 + 5 * 2;
        System.out.println("Result 1 (10 + 5 * 2) = " + result1); // Expected: 20
        // Explanation: Multiplication (*) has higher precedence than addition (+), so:
        // 5 * 2 = 10, then 10 + 10 = 20

        // Expression 2
        int result2 = (10 + 5) * 2;
        System.out.println("Result 2 ((10 + 5) * 2) = " + result2); // Expected: 30
        // Explanation: Parentheses override precedence, so:
        // 10 + 5 = 15, then 15 * 2 = 30

        // Expression 3
        int result3 = 100 / 10 + 4 * 3 - 2;
        System.out.println("Result 3 (100 / 10 + 4 * 3 - 2) = " + result3); // Expected: 20
        // Explanation:
        // 100 / 10 = 10
        // 4 * 3 = 12
        // 10 + 12 = 22
        // 22 - 2 = 20

        // Expression 4
        int result4 = 5 + 2 * (8 - 3);
        System.out.println("Result 4 (5 + 2 * (8 - 3)) = " + result4); // Expected: 15
        // Explanation:
        // 8 - 3 = 5
        // 2 * 5 = 10
        // 5 + 10 = 15
    }
}
