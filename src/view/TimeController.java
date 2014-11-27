package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Timer;
import utils.AlertDialog;

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

    public void initData(int min, int sec) {
        timer = new Timer(min, sec);
        minute.setText(String.valueOf(timer.getStartMinutes()));
        second.setText(String.valueOf(timer.getStartSeconds()));
        startTimer();
    }

    private void startTimer() {
        mainTimeLine = new Timeline(new KeyFrame(
                Duration.seconds(1),
                ae -> handleTimeLine()));
        mainTimeLine.setCycleCount(Animation.INDEFINITE);
        timer.setStarted(true);
        timer.setCurrMin(timer.getStartMinutes());
        timer.setCurrSec(timer.getStartSeconds());
        mainTimeLine.play();
    }

    private void handleTimeLine() {
        if (!dots.isVisible()) {
            dots.setVisible(true);
        } else {
            dots.setVisible(false);
        }

        System.out.println("curr min: " + timer.getCurrMin()
                + " curr sec: " + timer.getCurrSec()
                + " totalMin: " + timer.getStartMinutes()
                + " totalSec: " + timer.getStartSeconds()
                + " timerTextMin " + minute.getText()
                + " timerTxtSec: " + second.getText());

        if (timer.isStarted()) {
            if (timer.getCurrMin() == 0 && timer.getCurrSec() == 0) {
                System.out.println("ding dong");
                timer.setStarted(false);
                mainTimeLine.stop();
                AlertDialog.showDialog("Alert!");
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

    public void pauseButtAction(ActionEvent e) {
        if (mainTimeLine != null) {
            if(mainTimeLine.getStatus() == Animation.Status.RUNNING){
                mainTimeLine.pause();
                stopButton.setText("Play");
                timer.setStarted(false);
            }else if (mainTimeLine.getStatus() == Animation.Status.PAUSED){
                mainTimeLine.play();
                stopButton.setText("Pause");
                timer.setStarted(true);
            }
        }
    }

}
