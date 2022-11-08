package com.sheep.game.entity.mob;

import com.sheep.game.Game;
import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

import java.util.ArrayList;
import java.util.List;

public class meleeHitBox extends Mob{
    Mob instigator;

    int lifetime;
    int damage;
    float knockBack;

    int ticks;

    List<Entity> hitEntities = new ArrayList<>();

    public meleeHitBox(float x, float y, Level level, Mob instigator, int damage, float knockback, int lifeTime, Game game) {
        super(x, y, 24, 24, 99999, EntityType.MELEE_HIT_COL, level, game);
        this.instigator = instigator;
        this.damage = damage;
        this.knockBack = knockback;
        this.lifetime = lifeTime;
    }

    @Override
    public void tick() {
        for(Entity e : level.getEntities()){
            if(collision(e) && e != instigator && !hitEntities.contains(e)){
                e.Damage(damage, instigator.dirX*knockBack, instigator.dirY*knockBack, 5);
                hitEntities.add(e);
            }
        }

        ticks++;
        if(ticks >= lifetime) level.Remove(this);
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x, (int)y, Sprite.melee_hit_horizontal, instigator.dirX == -1);
    }
}
