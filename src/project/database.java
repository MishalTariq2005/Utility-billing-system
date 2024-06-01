/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.time.YearMonth;
import java.util.Random;
public class database {
    private Connection connection;
    private Statement statement;
    database()
            {
             try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill_system", "root", "mishal");
            } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            }
             }
    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void registerUser(String meterNo, String name, String email, String password, String phone, int joinYear, int joinMonth) {
         String registerQuery = "INSERT INTO Register (meter_no, name, email, password, phoneno, join_year, join_month) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(registerQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, meterNo);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, hashPassword(password));
            pstmt.setString(5, phone);
            pstmt.setInt(6, joinYear );
            pstmt.setInt(7, joinMonth);
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int registerId = generatedKeys.getInt(1);
                generateRandomUnits(registerId, name, phone, joinYear, joinMonth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void generateRandomUnits(int registerId, String username, String phone, int joinYear, int joinMonth) {
        YearMonth startMonth = YearMonth.of(joinYear, joinMonth);
        YearMonth currentMonth = YearMonth.now();
        Random random = new Random();

        while (!startMonth.isAfter(currentMonth)) {
            int unitsConsumed = random.nextInt(500) + 1; // Random units between 1 and 500
            double billAmount = unitsConsumed * 0.12; // Assuming $0.12 per unit
            String insertQuery = "INSERT INTO electricity (register_id, username, phoneno, units_consumed, bill_amount, billing_year, billing_month, bill_paid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                pstmt.setInt(1, registerId);
                pstmt.setString(2, username);
                pstmt.setString(3, phone);
                pstmt.setInt(4, unitsConsumed);
                pstmt.setDouble(5, billAmount);
                pstmt.setInt(6, startMonth.getYear());
                pstmt.setInt(7, startMonth.getMonthValue());
                pstmt.setBoolean(8, false); // Assuming the bill is not paid initially
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            startMonth = startMonth.plusMonths(1);
        }
    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    
    
    
}
