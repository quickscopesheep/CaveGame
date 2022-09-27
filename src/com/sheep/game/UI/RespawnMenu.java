package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.IButton;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.UI.Widgets.VerticalLayoutGroup;
import com.sheep.game.UI.Widgets.buttonFunctions.RestartGameCallback;
import com.sheep.game.UI.Widgets.buttonFunctions.SwitchMenuCallback;
import com.sun.tools.javac.Main;

public class RespawnMenu extends Menu{
    public static RespawnMenu menu = new RespawnMenu();

    VerticalLayoutGroup buttonGroup;

    public RespawnMenu(){
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 32, this, "You Died", 0xffffff));

        AddWidget(buttonGroup = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 1, 0));
        buttonGroup.AddWidget(new ButtonWidget(0, 0, "Restart", this, new RestartGameCallback()));
        buttonGroup.AddWidget(new ButtonWidget(0, 0, "Main Menu", this, new SwitchMenuCallback(MainMenu.menu)));
    }
}
