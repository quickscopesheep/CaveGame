package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.gfx.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Menu {
    List<Widget> widgets;

    public Game game;

    public Menu(Game game){
        widgets = new ArrayList<>();
        this.game = game;
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
