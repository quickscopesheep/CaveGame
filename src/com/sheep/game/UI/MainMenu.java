package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.UI.Widgets.buttonFunctions.QuitGameCallback;
import com.sheep.game.UI.Widgets.buttonFunctions.StartGameCallback;
import com.sheep.game.gfx.Screen;

public class MainMenu extends Menu{

    public MainMenu() {
        super();
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 32, this, "Untitled"));

        AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 - 5, "Start", this, new StartGameCallback()));
        AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 + 5 , "Quit", this, new QuitGameCallback()));
    }

    @Override
    public void render(Screen screen) {
        for (Widget w : widgets) {
            w.render(screen);
        }
    }
}
