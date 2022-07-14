package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public class Tile {

    public static Tile voidTile = new Tile(new Sprite(16, 16, 0x000000));
    public static Tile wallTile = new WallTile(Sprite.wallSprite);
    public static Tile floorTile = new Tile(Sprite.floor);

    public Sprite sprite;

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen, Level level){
        screen.renderSpriteLit(x, y, this.sprite, false);
    }

    public boolean solid(){
        return false;
    }
}
