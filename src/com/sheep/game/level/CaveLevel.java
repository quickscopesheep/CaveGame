package com.sheep.game.level;

import com.sheep.game.Game;
import com.sheep.game.entity.Door;
import com.sheep.game.entity.Entity;
import com.sheep.game.entity.mob.Chest;
import com.sheep.game.entity.mob.EnemyUnits.Husk;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;
import com.sheep.game.level.tiles.Tile;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.Coord;
import com.sheep.game.util.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaveLevel extends Level {

    //generation settings
    protected static final int randomFillPercent = 50;
    protected static final int smoothGenerations = 5;

    private static final int startAreaInfluence = 3;

    private static int minEnemies = 24;
    private static int minChests = 8;

    protected Coord playerStart;

    protected int[] tileIntegrity;

    protected int floor;

    AudioPlayer ambienceAudio;

    public CaveLevel(int width, int height, long seed, int floor) {
        super(width, height, seed);
        tileIntegrity = new int[width*height];

        this.floor = floor;

        this.ambienceAudio = new AudioPlayer();
        ambienceAudio.loadSound(AudioPlayer.AMBIENCE_CAVE_1);
        ambienceAudio.play();
        ambienceAudio.loop();

        generateLevel();
    }

    public int getSurroundingWallCount(int gridX, int gridY, int radius) {
        int wallCount = 0;
        for (int neighbourX = gridX - ((radius-1)/2); neighbourX <= gridX + ((radius-1)/2); neighbourX ++) {
            for (int neighbourY = gridY - ((radius-1)/2); neighbourY <= gridY + ((radius-1)/2); neighbourY ++) {
                if (neighbourX >= 0 && neighbourX < width && neighbourY >= 0 && neighbourY < height) {
                    if (neighbourX != gridX || neighbourY != gridY) {
                        wallCount += tiles[neighbourY*width+neighbourX] != 0 ? 1 : 0;
                    }
                }
                else {
                    wallCount ++;
                }
            }
        }

        return wallCount;
    }

    protected void smoothMap(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int neighbourCount = getSurroundingWallCount(x, y, 3);

                if(neighbourCount > 4) {
                    tiles[y * width + x] = 1;
                    tileIntegrity[y * width + x] = 4;
                }
                else if(neighbourCount < 4)
                    tiles[y*width+x] = 0;
            }
        }
    }

    void spawnDoors(Random random){
        Coord doorSpawnCoord = new Coord(0, 0);

        int distanceToSpawn = 0;
        switch (Game.settings.difficulty){
            case 0 -> distanceToSpawn = 24;
            default -> distanceToSpawn = 28;
            case 2 -> distanceToSpawn = 32;
        }

        boolean success = false;

        for(int iterations = 0; iterations < 1000; iterations++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);

            if (MathUtil.Distance(x, y, playerStart.x, playerStart.y) > distanceToSpawn) {
                if (getSurroundingWallCount(x, y, 4) == 0) {
                    success = true;
                    doorSpawnCoord.x = x;
                    doorSpawnCoord.y = y;
                }
            }
        }
        if(success) {
            this.Add(new Door(doorSpawnCoord.x * 16, doorSpawnCoord.y * 16, this, floor + 1, false));
            if(floor != 0)
                Add(new Door(playerStart.x * 16, playerStart.y * 16, this, 0, true));
        } else {
            System.out.print("failed to generate Door in seed: " + seed);
            seed = System.currentTimeMillis();
            System.out.println(" , Regenerating level with seed: " + seed);
            generateLevel();
        }
    }

    void spawnEnemies(Random random){
        List<Coord> mobSpawnCoords = new ArrayList<>();

        for(int iterations = 0; iterations < 1000; iterations++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);

            if (MathUtil.Distance(x, y, playerStart.x, playerStart.y) > 8) {
                if (getSurroundingWallCount(x, y, 4) == 0) {
                    if (mobSpawnCoords.size() > 0) {
                        boolean valid = true;
                        for (Coord c : mobSpawnCoords) {
                            if (MathUtil.Distance(x, y, c.x, c.y) < 3) {
                                valid = false;
                                break;
                            }
                        }
                        if (valid) {
                            mobSpawnCoords.add(new Coord(x, y));
                            if (mobSpawnCoords.size() == minEnemies) break;
                        }
                    } else {
                        mobSpawnCoords.add(new Coord(x, y));
                    }
                }
            }
        }

        for(Coord c : mobSpawnCoords) {
            this.Add(new Husk(c.x * 16, c.y * 16, this));
        }

    }

    void spawnChests(Random random){
        List<Coord> chestSpawnCoords = new ArrayList<>();

        for(int iterations = 0; iterations < 1000; iterations++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);

            if (MathUtil.Distance(x, y, playerStart.x, playerStart.y) > 16) {
                if (getSurroundingWallCount(x, y, 4) == 0) {
                    if (chestSpawnCoords.size() > 0) {
                        boolean valid = true;
                        for (Coord c : chestSpawnCoords) {
                            if (MathUtil.Distance(x, y, c.x, c.y) < 8) {
                                valid = false;
                                break;
                            }
                        }
                        if (valid) {
                            chestSpawnCoords.add(new Coord(x, y));
                            if (chestSpawnCoords.size() == minChests) break;
                        }
                    } else {
                        chestSpawnCoords.add(new Coord(x, y));
                    }
                }
            }
        }

        for(Coord c : chestSpawnCoords) {
            this.Add(new Chest(c.x * 16, c.y * 16, this));
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

        playerStart = new Coord(centerX,  centerY);

        for(int i = 0; i < smoothGenerations; i++){
            smoothMap();
        }

        minEnemies += Game.settings.difficulty*8;
        minChests -= (Game.settings.difficulty-1);

        spawnDoors(random);
        spawnChests(random);
        spawnEnemies(random);
    }

    @Override
    public void render(int xScroll, int yScroll, Screen screen) {
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

        screen.renderTextLit(playerStart.x * 16 - (7*8)/2, playerStart.y * 16 - 24,"Floor " + (floor + 1));

        for(Entity e : entities){
            e.render(screen);
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

    public Coord getPlayerStart() {
        return playerStart;
    }

    public int[] getTileIntegrity() {
        return tileIntegrity;
    }

    public int getFloor() {
        return floor;
    }
}
