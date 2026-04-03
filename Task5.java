package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Task5 {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // SQL Query to select all records
        String selectSQL = "SELECT * FROM student";

        try {
            // 1. Load Driver
            Class.forName("org.postgresql.Driver");

            // 2. Establish Connection and Create Statement
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement stmt = con.createStatement();
                 // 3. Execute the query and store the results in a ResultSet
                 ResultSet rs = stmt.executeQuery(selectSQL)) {

                System.out.println("-----------------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-5s %-15s %-5s\n", "ROLL NO", "NAME", "AGE", "DEPT", "CGPA");
                System.out.println("-----------------------------------------------------------------------");

                // 4. Iterate through the ResultSet
                while (rs.next()) {
                    // Extract data by column name or index
                    int roll = rs.getInt("roll_no");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String dept = rs.getString("department");
                    double cgpa = rs.getDouble("cgpa");

                    // Print the formatted record
                    System.out.printf("%-10d %-20s %-5d %-15s %-5.2f\n", roll, name, age, dept, cgpa);
                }
                System.out.println("-----------------------------------------------------------------------");

            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found.");
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}
