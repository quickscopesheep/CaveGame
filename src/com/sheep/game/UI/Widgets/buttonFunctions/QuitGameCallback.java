package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.UI.Widgets.base.IButtonClick;

public class QuitGameCallback extends IButtonClick {
    @Override
    public void OnClick() {
        System.exit(0);
    }
}
