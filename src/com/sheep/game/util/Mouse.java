package com.sheep.game.util;

import com.sheep.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
    private static int mouseX, mouseY;
    private static int button = -1;

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
