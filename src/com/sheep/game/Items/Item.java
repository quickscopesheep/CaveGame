package com.sheep.game.Items;

import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class Item {
    protected Mob owner;

    float staminaUse;

    public Item(Mob owner){
        this.owner = owner;
        this.staminaUse = 0;
    }

    public Item(Mob owner, float staminaUse){
        this.owner = owner;
        this.staminaUse = staminaUse;
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
}
