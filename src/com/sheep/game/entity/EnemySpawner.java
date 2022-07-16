package com.sheep.game.entity;

import com.sheep.game.entity.mob.EnemyUnits.Demon;
import com.sheep.game.entity.mob.EnemyUnits.Husk;
import com.sheep.game.entity.mob.EnemyUnits.Unit;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

import java.util.Random;

public class EnemySpawner extends Entity{
    boolean flipped;
    int spawnInterval = 60*5;
    int spawnTick;

    Unit currentEnemy;

    Random random = new Random();

    public EnemySpawner(float x, float y, Level level) {
        super(x, y, EntityType.EnemySpawner, level);
        flipped = random.nextBoolean();
        spawnTick = spawnInterval;
    }

    @Override
    public void tick() {
        if(spawnTick < spawnInterval) spawnTick++;
        else {
            if(currentEnemy == null || currentEnemy.isRemoved()){
                spawnTick = 0;
                int enemyType = random.nextInt(100);

                if(enemyType < 75){
                    level.Add(currentEnemy = new Husk(x, y, level));
                }else if(enemyType > 75) {
                    level.Add(currentEnemy = new Demon(x + random.nextInt(-8, 8), y + random.nextInt(-8, 8), level));
                }
            }
        }
    }

    @Override
    public void render(Screen screen) {
    }
}
