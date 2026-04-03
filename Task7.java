package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // SQL Query to delete a record based on roll_no
        String deleteSQL = "DELETE FROM student WHERE roll_no = ?";

        try (Scanner sc = new Scanner(System.in)) {
            // 1. Get the roll number to delete from the user
            System.out.println("--- Delete Student Record ---");
            System.out.print("Enter Roll Number of the student to delete: ");
            int roll = sc.nextInt();

            // 2. Load Driver and Establish Connection
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = con.prepareStatement(deleteSQL)) {

                // 3. Bind the roll number to the placeholder (?)
                pstmt.setInt(1, roll);

                // 4. Execute the deletion
                int rowsDeleted = pstmt.executeUpdate();
                
                System.out.println("------------------------------------------");
                if (rowsDeleted > 0) {
                    System.out.println("Success! Record for Roll No: " + roll + " has been deleted.");
                } else {
                    // This happens if the roll number does not exist
                    System.out.println("No record found with Roll No: " + roll);
                }
                System.out.println("------------------------------------------");

            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found.");
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}
