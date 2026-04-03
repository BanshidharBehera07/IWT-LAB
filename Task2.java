package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Task2 {
    public static void main(String[] args) {
        // Connection details (using your previous credentials)
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // SQL Query to create the table
        String createTableSQL = "CREATE TABLE student ("
                + "roll_no INT PRIMARY KEY, "
                + "name VARCHAR(50), "
                + "age INT, "
                + "department VARCHAR(30), "
                + "cgpa NUMERIC(4,2)" // Using NUMERIC for precision (similar to FLOAT(4,2))
                + ")";

        try {
            // 1. Load the Driver
            Class.forName("org.postgresql.Driver");

            // 2. Establish Connection and Create Statement
            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement stmt = con.createStatement()) {
                
                // 3. Execute the SQL command
                stmt.executeUpdate(createTableSQL);
                
                System.out.println("------------------------------------------");
                System.out.println("Table 'student' created successfully!");
                System.out.println("------------------------------------------");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Error occurred while creating the table.");
            e.printStackTrace();
        }
    }
}
