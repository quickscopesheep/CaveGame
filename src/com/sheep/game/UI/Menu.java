package com.sheep.game.UI;

import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    List<Widget> widgets;

    public Menu(){
        widgets = new ArrayList<>();
    }

    protected abstract void constructMenu();

    protected void AddWidget(Widget widget){
        widgets.add(widget);
    }

    public void tick(){
        for (Widget w : widgets) {
            w.tick();
        }
    }

    public void render(Screen screen){
        for (Widget w : widgets) {
            w.render(screen);
        }
    }
}
