package com.sheep.game.util;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioPlayer {
    public static String SFX_HOVER = "/sound/hover.wav";
    public static String SFX_CLICK = "/sound/click.wav";
    public static String SFX_PICKUP = "/sound/pickup.wav";

    public static String SFX_ROCK_HIT_1 = "/sound/rockhit1.wav";
    public static String SFX_ROCK_HIT_2 = "/sound/rockhit2.wav";
    public static String SFX_ROCK_DESTROY = "/sound/rockdestroy.wav";
    public static String SFX_TOOL_SWING = "/sound/toolswing1.wav";
    public static String SFX_SWORD_SWING = "/sound/swordswing.wav";
    public static String SFX_MOB_HIT = "/sound/damage1.wav";

    public static String AMBIENCE_CAVE_1 = "/sound/caveambience1.wav";
    public static String AMBIENCE_CAVE_2 = "/sound/caveambience2.wav";
    public static String AMBIENCE_CAVE_3 = "/sound/caveambience3.wav";

    private Clip clip;

    public void loadSound(String path) {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(path));
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
