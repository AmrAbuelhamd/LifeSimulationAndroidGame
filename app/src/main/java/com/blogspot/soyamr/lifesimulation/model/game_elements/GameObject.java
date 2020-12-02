package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Carnivore;

import static com.blogspot.soyamr.lifesimulation.Const.SEARCH_FOOD_THRESHOLD_NORMAL;
import static com.blogspot.soyamr.lifesimulation.Const.SEARCH_PARTNER_THRESHOLD_NORMAL;


public abstract class GameObject /*implements famouseanimaslinterface*/ {

    public static final int ANIMAL_FOOD_VISION_RANG = 50;
    public static final int ANIMAL_WOMEN_VISION_RANG = 30;
    public static final int SEARCH_FOOD_OPTIMIZATION_THRESHOLD = 40;//40
    public static final int SEARCH_WOMEN_OPTIMIZATION_THRESHOLD = 20;//20
    public static final int width = Const.CELL_WIDTH;
    public static final int height = Const.CELL_HEIGHT;
    public static final int[][] moveDirection = new int[][]{
            {0, -1},
            {1, -1},
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
    };
    public static int SEARCH_FOOD_THRESHOLD = SEARCH_FOOD_THRESHOLD_NORMAL;
    public static int SEARCH_PARTNER_THRESHOLD = SEARCH_PARTNER_THRESHOLD_NORMAL;
    protected final Rect rect = new Rect();
    public final Paint paint = new Paint();
    public int distance;
    public Type type;
    public GenderEnum genderEnum;
    public boolean isAlive = true;
    protected int x;
    protected int y;

    public GameObject(Type myType, GenderEnum genderEnum) {
        this.type = myType;
        this.genderEnum = genderEnum;
    }

    public void reachedScreenEdge() {
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

    //getGender(); todo
    public int getY() {
        return y;
    }
    public abstract void makeMeFamous();
    public abstract void updateAdditionalInfoLocation(float mScaleFactor);
    public abstract void drawAdditionalInfo(Canvas canvas);
}
