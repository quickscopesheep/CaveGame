package com.sheep.game.entity.mob;

import com.sheep.game.Items.medkit;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.ItemDrop;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

import java.util.Random;

public class Chest extends Mob{
    boolean open;

    public Chest(float x, float y, Level level) {
        super(x, y, 16, 16, 9999, EntityType.CHEST, level);
        health = 9999;
        open = false;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        if(!open){
            open = true;
            spillLoot();
        }
    }

    void spillLoot(){
        Random random = new Random();

        int offsetX = random.nextInt(-8, 8);
        int offsetY = random.nextInt(-8, 8);

        level.Add(new ItemDrop(x + offsetX, y + offsetY, level, new medkit(null)));
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-8, (int)y-8, open ? Sprite.chest_open : Sprite.chest_closed, false);
    }
}
