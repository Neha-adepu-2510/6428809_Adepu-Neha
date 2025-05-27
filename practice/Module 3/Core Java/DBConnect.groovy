import java.sql.*;

public class DBConnect {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root"; // default for XAMPP
        String password = ""; // empty for XAMPP by default

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connection successful!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("name") + " - " +
                    rs.getInt("age") + " - " +
                    rs.getString("grade")
                );
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
