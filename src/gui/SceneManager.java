package gui;

import be.Citizen;
import gui.controller.CreateNewController;
import gui.controller.NewCitizenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage primaryStage;
    private static String lastScene;

    public static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    /**
     * Sets the primary stage to show the login scene
     */
    public static void showLoginScene(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/Login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("FS3 Simulering");
            primaryStage.show();
            primaryStage.centerOnScreen();
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the primary stage to show the student scene
     */
    public static void showStudentScene(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/StudentView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            root.requestFocus();
            lastScene = "Student";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the primary stage to show the teacher scene
     */
    public static void showTeacherScene(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/TeacherView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            root.requestFocus();
            lastScene = "Teacher";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the primary stage to show the citizen overview scene
     */
    public static void showCitizenOverview(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/CitizenOverview.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns to the previous scene. Used to differentiate if the current user is a student or teacher
     */
    public static void goBack() {
        if (lastScene.equals("Student"))
            showStudentScene();
        else if (lastScene.equals("Teacher"))
            showTeacherScene();
    }

    /**
     * Opens a new window for creating a new citizen/editing the name of an existing one
     */
    public static void showNewCitizenWindow(Citizen citizen){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneManager.class.getResource("view/NewCitizenView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(primaryStage);
            stage.initModality(Modality.APPLICATION_MODAL);

            NewCitizenController controller = fxmlLoader.getController();
            controller.setEdit(citizen);

            root.requestFocus();
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new window for handling assignment of students to citizens
     */
    public static void showAssignStudentsWindow(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/AssignStudentsView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(primaryStage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new window for creating new users
     */
    public static void showNewUserWindow(int userType){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SceneManager.class.getResource("view/CreateNew.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(primaryStage);
            stage.initModality(Modality.APPLICATION_MODAL);

            CreateNewController controller = fxmlLoader.getController();
            controller.setType(userType);

            root.requestFocus();
            stage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the primary stage to show the admin scene
     */
    public static void showAdminScene(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/AdminView.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            root.requestFocus();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new window to show categories marked as not relevant
     */
    public static void showNotRelevantWindow(){
        try{
            Parent root = FXMLLoader.load(SceneManager.class.getResource("view/NotRelevantCats.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initOwner(primaryStage);
            stage.initModality(Modality.APPLICATION_MODAL);
            root.requestFocus();
            stage.showAndWait();

            showCitizenOverview();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
