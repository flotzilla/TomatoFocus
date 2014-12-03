import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tomato Focus");
        this.primaryStage.setResizable(false);

        buildRootScene();
    }

    private void buildRootScene() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/rootScene.fxml"));
            GridPane rootlayout = (GridPane) loader.load();

            Scene rootScene = new Scene(rootlayout);
            primaryStage.setScene(rootScene);
            primaryStage.show();
            primaryStage.toFront();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
