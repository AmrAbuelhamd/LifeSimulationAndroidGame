package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public abstract class GameObject {

    static final int width = Const.CELL_WIDTH;
    static final int height = Const.CELL_HEIGHT;
    final Rect rect = new Rect();
    int x;
    int y;
    static final int[][] moveDirection = new int[][]{
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
    };
    abstract Paint getPaint();

    String getKey() {
        return x + " " + y;
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, getPaint());
    }



}
