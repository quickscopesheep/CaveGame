package com.sheep.game.Items;

import com.sheep.game.Game;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.entity.mob.meleeHitBox;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.CaveLevel.CaveLevel;
import com.sheep.game.util.AudioManager;
import com.sheep.game.util.input.Keyboard;
import com.sheep.game.util.MathUtil;

public class pickaxe extends Item{
    public static final float hitRange = 16;

    float hitX, hitY;

    public pickaxe(Mob owner) {
        super(owner, 30,  10);
    }

    @Override
    public void use() {
        super.use();
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
        hitX = owner.getX() + aimX*hitRange;
        hitY = owner.getY() + aimY*hitRange;

        if(owner.getLevel().getTileIndex((int)(hitX/16), (int)(hitY/16)) > 0){
            ((CaveLevel)owner.getLevel()).getTileIntegrity()[((int)(hitX/16)) + ((int)(hitY/16))*owner.getLevel().getWidth()] -= 1;
            if(((CaveLevel)owner.getLevel()).getTileIntegrity()[((int)(hitX/16)) + ((int)(hitY/16))*owner.getLevel().getWidth()] <= 0){
                owner.getLevel().getTiles()[((int)(hitX/16)) + ((int)(hitY/16))*owner.getLevel().getWidth()] = 0;
            }
        }

        float attackDirY = MathUtil.NormalizeY(aimX, aimY);
        float attackDirX = MathUtil.NormalizeX(aimX, aimY);

        owner.getLevel().Add(new meleeHitBox(owner.getX()-8 + attackDirX*12, owner.getY()-8 + attackDirY*12, owner.getLevel(), owner, 10, 10, 3));
        ((Player)owner).useStamina(staminaUse);
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
