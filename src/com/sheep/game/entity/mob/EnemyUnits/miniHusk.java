package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.entity.EntityType;
import com.sheep.game.level.Level;

public class miniHusk extends Unit{
    public miniHusk(float x, float y, Level level) {
        super(x, y, 10, 10, 2, 6, 7, 7, 6, EntityType.HUSK_MINION, level);
    }
}
