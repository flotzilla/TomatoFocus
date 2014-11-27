package utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public static void showDialog(String messageText, String cssstyle, String title) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        Label label = new Label(messageText);

        Button button = new Button("Ok");
        button.setOnAction(e -> stage.close());

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);

        if (!cssstyle.isEmpty()) {
            vBox.setStyle("-fx-background: #FFFFFF;");
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
