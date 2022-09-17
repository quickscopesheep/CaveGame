package com.sheep.game.Items;

import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

import java.util.Random;

public class medkit extends Item{
    Random random;

    public medkit(Mob owner) {
        super(owner);
        random = new Random();
    }

    @Override
    public void use() {
        applyEffects();

        owner.equip(null);
        if(owner.getType() == EntityType.PLAYER){
            ((Player)owner).getItems()[((Player)owner).getCurrentItem()] = null;
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)((owner.getX()-8 + owner.getDirX()*8)), (int)((owner.getY()-8+2)), Sprite.item_medkit, false);
    }

    protected void applyEffects(){
        float hpBuff = random.nextInt(10, 25);
        owner.setHealth(owner.getHealth() + hpBuff);

        if(owner.getHealth() > owner.getMaxHealth()){
            owner.setHealth(owner.getMaxHealth());
        }
    }

    @Override
    public Sprite getIcon() {
        return Sprite.item_medkit;
    }

    @Override
    public String getItemName() {
        return "Medkit";
    }
}
