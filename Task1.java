package Assignment7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Task1 {
    public static void main(String[] args) {
        // Database credentials based on your environment
        String url = "jdbc:postgresql://192.168.1.17/cse_db24";
        String user = "25bcsl58";
        String password = "25bcsl58";

        // Using try-with-resources to ensure the connection is closed automatically
        try {
            // 1. Load the PostgreSQL Driver
            Class.forName("org.postgresql.Driver");

            // 2. Attempt to establish the connection
            try (Connection con = DriverManager.getConnection(url, user, password)) {
                
                if (con != null) {
                    System.out.println("------------------------------------------");
                    System.out.println("Connection established successfully!");
                    System.out.println("Connected to: " + con.getMetaData().getDatabaseProductName());
                    System.out.println("------------------------------------------");
                }
            } 
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found. Include the library in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed! Check IP, Database Name, or Credentials.");
            e.printStackTrace();
        }
    }
}
