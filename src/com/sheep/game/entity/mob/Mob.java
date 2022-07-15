package com.sheep.game.entity.mob;

import com.sheep.game.entity.Entity;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;
import com.sheep.game.level.tiles.Tile;
import com.sheep.game.util.Keyboard;

public class Mob extends Entity {
    protected int dir;

    protected float xBound;
    protected float yBound;

    protected float xBoundOffset;
    protected float yBoundOffset;

    float health;
    float knockBackX, knockBackY, knockBackTime;

    public Mob(float x, float y, float xBound, float yBound, EntityType type, Level level) {
        super(x, y, type, level);
        this.xBound = xBound;
        this.yBound = yBound;
        this.xBoundOffset = 0;
        this.yBoundOffset = 0;
        health = 25;
    }

    public Mob(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, EntityType type, Level level) {
        super(x, y, type, level);
        this.xBound = xBound;
        this.yBound = yBound;
        this.xBoundOffset = xBoundOffset;
        this.yBoundOffset = yBoundOffset;
    }

    @Override
    public void tick(){
        super.tick();
        if(knockBackX != 0 || knockBackY != 0){
            float frameKnockBackX = knockBackX / knockBackTime;
            float frameKnockBackY = knockBackY / knockBackTime;

            move(frameKnockBackX, frameKnockBackY);

            knockBackX -= frameKnockBackX;
            knockBackY -= frameKnockBackY;
        }
    }

    @Override
    public void render(Screen screen) {

    }

    public void move(float ax, float ay){
        if(!collision(ax, 0)){
            x += ax;
        }

        if(!collision(0, ay)){
           y += ay;
        }

        if(ax != 0){
            if(ax > 0) dir = 1;
            if(ax < 0) dir = 0;
        }
    }

    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime){
        health -= damage;
        this.knockBackX = knockBackX;
        this.knockBackY = knockBackY;
        this.knockBackTime = knockBackTime;

    }

    private boolean collision(float ax, float ay){
        float x0 = x + ax + xBoundOffset - xBound/2;
        float x1 = x + ax + xBoundOffset + xBound/2;
        float y0 = y + ay + yBoundOffset - yBound/2;
        float y1 = y + ay + yBoundOffset + yBound/2;

        return level.getTile((int) (x0 / 16), (int) (y0 / 16)).solid()||
                level.getTile((int) (x0 / 16), (int) (y1 / 16)).solid() ||
                level.getTile((int) (x1 / 16), (int) (y0 / 16)).solid() ||
                level.getTile((int) (x1 / 16), (int) (y1 / 16)).solid();
    }

    public float getXBound() {
        return xBound;
    }

    public float getYBound() {
        return yBound;
    }
}
