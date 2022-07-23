package com.sheep.game.entity.mob;

import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public class Chest extends Mob{
    boolean open;

    public Chest(float x, float y, Level level) {
        super(x, y, 16, 16, 9999, EntityType.CHEST, level);
        health = 9999;
        open = false;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-8, (int)y-8, open ? Sprite.chest_open : Sprite.chest_closed, false);
    }
}
