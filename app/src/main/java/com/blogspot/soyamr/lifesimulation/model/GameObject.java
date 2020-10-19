package com.blogspot.soyamr.lifesimulation.model;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;


public abstract class GameObject {

    public static final int width = Const.CELL_WIDTH;
    public static final int height = Const.CELL_HEIGHT;
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

    void reachedScreenEdge() {
        // When the game's character touches the edge of the screen, then stop it.
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x > Const.SCREEN_WIDTH - width) {
            this.x = Const.SCREEN_WIDTH - width;
        }
        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > Const.SCREEN_HEIGHT - height) {
            this.y = Const.SCREEN_HEIGHT - height;
        }
    }
    abstract Paint getPaint();

    public String getKey() {
        return x + " " + y;
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, getPaint());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
