package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.Game;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.EnemyUnits.Goals.AIGoal;
import com.sheep.game.entity.mob.EnemyUnits.Goals.AIState;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.level.Level;
import com.sheep.game.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class Unit extends Mob {
    protected float ViewDistance;

    protected List<AIState> AIStates;

    public Unit(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, float maxHealth, float startHealth, float viewDistance, EntityType type, Level level) {
        super(x, y, xBound, yBound, xBoundOffset, yBoundOffset, startHealth, maxHealth, type, level);
        AIStates = new ArrayList<>();
        this.ViewDistance = viewDistance;
    }

    protected void AiInit(){

    }

    @Override
    public void tick() {
        super.tick();
    }

    float dstToPlayer(){
        return MathUtil.Distance(this.x, this.y, Game.player.getX(), Game.player.getY());
    }

    boolean canSeePlayer(){
        return dstToPlayer() < ViewDistance;
    }
}
