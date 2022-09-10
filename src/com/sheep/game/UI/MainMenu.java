package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.base.ButtonWidget;
import com.sheep.game.UI.Widgets.base.TextWidget;
import com.sheep.game.UI.Widgets.buttonFunctions.QuitGameCallback;
import com.sheep.game.UI.Widgets.buttonFunctions.StartGameCallback;

public class MainMenu extends Menu{

    public MainMenu() {
        super();
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 32, "Cave Game"));
        AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 - 8, "Start", new StartGameCallback()));
        AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 + 2 , "Quit", new QuitGameCallback()));
    }
}
