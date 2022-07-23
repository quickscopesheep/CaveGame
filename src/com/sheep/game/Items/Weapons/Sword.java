package com.sheep.game.Items.Weapons;

import com.sheep.game.Items.Item;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class Sword extends Item {

    public Sword(Mob owner) {
        super(owner);
    }

    @Override
    public void use() {

    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
    }

    @Override
    public Sprite getIcon() {
        return null;
    }
}
