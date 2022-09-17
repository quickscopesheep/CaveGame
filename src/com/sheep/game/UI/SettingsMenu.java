package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.UI.Widgets.VerticalLayoutGroup;

public class SettingsMenu extends Menu{
    public static SettingsMenu menu = new SettingsMenu();

    public SettingsMenu(){
        super();
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        VerticalLayoutGroup layout;
        AddWidget(layout = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2, 2));

    }
}
