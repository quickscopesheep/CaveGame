package com.sheep.game.gfx;

import com.sheep.game.Game;
import com.sheep.game.util.MathUtil;

import java.awt.*;
import java.util.Arrays;

public class Screen {
    public static final float lightDst = 225;

    private final int width, height;
    public int[] pixels;

    private int xOffset, yOffset;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void renderSpriteLit(int xp, int yp, Sprite sprite, boolean flipped){
        float lightLevel = 1f;
        xp -= xOffset;
        yp -= yOffset;

        float dst = MathUtil.Distance(xp, yp, Game.player.getX() - xOffset, Game.player.getY() - yOffset);

        lightLevel = MathUtil.cosInterpolate(0, 1, dst/lightDst);
        lightLevel = MathUtil.clamp(lightLevel, 0, 1);

        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp ;

            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int xs;
                if(flipped)
                    xs = (sprite.getWidth() -1) - x;
                else
                    xs = x;

                if(xa < -sprite.getWidth() || xa > width || ya < 0 || ya > width) break;
                if(xa < 0) continue;
                if(xa > width - 1) continue;
                if(ya > height - 1) continue;

                int col = sprite.pixels[y * sprite.getWidth() + xs];
                if(col != 0xffff00ff){

                    Color originalColour = new Color(col);
                    int r = originalColour.getRed(), g = originalColour.getGreen(), b = originalColour.getBlue();
                    r *= lightLevel;
                    g *= lightLevel;
                    b *= lightLevel;

                    Color newColour = new Color(r, g, b);

                    pixels[ya * width + xa] = newColour.getRGB();
                }
            }
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean flipped){
        xp -= xOffset;
        yp -= yOffset;

        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp ;

            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int xs;
                if(flipped)
                    xs = (sprite.getWidth() -1) - x;
                else
                    xs = x;

                if(xa < -sprite.getWidth() || xa > width || ya < 0 || ya > width) break;
                if(xa < 0) continue;
                if(xa > width - 1) continue;
                if(ya > height - 1) continue;

                int col = sprite.pixels[y * sprite.getWidth() + xs];
                if(col != 0xffff00ff)
                    pixels[ya * width + xa] = col;
            }
        }
    }

    public void renderSpriteFixed(int xp, int yp, Sprite sprite, boolean flipped){

        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp ;

            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int xs;
                if(flipped)
                    xs = (sprite.getWidth() -1) - x;
                else
                    xs = x;

                if(xa < -sprite.getWidth() || xa > width || ya < 0 || ya > width) break;
                if(xa < 0) continue;
                if(xa > width - 1) continue;
                if(ya > height - 1) continue;

                int col = sprite.pixels[y * sprite.getWidth() + xs];
                if(col != 0xffff00ff)
                    pixels[ya * width + xa] = col;
            }
        }
    }

    public void clear(){
        Arrays.fill(pixels, 0x000000);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setOffset(int x, int y){
        this.xOffset = x;
        this.yOffset = y;
    }

}
