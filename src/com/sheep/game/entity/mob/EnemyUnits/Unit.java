package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.Game;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.level.Level;
import com.sheep.game.util.MathUtil;

public class Unit extends Mob {
    protected float ViewDistance;

    public Unit(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, float health, float viewDistance, EntityType type, Level level) {
        super(x, y, xBound, yBound, xBoundOffset, yBoundOffset, health, type, level);
        this.ViewDistance = viewDistance;
    }

    float dstToPlayer(){
        return MathUtil.Distance(this.x, this.y, Game.player.getX(), Game.player.getY());
    }

    boolean canSeePlayer(){
        return dstToPlayer() < ViewDistance;
    }
}
