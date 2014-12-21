package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by fanboy on 12/21/14.
 */
public class SoundPlayer {
    public static final String MEDIA_URL = "/main/resources/Doorbell.mp3";
    public static Media MEDIA = null;
    public static MediaPlayer MEDIA_PLAYER = null;

    public SoundPlayer() {
        if (MEDIA == null && MEDIA_PLAYER == null) {
            MEDIA = new Media(this.getClass().getResource(MEDIA_URL).toString());
            MEDIA_PLAYER = new MediaPlayer(MEDIA);
        }
    }

    public void play() {
        if (MEDIA_PLAYER != null) {

            MediaPlayer.Status status = MEDIA_PLAYER.getStatus();

            if (status == MediaPlayer.Status.UNKNOWN  || status == MediaPlayer.Status.HALTED)
                return;

            if (status == MediaPlayer.Status.PAUSED ||
                    status == MediaPlayer.Status.READY ||
                    status == MediaPlayer.Status.STOPPED){
                MEDIA_PLAYER.seek(MEDIA_PLAYER.getStartTime());
                MEDIA_PLAYER.play();
            }

        }
    }

    public void stop() {
        if(MEDIA_PLAYER != null){
            MEDIA_PLAYER.stop();
        }
    }


}
