package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;
import com.sheep.game.util.input.Mouse;
import com.sheep.game.util.input.MouseButtonListener;

public abstract class Widget implements MouseButtonListener {
    int x, y, w, h;

    Menu parent;

    boolean drawBackground;
    int backgroundColour;

    boolean drawBorder;
    int borderColour;

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
        if(Game.currentMenu != parent && parent != null) return;

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
        if(drawBackground || drawBorder) {
            for (int y = this.y - (h / 2); y < this.y + (h / 2); y++) {
                for (int x = this.x - (w / 2); x < this.x + (w / 2); x++) {
                    if(drawBackground)
                        screen.pixels[y * screen.getWidth() + x] = backgroundColour;
                    if(drawBorder)
                        if (x == this.x + (w / 2) - 1 || x == this.x - (w / 2) || y == this.y - (h / 2) || y == this.y + (h / 2) - 1) {
                            screen.pixels[y * screen.getWidth() + x] = borderColour;
                        }
                }
            }
        }
    }

    public void setBackground(boolean drawBackground, int backgroundColour){
        this.drawBackground = drawBackground;
        this.backgroundColour = backgroundColour;
    }

    public void setBorder(boolean drawBorder, int borderColour){
        this.drawBorder = drawBorder;
        this.borderColour = borderColour;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }
}
