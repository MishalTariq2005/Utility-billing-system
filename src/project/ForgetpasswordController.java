/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author dell1
 */
public class ForgetpasswordController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField newpassword;
    @FXML
    private PasswordField newpasswordagain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updatebutton(MouseEvent event) {
        String email = Email.getText();
        String newPassword = newpassword.getText();
        String confirmPassword = newpasswordagain.getText();
        if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        if (verifyEmail(email)) {
            if (updatePassword(email, newPassword)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password. Please try again.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Email not found.");
        }
    
    }
    private boolean verifyEmail(String email) {
        String query = "SELECT COUNT(*) FROM Register WHERE email = ?";
        try {database db = new database();
             Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     private boolean updatePassword(String email, String newPassword) {
        String query = "UPDATE Register SET password = ? WHERE email = ?";
        try {
            database db = new database();
            Connection connection = db.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, hashPassword(newPassword));
            pstmt.setString(2, email);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
