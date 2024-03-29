package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.IButton;
import com.sheep.game.UI.Widgets.Widget;

public class RestartGameCallback extends IButton {
    @Override
    public void OnClick(Widget widget, Game game) {
        super.OnClick(widget, game);
        game.respawnPlayer();
    }
}
