package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.util.AudioManager;

public class LogoMenu extends Menu{
    int duration;

    public static LogoMenu menu = new LogoMenu();

    public LogoMenu(){
        constructMenu();

        duration = 75;
        Game.audioManager.loadSound(AudioManager.SFX_HOVER);
        Game.audioManager.play();
    }

    @Override
    protected void constructMenu() {
        this.AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2, this, "Made By Noah Whewall", 0xffffff));
    }

    @Override
    public void tick() {
        super.tick();
        duration --;
        if(duration == 0){
            Game.audioManager.loadSound(AudioManager.SFX_CLICK);
            Game.audioManager.play();
            Game.currentMenu = MainMenu.menu;
        }
    }
}
