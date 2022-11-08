package com.sheep.game.Items;

import com.sheep.game.Game;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class Item {
    protected Mob owner;

    protected float cooldown;
    protected float coolDownVariation;
    protected float staminaUse;

    protected Game game;

    public Item(Mob owner, Game game){
        this.owner = owner;
        this.cooldown = 0;
        this.staminaUse = 0;
        this.game = game;
    }

    public Item(Mob owner, float cooldown, float coolDownVariation, float staminaUse, Game game){
        this.owner = owner;
        this.cooldown = cooldown;
        this.coolDownVariation = coolDownVariation;
        this.staminaUse = staminaUse;
        this.game = game;
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

    public float getCoolDownVariation() {
        return coolDownVariation;
    }

    public float getStaminaUse() {
        return staminaUse;
    }

    public void setOwner(Mob owner) {
        this.owner = owner;
    }

    public String getItemName(){
        return "item";
    }
}
