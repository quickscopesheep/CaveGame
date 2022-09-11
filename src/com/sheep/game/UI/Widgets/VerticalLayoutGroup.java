package com.sheep.game.UI.Widgets;

import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public class VerticalLayoutGroup extends Widget{
    List<Widget> widgets;

    int spacing;

    public VerticalLayoutGroup(int x, int y, Menu parent, int spacing) {
        super(x, y, 0, 0, parent);
        widgets = new ArrayList<>();
    }

    public void AddWidget(Widget widget){
        widgets.add(widget);
        updateWidgetPositions();
    }

    public void RemoveWidget(Widget widget){
        widgets.remove(widget);
        updateWidgetPositions();
    }

    void updateWidgetPositions(){
        int listPixelHeight = 0;
        for(Widget widget : widgets){
            listPixelHeight += widget.getH();
        }

        int listPixelHeightAccum = 0;

        for(int i = 0; i < widgets.size(); i++){
            Widget widget = widgets.get(i);
            widget.x = this.x;
            widget.y = this.y - listPixelHeight/2 + listPixelHeightAccum;
            listPixelHeightAccum += widget.getH() + spacing;
        }
    }

    @Override
    public void tick() {
        for (Widget widget : widgets) {
            widget.tick();
        }
    }

    @Override
    public void render(Screen screen) {
        for (Widget widget : widgets) {
            widget.render(screen);
        }
    }

    @Override
    public void OnClick() {

    }
}
