/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell1
 */
public class UserhomeController implements Initializable {

    @FXML
    private Button electricity;
    @FXML
    private Button gas;
    @FXML
    private Button wifi;
    @FXML
    private Button tele;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ElectricityButton(MouseEvent event) {
    }

    @FXML
    private void gasbutton(MouseEvent event) {
    }

    @FXML
    private void InternetButton(MouseEvent event) {
    }

    @FXML
    private void logoutbutton(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/FXML.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}
