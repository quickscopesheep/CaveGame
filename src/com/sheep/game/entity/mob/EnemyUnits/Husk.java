package com.sheep.game.entity.mob.EnemyUnits;

import com.sheep.game.Game;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.MathUtil;

public class Husk extends Unit{
    public static final float moveSpeed = (float) 16 / 60;

    float damageCoolDown, moveCoolDown;

    boolean moving;
    int frame;

    public Husk(float x, float y, Level level, Game game) {
        super(x, y, 10, 15, 0, 1, 25, 25, 6*16, EntityType.HUSK, level, game);
        switch (game.settings.difficulty){
            case 0 -> {maxHealth = 20;}
            case 1 -> {maxHealth = 25;}
            case 2 -> {maxHealth = 30;}
            case 3 -> {maxHealth = 50;}
        }

        this.health = maxHealth;
    }

    @Override
    public void tick() {
        super.tick();
        frame++;
        if(frame > 128) frame = 0;

        float playerX = game.player.getX();
        float playerY = game.player.getY();
        float dirX = MathUtil.NormalizeX(playerX - this.x, playerY - this.y) * .8f;
        float dirY = MathUtil.NormalizeY(playerX - this.x, playerY - this.y);

        if(damageCoolDown > 0) damageCoolDown--;
        if(moveCoolDown > 0) moveCoolDown--;
        if(canSeePlayer() && !collision(game.player) && moveCoolDown <= 0){
            moving = true;
            move(dirX * moveSpeed, dirY * moveSpeed);
        }else{
            moving = false;
            if(damageCoolDown <= 0 && canSeePlayer()){
                game.player.Damage(5, dirX * 20, dirY * 20, 10);
                damageCoolDown = 60;
                moveCoolDown = 15;
            }
        }
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        if(health <= 0){
            Player.kills++;
        }
    }

    @Override
    public void render(Screen screen) {
        int anim = frame / 12 % 2;
        if(moving){
            screen.renderSpriteLit((int) x - 8, (int) y - 8, anim == 1 ? Sprite.husk : Sprite.husk_walk, dirX == -1);
        }else{
            screen.renderSpriteLit((int) x - 8, (int) y - 8, Sprite.husk, dirX == -1);
        }
    }
}
