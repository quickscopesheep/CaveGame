package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.util.AudioManager;

public class IButton {

    public void OnClick(Widget widget){
        Game.audioManager.loadSound(AudioManager.SFX_CLICK);
        Game.audioManager.play();
    }

    public void OnHover(Widget widget){
        Game.audioManager.loadSound(AudioManager.SFX_HOVER);
        Game.audioManager.play();
    }
}
