package com.sheep.game.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    public final int width, height;
    public int[] pixels;

    public static final SpriteSheet caveTiles = new SpriteSheet("/sprites/caveTiles.png", 64, 64);
    public static final SpriteSheet caveEntities = new SpriteSheet("/sprites/caveEntities.png", 64, 64);
    public static final SpriteSheet Mobs = new SpriteSheet("/sprites/Mobs.png", 64, 72);

    public SpriteSheet(String path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;

        pixels = new int[width*height];
        load();
    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
