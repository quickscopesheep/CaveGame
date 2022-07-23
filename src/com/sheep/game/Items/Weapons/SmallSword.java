package com.sheep.game.Items.Weapons;

import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class SmallSword extends Sword{
    public SmallSword(Mob owner) {
        super(owner);
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)((owner.getX()-8 + owner.getDirX()*8)), (int)((owner.getY()-8 - 2)), Sprite.item_sword, false);
    }

    @Override
    public void use() {
        super.use();
    }

    @Override
    public Sprite getIcon() {
        return Sprite.item_sword;
    }
}
