/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package project;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dell1
 */
public class call extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlLocation = getClass().getResource("/project/frontpage.fxml");
        System.out.println("FXML URL: " + fxmlLocation);

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root);

        // Set up the stage
        primaryStage.setTitle("JavaFX FXML Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
