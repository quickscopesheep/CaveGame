package com.sheep.game.level.CaveLevel;

import com.sheep.game.level.Level;
import com.sheep.game.level.tiles.Tile;

import javax.swing.*;
import java.util.Random;

public class CaveLevel extends Level {

    //generation settings
    private static final int randomFillPercent = 50;
    private static final int smoothGenerations = 5;

    private static final int startAreaInfluence = 3;

    private final long seed;

    public CaveLevel(int width, int height, long seed) {
        super(width, height);
        this.seed = seed;
    }

    public int getSurroundingWallCount(int gridX, int gridY) {
        int wallCount = 0;
        for (int neighbourX = gridX - 1; neighbourX <= gridX + 1; neighbourX ++) {
            for (int neighbourY = gridY - 1; neighbourY <= gridY + 1; neighbourY ++) {
                if (neighbourX >= 0 && neighbourX < width && neighbourY >= 0 && neighbourY < height) {
                    if (neighbourX != gridX || neighbourY != gridY) {
                        wallCount += tiles[neighbourY*width+neighbourX];
                    }
                }
                else {
                    wallCount ++;
                }
            }
        }

        return wallCount;
    }

    private void smoothMap(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int neighbourCount = getSurroundingWallCount(x, y);

                if(neighbourCount > 4)
                    tiles[y*width+x] = 1;
                else if(neighbourCount < 4)
                    tiles[y*width+x] = 0;
            }
        }
    }

    @Override
    protected void generateLevel() {
        super.generateLevel();

        Random random = new Random(seed);

        int centerX = width/2, centerY = height/2;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(x == 0 || x == width-1 || y == 0 || y == height - 1)
                    tiles[y*width+x] = 1;
                else
                    tiles[y*width+x] = random.nextInt(100) > randomFillPercent ? 0 : 1;

                if(Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) + (centerY - y)) < startAreaInfluence){
                    tiles[y*width+x] = 0;
                }
            }
        }

        for(int i = 0; i < smoothGenerations; i++){
            smoothMap();
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || x > width - 1 || y < 0 || y > height - 1)
            return Tile.voidTile;

        if(tiles[x+y*width] == 1) return Tile.wallTile;
        else return Tile.floorTile;
    }

    public long getSeed() {
        return seed;
    }
}
