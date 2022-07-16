package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.Game;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.MathUtil;

public class Demon extends Unit{
    private static final float moveSpeed = (float) 16 / 60;

    boolean moving;
    int frame;

    public Demon(float x, float y, Level level) {
        super(x, y, 10, 16, 0, 2, 6*16, EntityType.Demon, level);
    }

    @Override
    public void tick() {
        super.tick();
        frame++;
        if(frame > 128) frame = 0;
        if(quadrant == Game.player.getQuadrant()){
            float playerX = Game.player.getX();
            float playerY = Game.player.getY();

            float dirX = MathUtil.NormalizeX(playerX - this.x, playerY - this.y) * .8f;
            float dirY = MathUtil.NormalizeY(playerX - this.x, playerY - this.y);

            if(dirX > 0) dir = 1;
            if(dirX < 0) dir = 0;

            if(canSeePlayer()) {
                if (dstToPlayer() > 4 * 16) {
                    moving = true;
                    move(dirX * moveSpeed, dirY * moveSpeed);
                } else if (dstToPlayer() < 3 * 16) {
                    moving = true;
                    move(-dirX * moveSpeed, -dirY * moveSpeed);
                } else {
                    moving = false;
                }
            }
        }
    }

    @Override
    public void move(float ax, float ay) {
        if(!collision(ax, 0)){
            x += ax;
        }

        if(!collision(0, ay)){
            y += ay;
        }
    }

    @Override
    public void render(Screen screen) {
        int anim = frame / 12 % 2;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y - 8, anim == 1 ? Sprite.demon : Sprite.demon_walk, dir == 0);
        }else{
            screen.renderSpriteLit((int) x - 8, (int) y - 8, Sprite.demon, dir == 0);
        }
    }
}
