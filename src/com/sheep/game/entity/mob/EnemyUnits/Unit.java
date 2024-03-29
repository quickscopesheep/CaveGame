package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.Game;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.level.Level;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.MathUtil;

public abstract class Unit extends Mob {
    protected float ViewDistance;
    protected AudioPlayer audio;

    public Unit(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, float maxHealth, float startHealth, float viewDistance,
                EntityType type, Level level, Game game) {
        super(x, y, xBound, yBound, xBoundOffset, yBoundOffset, startHealth, maxHealth, type, level, game);
        this.ViewDistance = viewDistance;
        this.audio = new AudioPlayer();
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        audio.loadSound(AudioPlayer.SFX_MOB_HIT);
        audio.play();
    }

    @Override
    public void tick() {
        super.tick();
    }

    float dstToPlayer(){
        return MathUtil.Distance(this.x, this.y, game.player.getX(), game.player.getY());
    }

    public boolean canSeePlayer(){
        return dstToPlayer() < ViewDistance;
    }
}
