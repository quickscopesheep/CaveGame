package com.sheep.game.UI.Widgets;

import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;

public class TextWidget extends Widget {
    String label;

    public TextWidget(int x, int y, Menu parent, String label) {
        super(x, y, 0, 0, parent);
        this.label = label;
        this.w = label.length()*8+2;
        this.h = 10;
    }

    @Override
    public void OnClick() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderText(x - (w/2), y - (h/2), 0xffffff, label);
    }
}