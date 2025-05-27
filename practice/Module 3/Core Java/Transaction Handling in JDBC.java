public class Main {
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();

        // Simulate transfer of ₹300 from Alice (ID 1) to Bob (ID 2)
        dao.transferMoney(1, 2, 300.0);
    }
}
