import java.sql.*;

public class AccountDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/bank";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void transferMoney(int fromAccount, int toAccount, double amount) {
        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try (Connection con = getConnection()) {
            con.setAutoCommit(false);  // Begin transaction

            try (PreparedStatement debitStmt = con.prepareStatement(debitQuery);
                 PreparedStatement creditStmt = con.prepareStatement(creditQuery)) {

                // Debit from sender
                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromAccount);
                int debitRows = debitStmt.executeUpdate();

                // Credit to receiver
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAccount);
                int creditRows = creditStmt.executeUpdate();

                if (debitRows == 1 && creditRows == 1) {
                    con.commit();  // Commit transaction
                    System.out.println("Transfer successful.");
                } else {
                    con.rollback();  // Rollback transaction
                    System.out.println("Transfer failed. Rolled back.");
                }

            } catch (SQLException e) {
                con.rollback();  // Rollback on exception
                System.out.println("Transaction error. Rolled back.");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
