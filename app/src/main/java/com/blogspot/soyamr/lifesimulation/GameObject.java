package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import java.util.Random;


public abstract class GameObject {
    int x;
    int y;
    int key;
    final Rect rect = new Rect();
    static final int width = CONST.CELL_WIDTH;
    static final int height = CONST.CELL_HEIGHT;
    static final Random rand = new Random();

    abstract Paint getPaint();

    int getKey() {//todo change the key mechanism
        return key;//// string key = x+" "+y // cartej -> hash
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, getPaint());
    }

}
