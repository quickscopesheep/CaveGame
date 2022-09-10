package com.sheep.game.UI.Widgets;

import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;
import com.sheep.game.util.Mouse;
import com.sheep.game.util.MouseButtonListener;

public abstract class Widget implements MouseButtonListener {
    int x, y, w, h;
    String name;

    Menu parent;

    public Widget(int x, int y, int w, int h, Menu parent){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.parent = parent;

        Mouse.AddListener(this);
    }

    @Override
    public void MouseButtonDown(int button) {
        int actualX = x - (w/2);
        int actualY = y - (h/2);

        if(Mouse.getMouseX() > actualX && Mouse.getMouseX() < actualX+w
                && Mouse.getMouseY() > actualY && Mouse.getMouseY() < actualY+h){
            OnClick();
        }
    }

    public abstract void OnClick();

    public void tick(){
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

    public Menu getParent() {
        return parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
