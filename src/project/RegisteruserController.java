/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;


/**
 * FXML Controller class
 *
 * @author dell1
 */
public class RegisteruserController implements Initializable {
    @FXML
     private ComboBox<Integer> year;
    @FXML
     private ComboBox<Integer> month;
    

    @FXML
    private TextField Name;
    @FXML
    private TextField Emal;
    @FXML
    private TextField Phone;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField address;
    @FXML
    private Label entername;
    @FXML
    private Label enteremail;
    @FXML
    private Label enterphoneno;
    @FXML
    private Label enterpassword;
    @FXML
    private Label enteraddress;
    @FXML
    private Label confirmpassword;
    @FXML
    private Button Register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        year.setItems(FXCollections.observableArrayList(
            2022,2023, 2024
        ));
        month.setItems(FXCollections.observableArrayList(
            1,2,3,4,5,6,7,8,9,10,11,12
        ));
    }    

    @FXML
    private void registerbutton(MouseEvent event) {
    String susername = Name.getText();
    String spassword = password.getText();
    String email = Emal.getText();
    String phone = Phone.getText();
    Integer joinYear = year.getValue();
    Integer joinMonth = month.getValue();
    if (susername.isEmpty() || spassword.isEmpty() || email.isEmpty() || phone.isEmpty()|| joinYear == null || joinMonth == null) {
        showAlert(AlertType.ERROR, "Form Error!", "Please fill all the fields.");
        return;
    }

    if (phone.length() != 11) {
        showAlert(AlertType.ERROR, "Form Error!", "Phone number must be 11 characters long.");
        return;
    }
    if (spassword.length() < 5) {
        showAlert(AlertType.ERROR, "Form Error!", "Password must be at least 5 characters long.");
        return;
    }
    try 
    {
        database db=new database();
        db.registerUser("8040", susername, email, spassword, phone, joinYear, joinMonth);
    } catch (Exception e) {
        e.printStackTrace();
    }
    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/FXML.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) Register.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException  e) {
            e.printStackTrace();
        } 
       
    }
    private void showAlert(AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
}
