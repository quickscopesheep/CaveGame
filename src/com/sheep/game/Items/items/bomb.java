package com.sheep.game.Items.items;

import com.sheep.game.Game;
import com.sheep.game.Items.Item;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.entity.mob.thrownBomb;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.input.Keyboard;

public class bomb extends Item {
    private static final float THROW_FORCE = 4f;

    AudioPlayer fuzeAudio;
    AudioPlayer explodeAudio;

    public bomb(Mob owner, Game game) {
        super(owner, game);
    }

    @Override
    public void use() {

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

        owner.getLevel().Add(new thrownBomb(owner.getX() + aimX*8, owner.getY() + aimY*8, aimX*THROW_FORCE, aimY * THROW_FORCE, owner.getLevel(), game));
        owner.equip(null);
        if(owner.getType() == EntityType.PLAYER){
            ((Player)owner).getItems()[((Player)owner).getCurrentItem()] = null;
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)((owner.getX()-8 + owner.getDirX()*8)), (int)((owner.getY()-8)), Sprite.item_bomb, false);
    }

    @Override
    public String getItemName() {
        return "bomb";
    }

    @Override
    public Sprite getIcon() {
        return Sprite.item_bomb;
    }
}
