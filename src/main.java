import javafx.application.Application;
import javafx.stage.Stage;
import gui.SceneManager;

public class main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.showLoginScene();
    }
}
