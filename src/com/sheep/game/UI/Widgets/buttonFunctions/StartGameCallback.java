package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.base.IButtonClick;

public class StartGameCallback extends IButtonClick {
    @Override
    public void OnClick() {
        Game.StartGame();
    }
}
