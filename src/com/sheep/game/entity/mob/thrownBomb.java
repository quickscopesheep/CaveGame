package com.sheep.game.entity.mob;

import com.sheep.game.Items.Item;
import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityType;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.MathUtil;

public class thrownBomb extends Mob{
    private static final float FRICTION = .8f;
    private static final int FUZE_TIME = 180;
    private static final int EXPLODE_RADIUS = 4;
    private static final int DAMAGE_RADIUS = 3;

    float velX, velY;
    int time;

    AudioPlayer fuzeAudio;
    AudioPlayer explodeAudio;

    public thrownBomb(float x, float y, float velX, float velY, Level level) {
        super(x, y, 12, 14, 2, 1, 9999, 9999, EntityType.BOMB, level);
        this.velX = velX;
        this.velY = velY;
        this.time = FUZE_TIME;

        fuzeAudio = new AudioPlayer();
        fuzeAudio.loadSound(AudioPlayer.SFX_BOMB_FUZE);

        explodeAudio = new AudioPlayer();
        explodeAudio.loadSound(AudioPlayer.SFX_BOMB_EXPLODE);

        fuzeAudio.play();
    }

    @Override
    public void tick() {
        move(velX, velY);
        velX *= FRICTION;
        velY *= FRICTION;

        time--;
        if(time <= 0){
            explode();
        }
    }

    void explode(){
        fuzeAudio.stop();
        explodeAudio.play();

        int tileX = (int)(x/16);
        int tileY = (int)(y/16);

        for(int x = tileX - EXPLODE_RADIUS; x < tileX + EXPLODE_RADIUS; x++){
            for(int y = tileY - EXPLODE_RADIUS; y < tileY + EXPLODE_RADIUS; y++){
                if(MathUtil.Distance(tileX, tileY, x, y) <= EXPLODE_RADIUS){
                    level.getTiles()[y * level.getWidth() + x] = 0;
                }
            }
        }

        for (Entity e : level.getEntities()) {
            if(e == this) continue;

            float dirX = MathUtil.NormalizeX(e.getX() - x, e.getY() - y);
            float dirY = MathUtil.NormalizeY(e.getX() - x, e.getY() - y);

            if(MathUtil.Distance(x, y, e.getX(), e.getY()) <= DAMAGE_RADIUS*16){
                System.out.println(e.getClass().getName());
                e.Damage(100, dirX * 20, dirY * 20, 10);
            }
        }

        level.Remove(this);
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-6, (int)y - 7, Sprite.item_bomb_lit, false);
    }
}
