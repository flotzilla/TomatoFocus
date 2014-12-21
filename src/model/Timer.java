package model;

/**
 * Created with IntelliJ IDEA.
 * User: 0byte
 * Debugging from
 * Date: 20.11.14
 * Time: 20:43
 */
public class Timer {
    private int startSeconds = 0;
    private int startMinutes = 0;
    private int totalTimeSec = 0;
    private int currMin = 0;
    private int currSec = 0;
    private boolean isStarted = false;

    public Timer() {
    }

    public Timer(int minutes, int seconds) {
        this.startSeconds = seconds;
        this.startMinutes = minutes;

        calculateTotalTime();
        prepareTime();
    }

    private void prepareTime() {
        if (startSeconds > 60) {
            startMinutes = startSeconds / 60;
            startSeconds = startSeconds - (startMinutes * 60);
        }
    }

    private void calculateTotalTime() {
        if (startSeconds != 0 && startMinutes != 0)
            totalTimeSec = startMinutes * 60 + startSeconds;
    }

    public int getTotalTimeSec() {
        return totalTimeSec;
    }

    public void setTotalTimeSec(int totalTimeSec) {
        this.totalTimeSec = totalTimeSec;
    }

    public int getStartSeconds() {
        return startSeconds;
    }

    public void setStartSeconds(int startSeconds) {
        this.startSeconds = startSeconds;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public void setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
    }

    public int getCurrSec() {
        return currSec;
    }

    public void setCurrSec(int currSec) {
        this.currSec = currSec;
    }

    public int getCurrMin() {
        return currMin;
    }

    public void setCurrMin(int currMin) {
        this.currMin = currMin;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }
}
