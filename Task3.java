package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Task3 {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // SQL queries for inserting two hard-coded records
        String query1 = "INSERT INTO student (roll_no, name, age, department, cgpa) " +
                        "VALUES (01, 'Banshidhar Behera', 19, 'CSE', 8.50)";
        
        String query2 = "INSERT INTO student (roll_no, name, age, department, cgpa) " +
                        "VALUES (02, 'Naresh Mahapatra', 21, 'CSE', 9.20)";

        try {
            // 1. Load Driver
            Class.forName("org.postgresql.Driver");

            // 2. Establish Connection and Create Statement
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement stmt = con.createStatement()) {
                
                // 3. Execute the first insert
                int rows1 = stmt.executeUpdate(query1);
                // 4. Execute the second insert
                int rows2 = stmt.executeUpdate(query2);
                
                System.out.println("------------------------------------------");
                System.out.println(rows1 + " record inserted (Student 01)");
                System.out.println(rows2 + " record inserted (Student 02)");
                System.out.println("Total records added: " + (rows1 + rows2));
                System.out.println("------------------------------------------");
                
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            // This will catch errors like "Duplicate Key" if you run the code twice
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}
