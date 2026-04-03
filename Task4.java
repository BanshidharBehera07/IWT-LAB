package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // SQL query with placeholders (?)
        String insertSQL = "INSERT INTO student (roll_no, name, age, department, cgpa) VALUES (?, ?, ?, ?, ?)";

        try (Scanner sc = new Scanner(System.in)) {
            // 1. Take inputs from the user
            System.out.println("--- Enter Student Details ---");
            System.out.print("Roll No: ");
            int roll = sc.nextInt();
            sc.nextLine(); // Consume newline left-over

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.print("Department: ");
            String dept = sc.nextLine();

            System.out.print("CGPA: ");
            double cgpa = sc.nextDouble();

            // 2. Establish Connection and Prepare Statement
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = con.prepareStatement(insertSQL)) {

                // 3. Bind the values to the placeholders
                pstmt.setInt(1, roll);
                pstmt.setString(2, name);
                pstmt.setInt(3, age);
                pstmt.setString(4, dept);
                pstmt.setDouble(5, cgpa);

                // 4. Execute the update
                int rowsInserted = pstmt.executeUpdate();
                
                if (rowsInserted > 0) {
                    System.out.println("------------------------------------------");
                    System.out.println("Student record inserted successfully!");
                    System.out.println("------------------------------------------");
                }

            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found.");
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
