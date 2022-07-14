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

    public Mob(float x, float y, float xBound, float yBound, Level level) {
        super(x, y, level);
        this.xBound = xBound;
        this.yBound = yBound;
        this.xBoundOffset = 0;
        this.yBoundOffset = 0;
    }

    public Mob(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, Level level) {
        super(x, y, level);
        this.xBound = xBound;
        this.yBound = yBound;
        this.xBoundOffset = xBoundOffset;
        this.yBoundOffset = yBoundOffset;
    }

    @Override
    public void tick(){

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
