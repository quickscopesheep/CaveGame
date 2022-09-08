package com.sheep.game.Items;

import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.tiles.Tile;
import com.sheep.game.util.Keyboard;
import com.sheep.game.util.MathUtil;

public class pickaxe extends Item{
    public static final float hitRange = 16;

    float useCooldownTimer = 0;

    float hitX, hitY;

    public pickaxe(Mob owner) {
        super(owner, 5);
    }

    @Override
    public void use() {
        super.use();
        if(useCooldownTimer <= 0){
            int aimX = 0, aimY = 0;

            if(Keyboard.UP){
                aimY --;
            }
            if(Keyboard.DOWN){
                aimY ++;
            }
            if(Keyboard.LEFT){
                aimX --;
            }
            if(Keyboard.RIGHT){
                aimX ++;
            }

            useCooldownTimer = getCooldown();

            hitX = owner.getX() + aimX*hitRange;
            hitY = owner.getY() + aimY*hitRange;

            System.out.println((int)(hitY/16));

            if(owner.getLevel().getTileIndex((int)(hitX/16), (int)(hitY/16)) > 0){
                owner.getLevel().getTiles()[((int)(hitX/16)) + ((int)(hitY/16))*owner.getLevel().getWidth()] -= 1;
            }
        }else{
            useCooldownTimer--;
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)((owner.getX()-8 + owner.getDirX()*8)), (int)((owner.getY()-8)), Sprite.item_pickaxe, false);
    }

    @Override
    public Sprite getIcon() {
        return Sprite.item_pickaxe;
    }
}
