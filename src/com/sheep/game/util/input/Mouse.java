package com.sheep.game.util.input;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.Widget;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener, MouseMotionListener {
    private static int mouseX, mouseY;
    private static int button = -1;

    private static List<Widget> listeners = new ArrayList<Widget>();

    public static void AddListener(Widget listener){
        listeners.add(listener);
    }

    public static void RemoveListener(Widget listener){
        listeners.remove(listener);
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static int getButton() {
        return button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < listeners.size(); i++) {
            Widget widget = listeners.get(i);
            if(widget.getParent().game.currentMenu == widget.getParent())
                widget.MouseButtonDown(e.getButton());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX() / Game.SCALE;
        mouseY = e.getY() / Game.SCALE;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() / Game.SCALE;
        mouseY = e.getY() / Game.SCALE;
    }
}
