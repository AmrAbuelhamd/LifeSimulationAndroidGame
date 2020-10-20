package com.blogspot.soyamr.lifesimulation.model;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Utils;


public abstract class GameObject {

    public static final int SEARCH_FOOD_THRESHOLD = 31;
    public static final int SEARCH_PARTNER_THRESHOLD = 49;
    public static final int ANIMAL_FOOD_VISION_RANG = 50;
    public static final int ANIMAL_WOMEN_VISION_RANG = 30;
    public static final int SEARCH_FOOD_OPTIMIZATION_THRESHOLD = 40;//40
    public static final int SEARCH_WOMEN_OPTIMIZATION_THRESHOLD = 20;//20

    public static final int width = Utils.Const.CELL_WIDTH;
    public static final int height = Utils.Const.CELL_HEIGHT;
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
        } else if (this.x > Utils.Const.SCREEN_WIDTH - width) {
            this.x = Utils.Const.SCREEN_WIDTH - width;
        }
        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > Utils.Const.SCREEN_HEIGHT - height) {
            this.y = Utils.Const.SCREEN_HEIGHT - height;
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
