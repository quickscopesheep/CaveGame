package com.sheep.game.items;

import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Sprite;

public abstract class Item {
    String name;
    Sprite icon;
    public Item(String name){

    }

    abstract void use(Mob owner);
}
