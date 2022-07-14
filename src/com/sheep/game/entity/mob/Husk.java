package com.sheep.game.entity.mob;

import com.sheep.game.Game;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.MathUtil;

public class Husk extends Mob{
    private static final float moveSpeed = (float) 16 / 60;

    boolean moving;
    int frame;

    public Husk(float x, float y, Level level) {
        super(x, y, 10, 15, 0, 1, level);
    }

    @Override
    public void tick() {
        frame++;
        if(frame > 128) frame = 0;

        if(canSeePlayer() && dstToPlayer() > 2){
            float playerX = Game.player.getX();
            float playerY = Game.player.getY();

            float dirX = MathUtil.NormalizeX(playerX - this.x, playerY - this.y) * .8f;
            float dirY = MathUtil.NormalizeY(playerX - this.x, playerY - this.y);



            moving = dirX != 0 || dirY != 0;

            move(dirX * moveSpeed, dirY * moveSpeed);
        }
    }

    float dstToPlayer(){
        return MathUtil.Distance(this.x, this.y, Game.player.getX(), Game.player.getY());
    }

    @Override
    public void render(Screen screen) {
        int anim = frame / 12 % 2;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y - 8, anim == 1 ? Sprite.husk : Sprite.husk_walk, dir == 0);
        }else{
            screen.renderSpriteLit((int) x - 8, (int) y - 8, Sprite.husk, dir == 0);
        }
    }

    boolean canSeePlayer(){
        return true;
    }
}
