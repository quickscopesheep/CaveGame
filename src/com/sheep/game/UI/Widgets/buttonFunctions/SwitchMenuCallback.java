package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.UI.Widgets.IButton;
import com.sheep.game.UI.Widgets.Widget;

public class SwitchMenuCallback extends IButton {
    public SwitchMenuCallback(String menu) {
        this.menu = menu;
    }

    String menu;

    @Override
    public void OnClick(Widget widget, Game game) {
        super.OnClick(widget,game);
        game.switchMenu(menu);
    }
}
