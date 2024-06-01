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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell1
 */
public class frontpagecontoller implements Initializable {

    @FXML
    private Label button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchtologin(MouseEvent event) {
         try {
            URL fxmlLocation = getClass().getResource("/project/FXML.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Parent root = fxmlLoader.load();

            // Option 1: Replace the current scene
            // Get the current stage
            Stage stage = (Stage) button.getScene().getWindow();
            // Set the new scene
            stage.setScene(new Scene(root));

            // Option 2: Open in a new window
            // Stage stage = new Stage();
            // stage.setScene(new Scene(root));
            // stage.setTitle("Login Page");
            // stage.show();
           
       

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
