package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.util.AudioPlayer;

public class IButton {

    public void OnClick(Widget widget, Game game){
        widget.getAudio().loadSound(AudioPlayer.SFX_CLICK);
        widget.getAudio().play();
    }

    public void OnHover(Widget widget, Game game){
        widget.getAudio().loadSound(AudioPlayer.SFX_HOVER);
        widget.getAudio().play();
    }
}
