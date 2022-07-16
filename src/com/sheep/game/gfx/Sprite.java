package com.sheep.game.gfx;

public class Sprite {
    private final int width, height;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static final Sprite wallSprite = new Sprite(0, 0, 16, 16, SpriteSheet.caveTiles);

    public static final Sprite innerWallSprite = new Sprite(2, 0, 16, 16, SpriteSheet.caveTiles);
    public static final Sprite floor = new Sprite(1, 0, 16, 16, SpriteSheet.caveTiles);

    public static final Sprite player = new Sprite(0, 0, 16, 18, SpriteSheet.Mobs);
    public static final Sprite player_walk = new Sprite(1, 0, 16, 18, SpriteSheet.Mobs);

    public static final Sprite husk = new Sprite(0, 1, 16, 18, SpriteSheet.Mobs);
    public static final Sprite husk_walk = new Sprite(1, 1, 16, 18, SpriteSheet.Mobs);


    public Sprite(int x, int y, int w, int h, SpriteSheet sheet){
        this.width = w;
        this.height = h;
        this.x = x * w;
        this.y = y * h;
        this.sheet = sheet;

        pixels = new int[w * h];
        load();
    }

    public Sprite(int w, int h, int Colour){
        width = w;
        height = h;
        pixels = new int[w * h];

        fillColour(Colour);
    }

    private void fillColour(int colour){
        for(int i = 0; i < width*height; i++){
                pixels[i] = colour;
        }
    }

    private void load(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                pixels[y*width+x] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
            }
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
