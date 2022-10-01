package com.sheep.game.level;

import com.sheep.game.entity.Entity;
import com.sheep.game.gfx.Screen;
import com.sheep.game.util.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossLevel extends CaveLevel{
    private static final int border = 2;
    private static final int maxStrucures = 3;

    public BossLevel(int width, int height, long seed, int floor) {
        super(width, height, seed, floor);
    }

    void generateStructure(int xp, int yp, Structure s){
        int ax = xp - s.width/2;
        int ay = yp - s.height/2;

        System.out.println("spawning structure at: " + ax + " , " + ay);

        for(int y = 0; y < s.height; y++){
            for (int x = 0; x < s.width; x++){
                if(ax+x > 0 | ax+x < width-1 || ay+y > 0 || ay+y < width-1){
                    if(s.tiles[y*s.width+x] == 1){
                        tiles[(ay+y) * width + (ax+x)] = 1;
                        tileIntegrity[(ay+y) * width + (ax+x)] = 4;
                    }
                }
            }
        }
    }

    void generateStructures(Random random){
        List<Structure> blueprints = Structure.loadStructures("res/structures/BossLevelStructures.json");
        List<Coord> structureCoords = new ArrayList<>();

        int centerX = width/2, centerY = height/2;

        for(int iterations = 0; iterations < 1000; iterations++){

            int x = random.nextInt(4, width - 5);
            int y = random.nextInt(4, height - 5);

            if(getSurroundingWallCount(x, y, 5) == 0)
                    if (Math.sqrt((centerX - x) * (centerY - x) + (centerX - y) + (centerY - y)) > 5) {
                        structureCoords.add(new Coord(x, y));
                        if(structureCoords.size() >= maxStrucures) break;
                    }
        }

        for (Coord c : structureCoords){
            generateStructure(c.x, c.y, blueprints.get(random.nextInt(blueprints.size())));
        }
    }

    @Override
    protected void generateLevel() {
        Random random = new Random(seed);

        int centerX = width/2, centerY = height/2;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(x < border || x > width-1 - border || y < border || y > height - 1 - border)
                    tiles[y*width+x] = 1;

                if(x < border + 2 || x > width-1 - border - 2 || y < border + 2 || y > height - 1 - border - 2){
                    tiles[y*width+x] = random.nextInt(100) > randomFillPercent ? 0 : 1;
                }
            }
        }

        for(int i = 0; i < smoothGenerations; i++){
            smoothMap();
        }

        generateStructures(random);

        playerStart = new Coord(centerX - width/4,  centerY);

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                if(x == 0 || x == width-1 || y == 0 || y == height - 1)
                    tiles[y*width+x] = 1;
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

        for(Entity e : entities){
            e.render(screen);
        }
    }
}
