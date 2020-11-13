package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;

import static com.blogspot.soyamr.lifesimulation.Const.SEARCH_FOOD_THRESHOLD_NORMAL;
import static com.blogspot.soyamr.lifesimulation.Const.SEARCH_PARTNER_THRESHOLD_NORMAL;


public abstract class GameObject {

    public static  int SEARCH_FOOD_THRESHOLD = SEARCH_FOOD_THRESHOLD_NORMAL;
    public static  int SEARCH_PARTNER_THRESHOLD = SEARCH_PARTNER_THRESHOLD_NORMAL;
    public static final int ANIMAL_FOOD_VISION_RANG = 50;
    public static final int ANIMAL_WOMEN_VISION_RANG = 30;
    public static final int SEARCH_FOOD_OPTIMIZATION_THRESHOLD = 40;//40
    public static final int SEARCH_WOMEN_OPTIMIZATION_THRESHOLD = 20;//20
    public int distance;
    public static final int width = Const.CELL_WIDTH;
    public static final int height = Const.CELL_HEIGHT;
    protected final Rect rect = new Rect();
    protected final Paint paint = new Paint();
    protected int x;
    protected int y;
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
        } else if (this.x > Const.FIELD_WIDTH - width) {
            this.x = Const.FIELD_WIDTH - width;
        }
        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > Const.FIELD_HEIGHT - height) {
            this.y = Const.FIELD_HEIGHT - height;
        }
    }

    public String getKey() {
        return x + " " + y;
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
