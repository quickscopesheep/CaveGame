package com.sheep.game.entity.mob;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.Keyboard;

import java.awt.desktop.SystemSleepEvent;

public class Player extends Mob{
    private static final float moveSpeed = (float) 48 / 60;
    boolean moving;
    int frame;

    public Player(int x, int y, Level level) {
        super(x, y, 12, 14, 0, 2, EntityType.Player, level);
        health = 100;
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

        float moveX = ((float)inputX/mag) * moveSpeed;
        float moveY = ((float)inputY/mag) * moveSpeed;

        if(inputX == 0) moveX = 0;
        if(inputY == 0) moveY = 0;

        move(moveX, moveY);
    }

    @Override
    public void render(Screen screen) {
        int anim = frame / 12 % 2;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y - 8, anim == 1 ? Sprite.player : Sprite.player_walk, dir == 0);
        }else{
            screen.renderSpriteLit((int) x - 8, (int) y - 8, Sprite.player, dir == 0);
        }

    }
}
