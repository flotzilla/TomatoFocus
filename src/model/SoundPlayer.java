package model;

import javafx.scene.media.AudioClip;

/**
 * Created by fanboy on 12/21/14.
 */
public class SoundPlayer {
    public static final String MEDIA_URL = "/main/resources/Doorbell.mp3";
    public static AudioClip AUDIO_PLAYER = null;

    public SoundPlayer() {
        if (AUDIO_PLAYER == null)
            AUDIO_PLAYER = new AudioClip(this.getClass().getResource(MEDIA_URL).toString());
    }

    public void play() {
        AUDIO_PLAYER.play();
    }
}
