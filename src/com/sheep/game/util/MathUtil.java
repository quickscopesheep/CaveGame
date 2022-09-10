package com.sheep.game.util;

import com.sheep.game.entity.Entity;
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
}
