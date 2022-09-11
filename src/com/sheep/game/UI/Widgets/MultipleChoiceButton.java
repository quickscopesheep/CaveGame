package com.sheep.game.UI.Widgets;

import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;

public class MultipleChoiceButton extends ButtonWidget{
    String[] options;
    int index = 0;

    public MultipleChoiceButton(int x, int y, Menu parent, String[] options) {
        super(x, y,"", parent, new IButton());

        this.options = options;

        int longestOption = 0;
        for (String option : options) {
            if (option.length() > longestOption) longestOption = option.length();
        }

        this.w = longestOption*8+2;
        this.h = 10;
    }

    @Override
    public void OnClick() {
        super.OnClick();
        index++;
        if(index >= options.length) index = 0;
    }

    @Override
    public void render(Screen screen) {
        if(isHovering())
            screen.renderText(x - ((options[index].length()*8)/2) + 1, y - (h/2) + 1, 0xffc800, options[index]);
        else
            screen.renderText(x - ((options[index].length()*8)/2) + 1, y - (h/2) + 1 + 1, 0xffffff, options[index]);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
