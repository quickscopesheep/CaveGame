package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.Game;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.MathUtil;

public class Husk extends Unit{
    private static final float moveSpeed = (float) 16 / 60;

    float damageCoolDown, moveCoolDown;

    boolean moving;
    int frame;

    public Husk(float x, float y, Level level) {
        super(x, y, 10, 15, 0, 1, 6*16, EntityType.HUSK, level);
    }

    @Override
    public void tick() {
        super.tick();
        frame++;
        if(frame > 128) frame = 0;

        float playerX = Game.player.getX();
        float playerY = Game.player.getY();
        float dirX = MathUtil.NormalizeX(playerX - this.x, playerY - this.y) * .8f;
        float dirY = MathUtil.NormalizeY(playerX - this.x, playerY - this.y);

        if(damageCoolDown > 0) damageCoolDown--;
        if(moveCoolDown > 0) moveCoolDown--;
        if(canSeePlayer() && !collision(Game.player) && moveCoolDown <= 0){
            moving = true;
            move(dirX * moveSpeed, dirY * moveSpeed);
        }else{
            moving = false;
            if(damageCoolDown <= 0 && canSeePlayer()){
                Game.player.Damage(10, dirX * 20, dirY * 20, 10);
                damageCoolDown = 60;
                moveCoolDown = 15;
            }
        }
    }

    @Override
    public void render(Screen screen) {
        int anim = frame / 12 % 2;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y - 8, anim == 1 ? Sprite.husk : Sprite.husk_walk, dirX == -1);
        }else{
            screen.renderSpriteLit((int) x - 8, (int) y - 8, Sprite.husk, dirX == -1);
        }
    }
}
