package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.Game;
import com.sheep.game.UI.StartGameMenu;
import com.sheep.game.UI.Widgets.IButton;
import com.sheep.game.UI.Widgets.Widget;

public class StartGameCallback extends IButton {
    @Override
    public void OnClick(Widget widget) {
        super.OnClick(widget);
        Game.StartGame(((StartGameMenu)widget.getParent()).makeGameSettings());
    }

    @Override
    public void OnHover(Widget widget) {
        super.OnHover(widget);
    }
}
