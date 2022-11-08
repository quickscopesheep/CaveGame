package com.sheep.game.entity;

import com.sheep.game.Game;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

public abstract class Entity {
    protected float x, y;

    protected float xBound;
    protected float yBound;

    protected float xBoundOffset;
    protected float yBoundOffset;

    EntityType type;

    protected Level level;
    boolean removed;

    protected Game game;

    public Entity(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, EntityType type, Level level, Game game){
        this.x = x;
        this.y = y;
        this.xBound = xBound;
        this.yBound = yBound;
        this.xBoundOffset = xBoundOffset;
        this.yBoundOffset = yBoundOffset;

        this.type = type;
        this.level = level;
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Screen screen);


    //inheritance bullshit
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime){

    }

    public void Remove(){
        removed = true;
        level.Remove(this);
    }

    protected boolean collision(float ax, float ay){
        float x0 = x + ax + xBoundOffset - xBound/2;
        float x1 = x + ax + xBoundOffset + xBound/2;
        float y0 = y + ay + yBoundOffset - yBound/2;
        float y1 = y + ay + yBoundOffset + yBound/2;

        return level.getTile((int) (x0 / 16), (int) (y0 / 16)).solid()||
                level.getTile((int) (x0 / 16), (int) (y1 / 16)).solid() ||
                level.getTile((int) (x1 / 16), (int) (y0 / 16)).solid() ||
                level.getTile((int) (x1 / 16), (int) (y1 / 16)).solid();
    }

    protected boolean collision(Entity other){
        return this.x < other.x + other.xBound &&
                this.x + this.xBound > other.x &&
                this.y < other.y + other.yBound &&
                this.yBound + this.y > other.y;
    }

    public Level getLevel(){
        return level;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getXBound() {
        return xBound;
    }

    public float getYBound() {
        return yBound;
    }

    public float getXBoundOffset() {
        return xBoundOffset;
    }

    public float getYBoundOffset() {
        return yBoundOffset;
    }

    public boolean isRemoved() {
        return removed;
    }

    public EntityType getType() {
        return type;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
