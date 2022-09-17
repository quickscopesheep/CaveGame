package com.sheep.game.entity;

import com.sheep.game.Game;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.input.Keyboard;

public class Door extends Entity{
    int level;
    boolean isStatic;

    public Door(float x, float y, Level level, boolean isStatic, int destinationLevel) {
        super(x, y, 16, 16, 0, 0, EntityType.DOOR, level);
        this.isStatic = isStatic;
        this.level = destinationLevel;
    }

    @Override
    public void tick() {
        if(collision(Game.player) && Keyboard.USE2  && !isStatic) Game.ChangeLevel(level);
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-8, (int)y-8, Sprite.door, false);

        if(collision(Game.player) && !isStatic) screen.renderText(Game.WIDTH/2 - (16*8)/2, Game.HEIGHT - 48, 0xeeeeee, "Press X To Enter");
    }
}
