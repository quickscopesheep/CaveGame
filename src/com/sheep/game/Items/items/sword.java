package com.sheep.game.Items.items;

import com.sheep.game.Items.Item;
import com.sheep.game.entity.mob.Mob;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.entity.mob.meleeHitBox;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.CaveLevel;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.MathUtil;
import com.sheep.game.util.input.Keyboard;

import java.util.Random;

public class sword extends Item {
    public static final float hitRange = 16;

    AudioPlayer audio;

    Random random;
    float hitX, hitY;

    public sword(Mob owner) {
        super(owner, 20, 2, 7);
        random = new Random();
        audio = new AudioPlayer();
    }

    @Override
    public void use() {
        super.use();
        int aimX = 0, aimY = 0;

        if (Keyboard.UP) {
            aimY--;
        }
        if (Keyboard.DOWN) {
            aimY++;
        }
        if (Keyboard.LEFT) {
            aimX--;
        }
        if (Keyboard.RIGHT) {
            aimX++;
        }

        audio.loadSound(AudioPlayer.SFX_SWORD_SWING);
        audio.play();

        float attackDirY = MathUtil.NormalizeY(aimX, aimY);
        float attackDirX = MathUtil.NormalizeX(aimX, aimY);

        owner.getLevel().Add(new meleeHitBox(owner.getX() - 8 + attackDirX * 12, owner.getY() - 8 + attackDirY * 12, owner.getLevel(), owner, 15, 10, 3));
        ((Player) owner).useStamina(staminaUse);
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int) ((owner.getX() - 8 + owner.getDirX() * 8)), (int) ((owner.getY() - 8)), Sprite.item_sword, false);
    }

    @Override
    public Sprite getIcon() {
        return Sprite.item_sword;
    }

    @Override
    public String getItemName() {
        return "Sword";
    }
}