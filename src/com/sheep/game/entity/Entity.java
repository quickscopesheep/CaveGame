package com.sheep.game.entity;

import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

public abstract class Entity {
    protected float x, y;
    EntityType type;

    protected Level level;
    boolean removed;

    public Entity(float x, float y, EntityType type, Level level){
        this.x = x;
        this.y = y;
        this.type = type;
        this.level = level;
    }

    public abstract void tick();

    public abstract void render(Screen screen);

    public void Remove(){
        removed = true;
        level.Remove(this);
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

    public boolean isRemoved() {
        return removed;
    }

    public EntityType getType() {
        return type;
    }
}
