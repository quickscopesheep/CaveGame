package com.sheep.game.util;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioManager {
    public static String SFX_HOVER = "/sound/hover.wav";
    public static String SFX_CLICK = "/sound/click.wav";
    public static String SFX_FOOTSTEP = "/sound/footstep.wav";

    private Clip clip;

    public void loadSound(String path) {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(AudioManager.class.getResource(path));
            clip = AudioSystem.getClip();
            clip.open(stream);
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
