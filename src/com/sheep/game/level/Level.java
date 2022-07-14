package com.sheep.game.level;

import com.sheep.game.entity.Entity;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.tiles.Tile;
import com.sheep.game.util.MathUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Level {
    protected int width, height;
    protected int[] tiles;

    List<Entity> entities;

    public Level(int width, int height){
        this.width= width;
        this.height = height;

        entities = new ArrayList<>();

        tiles = new int[width * height];
        generateLevel();
    }

    protected void generateLevel(){

    }

    public void tick(){
        for(Entity e : entities){
            e.tick();
        }
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
        entities.remove(entity);
    }

    public void Add(Entity entity){
        entities.add(entity);
    }
}
