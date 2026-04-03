package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // SQL Query to update the CGPA based on roll_no
        String updateSQL = "UPDATE student SET cgpa = ? WHERE roll_no = ?";

        try (Scanner sc = new Scanner(System.in)) {
            // 1. Get the target roll number and new CGPA from user
            System.out.println("--- Update Student CGPA ---");
            System.out.print("Enter Roll Number of the student: ");
            int roll = sc.nextInt();
            
            System.out.print("Enter the New CGPA: ");
            double newCgpa = sc.nextDouble();

            // 2. Load Driver and Establish Connection
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = con.prepareStatement(updateSQL)) {

                // 3. Bind the values to the placeholders (?)
                // Set the NEW CGPA first (position 1), then the Roll No (position 2)
                pstmt.setDouble(1, newCgpa);
                pstmt.setInt(2, roll);

                // 4. Execute the update
                int rowsUpdated = pstmt.executeUpdate();
                
                System.out.println("------------------------------------------");
                if (rowsUpdated > 0) {
                    System.out.println("Success! CGPA updated for Roll No: " + roll);
                } else {
                    // This happens if the roll number does not exist in the table
                    System.out.println("No student found with Roll No: " + roll);
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
