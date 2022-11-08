package com.sheep.game.entity.mob;

import com.sheep.game.Game;
import com.sheep.game.Items.Item;
import com.sheep.game.Items.items.bomb;
import com.sheep.game.Items.items.sword;
import com.sheep.game.Items.lootTable.LootTable;
import com.sheep.game.Items.items.medkit;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.ItemDrop;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Chest extends Mob{
    boolean open;

    LootTable lootTable;

    public Chest(float x, float y, Level level, Game game) {
        super(x, y, 16, 16, 9999, EntityType.CHEST, level, game);
        health = 9999;
        open = false;
        //lootTable = new LootTable("json/lootTables/crateLootTable.json");

        lootTable = new LootTable();
        lootTable.addDrop(sword.class, 25);
        lootTable.addDrop(medkit.class, 40);
        lootTable.addDrop(bomb.class, 35);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
        if(!open){
            open = true;
            spillLoot();
        }
    }

    void spillLoot(){
        Random random = new Random();

        int offsetX = random.nextInt(-8, 8);
        int offsetY = random.nextInt(-8, 8);

        try {
            Class<?> itemClass = lootTable.getRandomDrop().itemClass;
            Constructor<?> itemConstructor = itemClass.getConstructor(Mob.class, Game.class);
            Item item = (Item)itemConstructor.newInstance((Mob)null, game);

            level.Add(new ItemDrop(x + offsetX, y + offsetY, level, item, 0, game));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSpriteLit((int)x-8, (int)y-8, open ? Sprite.chest_open : Sprite.chest_closed, false);
    }
}
