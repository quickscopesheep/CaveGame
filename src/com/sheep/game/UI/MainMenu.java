package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.UI.Widgets.VerticalLayoutGroup;
import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.UI.Widgets.buttonFunctions.QuitGameCallback;
import com.sheep.game.UI.Widgets.buttonFunctions.StartGameCallback;
import com.sheep.game.UI.Widgets.buttonFunctions.SwitchMenuCallback;
import com.sheep.game.gfx.Screen;

public class MainMenu extends Menu{

    public static MainMenu menu = new MainMenu();

    public MainMenu() {
        super();
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 32, this, "Untitled"));

        VerticalLayoutGroup group;
        AddWidget(group = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2));

        group.AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 - 5, "Start", this, new SwitchMenuCallback(StartGameMenu.menu)));
        group.AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 + 5 , "Quit", this, new QuitGameCallback()));
    }

    @Override
    public void render(Screen screen) {
        for (Widget w : widgets) {
            w.render(screen);
        }
    }
}
