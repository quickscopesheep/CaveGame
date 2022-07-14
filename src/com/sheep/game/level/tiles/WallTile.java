package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.CaveLevel.CaveLevel;
import com.sheep.game.level.Level;

public class WallTile extends Tile{
    public WallTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        screen.renderSpriteLit(x, y, this.sprite, false);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
