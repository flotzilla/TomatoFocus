package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Timer;
import utils.AlertDialog;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Debugging from
 * Date: 21.11.14
 * Time: 21:15
 */
public class TimeController {
    @FXML
    public Text minute;
    @FXML
    public Text second;
    @FXML
    public Text dots;
    @FXML
    public AnchorPane acPanel;
    @FXML
    public Button stopButton;

    private Timer timer;
    private Timeline mainTimeLine;
    private Timeline dotsTimeLine;

    public void initData(int min, int sec) {
        timer = new Timer(min, sec);
        minute.setText(digitsPreparation(String.valueOf(timer.getStartMinutes())));
        second.setText(digitsPreparation(String.valueOf(timer.getStartSeconds())));

        Stage s = (Stage) acPanel.getScene().getWindow();
        s.setTitle("Ticking out");
        s.setOnHiding(new onHideEvent());

        startTimer();
        mainTimeLine.play();
        dotsTimeLine.play();
    }

    private String digitsPreparation(String digit) {
        if (!digit.startsWith("0") && digit.length() < 2) {
            return "0" + digit;
        } else if (digit.equals("0")) {
            return "00";
        } else return digit;
    }

    private void startTimer() {
        mainTimeLine = new Timeline(new KeyFrame(
                Duration.seconds(1),
                ae -> handleTimeLineAnimation()));
        mainTimeLine.setCycleCount(Animation.INDEFINITE);

        dotsTimeLine = new Timeline(new KeyFrame(
                Duration.seconds(0.5),
                ae -> handleDotsAnimation()
        ));
        dotsTimeLine.setCycleCount(Animation.INDEFINITE);

        timer.setCurrMin(timer.getStartMinutes());
        timer.setCurrSec(timer.getStartSeconds());
        timer.setStarted(true);
    }

    private void handleDotsAnimation() {
        if (timer.isStarted()) {
            if (!dots.isVisible()) {
                dots.setVisible(true);
            } else {
                dots.setVisible(false);
            }
        }

        mainTimeLine.play();
    }

    private void handleTimeLineAnimation() {
        if (!dots.isVisible()) {
            dots.setVisible(true);
        } else {
            dots.setVisible(false);
        }

        //for debug
        System.out.println("curr min: " + timer.getCurrMin()
                + " curr sec: " + timer.getCurrSec()
                + " totalMin: " + timer.getStartMinutes()
                + " totalSec: " + timer.getStartSeconds()
                + " timerTextMin " + minute.getText()
                + " timerTxtSec: " + second.getText());

        if (timer.isStarted()) {
            if (timer.getCurrMin() == 0 && timer.getCurrSec() == 0) {
                stopTimerAction();
                alertAction();
                return;
            } else if (timer.getCurrSec() == 0) {
                timer.setCurrSec(60);
                timer.setCurrMin(timer.getCurrMin() - 1);
            }
            timer.setCurrSec(timer.getCurrSec() - 1);

            //draw a zero before a single number
            if (timer.getCurrMin() < 10) {
                minute.setText("0" + Integer.toString(timer.getCurrMin()));
            } else {
                minute.setText(Integer.toString(timer.getCurrMin()));
            }
            if (timer.getCurrSec() < 10) {
                second.setText("0" + Integer.toString(timer.getCurrSec()));
            } else {
                second.setText(Integer.toString(timer.getCurrSec()));
            }

        }
    }

    private void alertAction() {
        System.out.println("ding dong");
        AlertDialog.showDialog("Alert!", "Alarm!");
    }

    private void stopTimerAction() {
        timer.setStarted(false);
        mainTimeLine.stop();
        dotsTimeLine.stop();
        Stage window = (Stage) acPanel.getScene().getWindow();
        window.close();
    }

    public void pauseButtAction(ActionEvent e) {
        if (mainTimeLine != null && dotsTimeLine != null) {
            if (mainTimeLine.getStatus() == Animation.Status.RUNNING) {
                mainTimeLine.pause();
                dotsTimeLine.pause();
                stopButton.setText("Play");
                timer.setStarted(false);
            } else if (mainTimeLine.getStatus() == Animation.Status.PAUSED) {
                mainTimeLine.play();
                dotsTimeLine.play();
                stopButton.setText("Pause");
                timer.setStarted(true);
            }
        }
    }

    private class onHideEvent implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event) {
            if (timer.isStarted()) stopTimerAction();

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Tomato Focus");
            primaryStage.setResizable(false);

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/rootScene.fxml"));
                GridPane rootlayout = (GridPane) loader.load();

                Scene rootScene = new Scene(rootlayout);
                primaryStage.setScene(rootScene);
                primaryStage.show();
                primaryStage.toBack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
