package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.AlertDialog;

import java.io.IOException;

public class RootController {

    @FXML
    public TextField minField;

    @FXML
    public TextField secField;

    @FXML
    public Button startButton;

    @FXML
    public void startTimerEventHandler(ActionEvent e) {
        try {
            String minFieldText = minField.getText();
            String secFieldText = secField.getText();
            if (!minFieldText.equals("") && !secFieldText.equals("")) {
                if (minFieldText.matches("\\d+") && secFieldText.matches("\\d+")) {
                    int min = Integer.parseInt(minFieldText);
                    int sec = Integer.parseInt(secFieldText);
                    ((Node) e.getSource()).getScene().getWindow().hide();

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("TimeScene.fxml"));

                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setAlwaysOnTop(true);
                        stage.setResizable(false);
                        stage.setScene(new Scene((AnchorPane) loader.load()));

                        TimeController controller = loader.<TimeController>getController();
                        controller.initData(min, sec);

                        stage.show();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    AlertDialog.showDialog("Values must be numeric");
                    minField.setText("");
                    secField.setText("");
                }
            } else {
                AlertDialog.showDialog("Time fields are empty");
            }
        } catch (NumberFormatException er) {
            er.printStackTrace();
        }
    }

    public void closeAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void aboutWindowAction(ActionEvent actionEvent) {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("about.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setResizable(false);
            stage.setTitle("About TomatoFocus");
            stage.setScene(new Scene((AnchorPane) l.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
