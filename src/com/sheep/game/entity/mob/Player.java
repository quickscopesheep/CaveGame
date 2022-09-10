package com.sheep.game.entity.mob;

import com.sheep.game.Items.Item;
import com.sheep.game.Items.medkit;
import com.sheep.game.Items.pickaxe;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.Keyboard;

public class Player extends Mob{
    private static final float moveSpeed = (float) 48 / 60;

    boolean moving;
    int frame;

    Item[] items;
    int currentItem;

    boolean itemButtonLast;

    float useCooldown;

    float stamina;
    float maxStamina;

    float staminaRegenTime = 120;
    float staminaRegenTimer;

    public Player(int x, int y, Level level) {
        super(x, y, 12, 14, 0, 2, 25, 25, EntityType.PLAYER, level);
        health = 25;
        maxStamina = 100;
        stamina = maxStamina;
        items = new Item[3];
        items[0] = new pickaxe(this);
        items[1] = new medkit(this);
        equip(items[0]);
    }

    @Override
    public void tick(){
        super.tick();
        int inputX = 0, inputY = 0;
        if(Keyboard.UP) inputY = -1;
        if(Keyboard.DOWN) inputY = 1;
        if(Keyboard.LEFT) inputX = -1;
        if(Keyboard.RIGHT) inputX = 1;

        moving = inputX != 0 || inputY != 0;

        frame++;
        if(frame > 128) frame = 0;

        float mag = (float)Math.sqrt(inputX*inputX + inputY*inputY);

        float dirX = (float)inputX/mag;
        float dirY = (float)inputY/mag;

        float moveX = dirX*moveSpeed;
        float moveY = dirY*moveSpeed;

        if(inputX == 0) moveX = 0;
        if(inputY == 0) moveY = 0;

        move(moveX, moveY);

        boolean itemButton = Keyboard.USE3;
        if(itemButton && !itemButtonLast){
            if(currentItem < 2)
                equipFromHotbar(currentItem + 1);
            else
                equipFromHotbar(0);
        }
        itemButtonLast = itemButton;

        if(Keyboard.USE1 && useCooldown <= 0 && item != null && stamina >= item.getStaminaUse()){
            useCooldown = item.getCooldown();
            useItem();
        }

        if(useCooldown > 0) useCooldown--;

        if(staminaRegenTimer <= 0 && stamina < maxStamina){
            stamina++;
        }else{
            staminaRegenTimer--;
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
        if(!Keyboard.USE2){
            if(ax != 0){
                if(ax > 0) dirX = 1;
                if(ax < 0) dirX = -1;
            }
            if(ay != 0){
                if(ay > 0) dirY = 1;
                if(ay < 0) dirY = -1;
            }
        }
    }

    void equipFromHotbar(int index){
        if(items[index] != null)
            equip(items[index]);
        else
            equip(null);

        currentItem = index;
    }

    @Override
    public void render(Screen screen) {
        int anim = frame / 12 % 2;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y-8, anim == 1 ? Sprite.player : Sprite.player_walk, dirX == -1);
        }else{
            screen.renderSpriteLit((int) x - 8, (int) y-8, Sprite.player, dirX == -1);
        }

        if(item != null)
            item.render(screen);
    }

    public void useStamina(float amount){
        stamina -= amount;
        staminaRegenTimer = staminaRegenTime;
    }

    public Item[] getItems(){
        return items;
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public float getStamina() {
        return stamina;
    }

    public float getMaxStamina() {
        return maxStamina;
    }
}
