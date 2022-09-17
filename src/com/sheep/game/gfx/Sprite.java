package com.sheep.game.gfx;

public class Sprite {
    private final int width, height;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    //level
    public static final Sprite wallSprite = new Sprite(0, 0, 16, 16, SpriteSheet.caveTiles);
    public static final Sprite floor = new Sprite(16, 0, 16, 16, SpriteSheet.caveTiles);

    public static final Sprite crack1 = new Sprite(0, 16, 16, 16, SpriteSheet.caveTiles);
    public static final Sprite crack2 = new Sprite(16, 16, 16, 16, SpriteSheet.caveTiles);
    public static final Sprite crack3 = new Sprite(32, 16, 16, 16, SpriteSheet.caveTiles);

    public static final Sprite skull = new Sprite(0, 0, 16, 16, SpriteSheet.caveEntities);

    public static final Sprite chest_closed = new Sprite(0, 16, 16, 16, SpriteSheet.caveEntities);
    public static final Sprite chest_open = new Sprite(0, 2*16, 16, 16, SpriteSheet.caveEntities);

    public static final Sprite door = new Sprite(16, 0, 16, 16, SpriteSheet.caveEntities);

    //mob
    public static final Sprite player = new Sprite(0, 0, 16, 18, SpriteSheet.Mobs);
    public static final Sprite player_walk = new Sprite(16, 0, 16, 18, SpriteSheet.Mobs);

    public static final Sprite husk = new Sprite(0, 18, 16, 18, SpriteSheet.Mobs);
    public static final Sprite husk_walk = new Sprite(16, 18, 16, 18, SpriteSheet.Mobs);

    public static final Sprite demon = new Sprite(0, 2*18, 16, 18, SpriteSheet.Mobs);
    public static final Sprite demon_walk = new Sprite(16, 2*18, 16, 18, SpriteSheet.Mobs);

    public static final Sprite melee_hit_horizontal = new Sprite(16, 16, 16, 16, SpriteSheet.caveEntities);

    //items
    public static final Sprite item_pickaxe = new Sprite(0, 16*2, 16, 16, SpriteSheet.items);
    public static final Sprite item_shotgun = new Sprite(0, 0, 16, 16, SpriteSheet.items);
    public static final Sprite item_medkit = new Sprite(0, 16, 16, 16, SpriteSheet.items);
    public static final Sprite item_map = new Sprite(48, 0, 16, 16, SpriteSheet.items);

    //UI
    public static final Sprite UI_ItemFrame = new Sprite(0, 0, 16, 16, SpriteSheet.UI);
    public static final Sprite UI_ItemFrame_select = new Sprite(16, 0, 16, 16, SpriteSheet.UI);

    public Sprite(int x, int y, int w, int h, SpriteSheet sheet){
        this.width = w;
        this.height = h;
        this.x = x;
        this.y = y;
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
