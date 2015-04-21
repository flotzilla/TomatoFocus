package utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Debugging from
 * Date: 21.11.14
 * Time: 18:15
 */
public class AlertDialog {
    public static void showDialog(String messageText, String title){
        showDialog(messageText, "", title);
    }

    public static void showDialog(String messageText) {
        showDialog(messageText, "", "");
    }

    public static void showDialog(String messageText, String title, String cssstyle) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        Label label = new Label(messageText);

        Button button = new Button("Ok");
        button.setOnAction(event -> {
            stage.close();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Tomato Focus");
            primaryStage.setResizable(false);

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AlertDialog.class.getResource("/view/rootScene.fxml"));
                GridPane rootlayout = (GridPane) loader.load();

                Scene rootScene = new Scene(rootlayout);
                primaryStage.setScene(rootScene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);

        if (!cssstyle.isEmpty()) {
            vBox.setStyle("-fx-background: #cedde8;");
        } else {
            vBox.setStyle(cssstyle);
        }

        Scene sc = new Scene(vBox, 250, 150);
        stage.setTitle(title);
        stage.setScene(sc);
        stage.show();
        stage.toFront();
        // AlertDialog with Stage.showAndWait Throws a IllegalStateException
        // showAndWait();
    }
}
