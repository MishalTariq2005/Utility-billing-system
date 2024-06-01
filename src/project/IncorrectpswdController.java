/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell1
 */
public class IncorrectpswdController implements Initializable {

    @FXML
    private TextField ReEnterUsername;
    @FXML
    private Button login;
    @FXML
    private PasswordField ReEnterPassword;
    @FXML
    private Hyperlink signup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginbutton(MouseEvent event) {
        String username = ReEnterUsername.getText();
        String password = ReEnterPassword.getText();
        if (username.isEmpty() || password.isEmpty()) {
        // Show alert if either username or password is empty
        Alert alert = new Alert(Alert.AlertType.ERROR, "Username and password cannot be empty.", ButtonType.OK);
        alert.showAndWait();
    } else {

         if (authenticate(username, password)) {
            System.out.println("Login successful!");
            // Redirect to another scene or perform other actions
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/userhome.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } 
        } else {
            System.out.println("Invalid username or password.");
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/incorrectpswd.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } 
         
    }}}
    private boolean authenticate(String username, String password) {
        String query = "SELECT * FROM Register WHERE name = ? AND password = ?";
        try 
        {
            database db = new database();
             Connection connection = db.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query); 

            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
}}
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

    @FXML
    private void signuplink(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/registeruser.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) signup.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}
