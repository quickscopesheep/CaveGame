package com.sheep.game.entity.mob;

import com.sheep.game.Game;
import com.sheep.game.Items.Item;
import com.sheep.game.Items.items.pickaxe;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.ItemDrop;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.input.Keyboard;

import java.util.Random;

public class Player extends Mob{
    private static final float moveSpeed = (float) 42 / 60;

    public static int deaths = 0;
    public static int kills = 0;

    boolean moving;
    int frame;

    Item[] items;
    int currentItem;

    AudioPlayer footstepAudio;

    boolean itemButtonLast;
    boolean dropButtonLast;

    float useCooldown;

    float stamina;
    float maxStamina;

    float staminaRegenTime = 120;
    float staminaRegenTimer;

    float healthRegenStartTime = 300;
    float healthRegenTime = 60;

    float healthRegenStartTimer;
    float healthRegenTimer;

    Random random;

    public Player(int x, int y, Level level, Game game) {
        super(x, y, 12, 14, 0, 2, 25, 25, EntityType.PLAYER, level, game);
        maxStamina = 100;
        stamina = maxStamina;

        random = new Random();
        footstepAudio = new AudioPlayer();

        items = new Item[5];
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
        if(frame == 7500) frame = 0;

        if(frame % 24 == 0 && moving){
             footstepAudio.loadSound("/sound/footstep" + random.nextInt(1, 2) + ".wav");
             footstepAudio.play();
        }

        float mag = (float)Math.sqrt(inputX*inputX + inputY*inputY);

        float dirX = (float)inputX/mag;
        float dirY = (float)inputY/mag;

        float moveX = dirX * moveSpeed;
        float moveY = dirY * moveSpeed;

        if(inputX == 0) moveX = 0;
        if(inputY == 0) moveY = 0;

        move(moveX, moveY);

        boolean itemButton = Keyboard.USE3;
        if(itemButton && !itemButtonLast){
            if(currentItem < items.length-1)
                equipFromHotbar(currentItem + 1);
            else
                equipFromHotbar(0);
        }
        itemButtonLast = itemButton;

        boolean dropButton = Keyboard.INVENTORY;
        if(dropButton && !dropButtonLast){
            dropItem(currentItem);
        }
        dropButtonLast = dropButton;

        if(Keyboard.USE1 && useCooldown <= 0 && item != null && stamina >= item.getStaminaUse()){
            useCooldown = item.getCooldown();
            if(item.getCoolDownVariation() > 0)
                useCooldown += random.nextFloat(-item.getCoolDownVariation(), item.getCoolDownVariation());
            useItem();
        }

        if(useCooldown > 0) useCooldown--;

        if(staminaRegenTimer <= 0 && stamina < maxStamina){
            stamina++;
        }else{
            staminaRegenTimer--;
        }

        if(!moving & health < maxHealth*.75 && healthRegenStartTimer <= 0 && game.settings.difficulty < 3){
            if(healthRegenTimer <= 0){
                health += .5;
                healthRegenTimer = healthRegenTime;
            }else {
                healthRegenTimer--;
            }
        }else{
            healthRegenStartTime--;
        }
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        healthRegenTimer = healthRegenTime;
        if(health <= 0) game.playerDead();
    }

    @Override
    public void move(float ax, float ay) {
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

    public boolean pickupItem(Item item){
        for(int i = 0; i < items.length; i++){
            if(items[i] == null){
                items[i] = item;
                item.setOwner(this);
                equipFromHotbar(currentItem);
                return true;
            }
        }
        return false;
    }

    public void dropItem(int index){
        if(items[index] == null) return;
        Item itemToDrop = items[index];
        items[index] = null;
        item = null;
        level.Add(new ItemDrop(this.x + dirX*16, this.getY() + dirY*16, level, itemToDrop, 30, game));
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
        int anim = frame % 24;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y-8, anim > 10 ? Sprite.player : Sprite.player_walk, dirX == -1);
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
