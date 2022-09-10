package com.sheep.game.UI.Widgets.base;

import com.sheep.game.Game;
import com.sheep.game.gfx.Screen;

public class ButtonWidget extends Widget{
    String Label;
    IButtonClick OnClickCallback;

    public ButtonWidget(int x, int y, String Label, IButtonClick OnClickCallback) {
        super(x, y, 0, 0);
        this.Label = Label;
        this.w = Label.length() * 8 + 2;
        this.h = 10;
        this.OnClickCallback = OnClickCallback;
    }

    @Override
    public void OnClick() {
        OnClickCallback.OnClick();
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        if(isHovering())
            screen.renderText(x - (w/2) + 1, y - (h/2) +1, 0xffc800, Label);
        else
            screen.renderText(x - (w/2) + 1, y - (h/2) +1, 0xffffff, Label);
    }
}
