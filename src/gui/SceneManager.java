package gui;

import gui.controller.CitizenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    public static void showLoginScene(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/Login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void showStudentScene(){ //Student parameter?
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/StudentView.fxml"));
            primaryStage.setScene(new Scene(root));
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}