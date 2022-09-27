package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.CaveLevel;
import com.sheep.game.level.Level;

public class WallTile extends Tile{
    public WallTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        screen.renderSpriteLit(x, y, this.sprite, false);
        if(level.getTileIndex((int)(x/16f), (int)(y/16f)) < 4) {

            int tileX = (int) (x / 16f);
            int tileY = (int) (y / 16f);

            switch (((CaveLevel) level).getTileIntegrity()[tileY*level.getWidth()+tileX]){
                case 3 -> screen.renderSpriteLit(x, y, Sprite.crack1, false);
                case 2 -> screen.renderSpriteLit(x, y, Sprite.crack2, false);
                case 1 -> screen.renderSpriteLit(x, y, Sprite.crack3, false);
            }
        }
    }

    @Override
    public boolean solid() {
        return true;
    }
}
