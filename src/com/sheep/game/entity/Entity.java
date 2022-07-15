package com.sheep.game.entity;

import com.sheep.game.entity.mob.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

public abstract class Entity {
    protected float x, y;
    int quadrant;

    EntityType type;

    protected Level level;
    boolean removed;

    public Entity(float x, float y, EntityType type, Level level){
        this.x = x;
        this.y = y;
        this.type = type;
        this.level = level;
    }

    public void tick(){
        int quadX = (int)((x/16)/16);
        int quadY = (int)((y/16)/16);
        quadrant = quadY*level.getWidth()+quadX;
    }

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

    public int getQuadrant() {
        return quadrant;
    }

    public EntityType getType() {
        return type;
    }
}
