package com.sheep.game.UI.Widgets.base;

import com.sheep.game.gfx.Screen;
import com.sheep.game.util.Mouse;

public abstract class Widget {
    int x, y, w, h;
    String name;

    public Widget(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void OnClick();

    public void tick(){
        if(Mouse.getButton() == 1){
            int actualX = x - (w/2);
            int actualY = y - (h/2);

            if(Mouse.getMouseX() > actualX && Mouse.getMouseX() < actualX+w
            && Mouse.getMouseY() > actualY && Mouse.getMouseY() < actualY+h){
                OnClick();
            }
        }
    }

    public boolean isHovering(){
        int actualX = x - (w/2);
        int actualY = y - (h/2);
        return Mouse.getMouseX() > actualX && Mouse.getMouseX() < actualX+w
                && Mouse.getMouseY() > actualY && Mouse.getMouseY() < actualY+h;
    }

    public void render(Screen screen){

    }

    public String getName(){
        return name;
    }
}
