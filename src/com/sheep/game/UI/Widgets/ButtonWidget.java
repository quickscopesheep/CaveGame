package com.sheep.game.UI.Widgets;

import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;
import com.sheep.game.util.MouseButtonListener;

public class ButtonWidget extends Widget{
    String Label;
    IButton ButtonCallback;

    boolean hoverLastTick;

    public ButtonWidget(int x, int y, String Label, Menu parent, IButton OnClickCallback) {
        super(x, y, 0, 0, parent);
        this.Label = Label;
        this.w = Label.length() * 8 + 2;
        this.h = 10;
        this.ButtonCallback = OnClickCallback;
    }

    @Override
    public void tick() {
        super.tick();

        if(isHovering() && !hoverLastTick){
            OnHover();
            hoverLastTick = true;
        }else if(!isHovering()){
            hoverLastTick = false;
        }
    }

    public void OnHover(){
        ButtonCallback.OnHover(this);
    }

    @Override
    public void OnClick() {
        ButtonCallback.OnClick(this);
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        if(isHovering())
            screen.renderText(x - (w/2) + 1, y - (h/2) + 1, 0xffc800, Label);
        else
            screen.renderText(x - (w/2) + 1, y - (h/2) + 1 + 1, 0xffffff, Label);
    }
}
