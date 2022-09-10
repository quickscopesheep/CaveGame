package com.sheep.game.UI.Widgets.buttonFunctions;

import com.sheep.game.UI.Widgets.IButton;
import com.sheep.game.UI.Widgets.Widget;

public class QuitGameCallback extends IButton {
    @Override
    public void OnClick(Widget widget) {
        super.OnClick(widget);
        System.exit(0);
    }

    @Override
    public void OnHover(Widget widget) {
        super.OnHover(widget);
    }
}
