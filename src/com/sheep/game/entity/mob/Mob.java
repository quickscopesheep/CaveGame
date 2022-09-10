package com.sheep.game.entity.mob;

import com.sheep.game.Items.Item;
import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

public class Mob extends Entity {
    protected int dirX, dirY;

    protected float health;
    protected float maxHealth;
    float knockBackX, knockBackY, knockBackTime;

    protected Item item;

    public Mob(float x, float y, float xBound, float yBound, float maxHealth, EntityType type, Level level) {
        super(x, y, xBound, yBound, 0, 0, type, level);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        dirX = 1;
    }

    public Mob(float x, float y, float xBound, float yBound, float maxHealth, float startHealth, EntityType type, Level level) {
        super(x, y, xBound, yBound, 0, 0, type, level);
        this.maxHealth = maxHealth;
        this.health = startHealth;
        dirX = 1;
    }

    public Mob(float x, float y, float xBound, float yBound, float xBoundOffset, float yBoundOffset, float maxHealth, float startHealth, EntityType type, Level level) {
        super(x, y, xBound, yBound, xBoundOffset, yBoundOffset, type, level);
        this.maxHealth = maxHealth;
        this.health = startHealth;
        dirX = 1;
    }

    @Override
    public void tick(){
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

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime){
        health -= damage;
        this.knockBackX = knockBackX;
        this.knockBackY = knockBackY;
        this.knockBackTime = knockBackTime;
        if(health <= 0) Remove();
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

    public float getHealth(){
        return health;
    }

    public void setHealth(float health){
        this.health = health;
    }

    public float getMaxHealth() {
        return maxHealth;
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
