package com.sheep.game.entity.mob;

import com.sheep.game.Items.Item;
import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

public class Mob extends Entity {
    protected int dirX, dirY;

    protected float xBound;
    protected float yBound;

    protected float xBoundOffset;
    protected float yBoundOffset;

    protected float health;
    float knockBackX, knockBackY, knockBackTime;

    protected Item item;

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
        dirX = 1;
    }

    @Override
    public void tick(){
        super.tick();
        if(knockBackX > 1 || knockBackX < -1 || knockBackY > 1 || knockBackY < -1){
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
            if(ax > 0) dirX = 1;
            if(ax < 0) dirX = -1;
        }
        if(ay != 0){
            if(ay > 0) dirY = 1;
            if(ay < 0) dirY = -1;
        }
    }

    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime){
        health -= damage;
        this.knockBackX = knockBackX;
        this.knockBackY = knockBackY;
        this.knockBackTime = knockBackTime;

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

    public void useItem(){
        if(item != null)
            item.use();
    }

    public void equip(Item item){
        this.item = item;
    }

    public Item getEquipedItem(){
        return item;
    }

    float getHealth(){
        return health;
    }

    protected boolean collision(Mob other){
        return this.x < other.x + other.xBound &&
                this.x + this.xBound > other.x &&
                this.y < other.y + other.yBound &&
                this.yBound + this.y > other.y;
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

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }
}
