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
    private int currentTotalTimeSec = 0;
    private int currMin = 0;
    private int currSec = 0;
    private boolean isStarted = false;
    private int oneThird = 0;
    private int two_thirds = 0;
    private TimerState ts = TimerState.WHOLE_TIME;

    public Timer() {
    }

    public Timer(int minutes, int seconds) {
        this.startSeconds = seconds;
        this.startMinutes = minutes;

        calculateAdditionTime();
        prepareTime();
    }

    private void prepareTime() {
        if (startSeconds > 60) {
            startMinutes = startSeconds / 60;
            startSeconds = startSeconds - (startMinutes * 60);
        }
    }

    private void calculateAdditionTime() {
        totalTimeSec = startMinutes * 60 + startSeconds;

        oneThird = (int) (totalTimeSec / 3);
        two_thirds = (int) (oneThird * 2);
    }

    private void calculateCurrentTotalTime() {
        currentTotalTimeSec = currMin * 60 + currSec;
    }

    public TimerState calculateFractionalTime() {
        calculateCurrentTotalTime();
        if (currentTotalTimeSec > two_thirds)
            ts = TimerState.WHOLE_TIME;
        else if (currentTotalTimeSec <= two_thirds && currentTotalTimeSec > oneThird)
            ts = TimerState.TWOTHIRD_OF_TIME;
        else
            ts = TimerState.ONE_THIRD_OF_TIME;

        return ts;
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

    public int getCurrentTotalTimeSec() {
        calculateCurrentTotalTime();
        return currentTotalTimeSec;
    }

    public TimerState getTimerState() {
        return ts;
    }

    public enum TimerState {
        ONE_THIRD_OF_TIME,
        TWOTHIRD_OF_TIME,
        WHOLE_TIME,
    }
}
