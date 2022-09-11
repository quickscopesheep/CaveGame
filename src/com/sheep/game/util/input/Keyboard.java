package com.sheep.game.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public static boolean UP, DOWN, LEFT, RIGHT, USE1, USE2, USE3;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> UP = true;
            case KeyEvent.VK_DOWN -> DOWN = true;
            case KeyEvent.VK_LEFT -> LEFT = true;
            case KeyEvent.VK_RIGHT -> RIGHT = true;
            case KeyEvent.VK_Z -> USE1 = true;
            case KeyEvent.VK_X -> USE2 = true;
            case KeyEvent.VK_C -> USE3 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> UP = false;
            case KeyEvent.VK_DOWN -> DOWN = false;
            case KeyEvent.VK_LEFT -> LEFT = false;
            case KeyEvent.VK_RIGHT -> RIGHT = false;
            case KeyEvent.VK_Z -> USE1 = false;
            case KeyEvent.VK_X -> USE2 = false;
            case KeyEvent.VK_C -> USE3 = false;
        }
    }
}
