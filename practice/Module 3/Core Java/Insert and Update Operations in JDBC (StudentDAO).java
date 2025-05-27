import java.sql.*;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to insert a new student
    public void insertStudent(int id, String name, int age) {
        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            int rows = ps.executeUpdate();
            System.out.println(rows + " student(s) inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update student details
    public void updateStudent(int id, String newName, int newAge) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, newAge);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " student(s) updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
