package com.sheep.game.entity;

import com.sheep.game.Game;
import com.sheep.game.Items.Item;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;
import com.sheep.game.util.AudioPlayer;

public class ItemDrop extends Entity{
    Item drop;
    float pickupDelay;

    AudioPlayer audio;

    public ItemDrop(float x, float y, Level level, Item drop, float pickupDelay, Game game) {
        super(x, y, 16, 16, 0, 0, EntityType.ITEM_DROP, level, game);
        this.drop = drop;
        this.pickupDelay = pickupDelay;
        this.audio = new AudioPlayer();
    }

    @Override
    public void tick() {
        if(collision(game.player) && pickupDelay <= 0){
            if(game.player.pickupItem(drop)){
                audio.loadSound(AudioPlayer.SFX_PICKUP);
                audio.play();
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
