public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        // Insert a new student
        dao.insertStudent(4, "Anika", 23);

        // Update an existing student
        dao.updateStudent(2, "Hruthwik R", 22);
    }
}
