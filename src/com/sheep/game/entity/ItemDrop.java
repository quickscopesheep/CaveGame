package com.sheep.game.entity;

import com.sheep.game.Game;
import com.sheep.game.Items.Item;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;
import com.sheep.game.util.AudioManager;

public class ItemDrop extends Entity{
    Item drop;
    float pickupDelay;

    public ItemDrop(float x, float y, Level level, Item drop, float pickupDelay) {
        super(x, y, 16, 16, 0, 0, EntityType.ITEM_DROP, level);
        this.drop = drop;
        this.pickupDelay = pickupDelay;
    }

    @Override
    public void tick() {
        if(collision(Game.player) && pickupDelay <= 0){
            if(Game.player.pickupItem(drop)){
                Game.audioManager.loadSound(AudioManager.SFX_PICKUP);
                Game.audioManager.play();
                level.Remove(this);
            }
        }

        if(pickupDelay > 0) pickupDelay--;
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-8, (int)y-8, drop.getIcon(), false);
    }

    public Item getDrop() {
        return drop;
    }
}
