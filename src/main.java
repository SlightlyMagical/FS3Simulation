import gui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.showLoginScene();
    }
}
