package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public class VerticalLayoutGroup extends Widget{
    List<Widget> widgets;

    int spacing;
    int padding;

    public VerticalLayoutGroup(int x, int y, Menu parent, int spacing, int padding, Game game) {
        super(x, y, 0, 0, parent, game);
        widgets = new ArrayList<>();
        this.spacing = spacing;
        this.padding = padding;
    }

    public void AddWidget(Widget widget){
        widgets.add(widget);
        updateWidgetPositions();
    }

    public void RemoveWidget(Widget widget){
        widgets.remove(widget);
        updateWidgetPositions();
    }

    public void clearWidgets(){
        for (int i = 0; i < widgets.size(); i++) {
            RemoveWidget(widgets.get(i));
        }
    }

    void updateWidgetPositions(){
        int listPixelHeight = 0;
        for(Widget widget : widgets){
            listPixelHeight += widget.getH();
        }

        int listPixelHeightAccum = 0;

        int widest = 0;
        for(int i = 0; i < widgets.size(); i++){
            Widget widget = widgets.get(i);
            widget.x = this.x;
            widget.y = this.y - listPixelHeight/2 + listPixelHeightAccum + padding;
            listPixelHeightAccum += widget.getH() + spacing;

            if(widget.w > widest) widest = widget.w;
        }

        w = widest + padding*2;
        h = listPixelHeightAccum + padding*2;
    }

    @Override
    public void tick() {
        for (Widget widget : widgets) {
            widget.tick();
        }
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        for (Widget widget : widgets) {
            widget.render(screen);
        }
    }

    @Override
    public void OnClick(Game game) {

    }

    public List<Widget> getWidgets() {
        return widgets;
    }
}
