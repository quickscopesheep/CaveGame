package com.sheep.game.util;

import com.sheep.game.entity.mob.Mob;

public class MathUtil {
    public static float NormalizeX(float x, float y){
        float mag = (float) Math.sqrt((x*x)+(y*y));
        return x/mag;
    }

    public static float NormalizeY(float x, float y){
        float mag = (float) Math.sqrt((x*x)+(y*y));
        return y/mag;
    }

    public static float Distance(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }

    public static float lerp(float a, float b, float t){
        return a + t * (b - a);
    }

    public static float cosInterpolate(float a, float b, float t){
        float theta = t * (float) Math.PI;
        float f = (float)(1f * Math.cos(theta)) * .5f;
        return a * (1f - f) + b * f;
    }

    public static float clamp(float a, float min, float max){
        if(a > max) a = max;
        else if(a < min) a = min;
        return a;
    }

    public boolean collision(float x1, float y1, float w1, float h1,
                             float x2, float y2, float w2, float h2){
        return x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                h1 + y1 > y2;
    }

    public boolean collision(float x1, float y1, float w1, float h1,
                             Mob other){
        float x2 = other.getX();
        float y2 = other.getY();
        float w2 = other.getXBound();
        float h2 = other.getYBoundOffset();

        return x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                h1 + y1 > y2;
    }
}
