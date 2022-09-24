package com.sheep.game.level;

import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityType;
import com.sheep.game.entity.mob.EnemyUnits.Unit;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.tiles.Tile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Level {
    protected int width, height;
    protected int[] tiles;

    protected final long seed;

    protected List<Entity> entities;

    List<Entity> entitiesToAdd;
    List<Entity> entitiesToRemove;

    protected int[] tileIntegrity;

    public Level(int width, int height, long seed){
        this.width= width;
        this.height = height;
        this.seed = seed;

        entities = new LinkedList<>();

        entitiesToAdd = new LinkedList<>();
        entitiesToRemove = new LinkedList<>();

        tiles = new int[width * height];
    }

    protected void generateLevel(){

    }

    public void tick(){
        for(Entity e : entities){
            e.tick();
        }

        entities.addAll(entitiesToAdd);
        entities.removeAll(entitiesToRemove);
        entitiesToAdd.clear();
        entitiesToRemove.clear();
    }

    public void render(int xScroll, int yScroll, Screen screen){
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = ((xScroll + screen.getWidth()) >> 4) + 16;
        int y0 = yScroll >> 4;
        int y1 = ((yScroll + screen.getHeight()) >> 4) + 16;

        for(int y = y0; y < y1; y++){
            for(int x = x0; x < x1; x++){
                getTile(x, y).render(x << 4, y << 4, screen, this);
            }
        }

        for(Entity e : entities){
            e.render(screen);
        }
    }

    public List<Entity> getAllEntitiesOfType(EntityType type){
        List<Entity> list = new ArrayList<>();
        for(Entity e : entities){
            if(e.getType() == type) list.add(e);
        }

        return list;
    }

    public Entity getAllEntityOfType(EntityType type){
        for(Entity e : entities){
            if(e.getType() == type) return e;
        }

        return null;
    }

    public Tile getTile(int x, int y){
        return Tile.voidTile;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void Remove(Entity entity){
        entitiesToRemove.add(entity);
    }

    public void Add(Entity entity){
        entitiesToAdd.add(entity);
    }

    public long getSeed(){
        return seed;
    }

    public int getTileIndex(int x, int y){
        return tiles[y*width+x];
    }

    public int[] getTiles(){
        return tiles;
    }
}
