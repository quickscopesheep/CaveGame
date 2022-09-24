package com.sheep.game.entity;

import com.sheep.game.Game;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.input.Keyboard;
import com.sheep.game.util.input.KeyboardButtonListener;

import java.awt.event.KeyEvent;

public class Door extends Entity implements KeyboardButtonListener {
    int level;
    boolean isStatic;
    public Door(float x, float y, Level level, int destinationLevel, boolean isStatic) {
        super(x, y, 16, 16, 0, 0, EntityType.DOOR, level);
        this.level = destinationLevel;
        this.isStatic = isStatic;

        Keyboard.AddListener(this);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-8, (int)y-8, Sprite.door, false);

        if(collision(Game.player) && !isStatic) screen.renderText(Game.WIDTH/2 - (16*8)/2, Game.HEIGHT - 48, 0xeeeeee, "Press X To Enter");
    }

    @Override
    public void KeyDown(int button) {
        if(collision(Game.player) && button == KeyEvent.VK_X && !isStatic)
            Game.ChangeLevel(level);
    }
}
