package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.UI.Widgets.IButton;
import com.sheep.game.UI.Widgets.Widget;

public class SwitchMenuCallback extends IButton {
    public SwitchMenuCallback(Menu menu) {
        this.menu = menu;
    }

    Menu menu;

    @Override
    public void OnClick(Widget widget) {
        super.OnClick(widget);
        Game.currentMenu = menu;
    }
}
