package com.sheep.game.Items;

import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class Item {
    protected Mob owner;

    float cooldown;

    public Item(Mob owner){
        this.owner = owner;
        this.cooldown = cooldown;
    }

    public Item(Mob owner, float cooldown){
        this.owner = owner;
        this.cooldown = cooldown;
    }
    public void render(Screen screen){

    }

    public void use(){

    }

    public Sprite getIcon(){
        return null;
    }

    public Mob getOwner() {
        return owner;
    }

    public float getCooldown() {
        return cooldown;
    }
}
