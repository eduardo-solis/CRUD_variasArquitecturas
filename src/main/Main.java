/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author solis
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/UsuarioVista.fxml"));
            Pane ventana = (Pane) loader.load();
            
            Scene scene =  new Scene(ventana);
            stage.setScene(scene);
            stage.setTitle("CRUD Usuarios");
            stage.show();
            
        } catch (Exception e) {
            System.out.println("No jalo");
            e.printStackTrace();
        }
        
        
        /*
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
