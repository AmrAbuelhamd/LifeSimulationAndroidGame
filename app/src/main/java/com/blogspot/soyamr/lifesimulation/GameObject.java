package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import java.util.Random;


public abstract class GameObject {
    static final int width = CONST.CELL_WIDTH;
    static final int height = CONST.CELL_HEIGHT;
    static final Random rand = new Random();
    final Rect rect = new Rect();
    int x;
    int y;

    abstract Paint getPaint();

    String getKey() {
        return x + " " + y;//// string key = x+" "+y // cartej -> hash
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, getPaint());
    }

    static int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

}
