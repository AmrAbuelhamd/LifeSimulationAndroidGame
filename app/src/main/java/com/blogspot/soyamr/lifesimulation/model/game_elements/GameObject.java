package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;

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
    public final Rect rect = new Rect();
    public final Paint paint = new Paint();
    public int distance;
    public Type type;
    public GenderEnum genderEnum = GenderEnum.BOTH;
    public boolean isAlive = true;
    protected int x = -1;
    protected int y = -1;
    protected Bitmap image;

    protected GameObject() {
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
        if (isAlive)
            canvas.drawBitmap(image, x, y, null);
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

    public abstract int getMyColor();

    protected static abstract class Builder
            <T extends GameObject, B extends Builder<T, B>> {
        protected T object;
        protected B thisObject;

        public Builder() {
            object = createObject();
            thisObject = thisObject();
        }

        protected abstract T createObject();

        protected abstract B thisObject();

        public B setType(Type type) {
            object.type = type;
            return thisObject;
        }

        public B setImage(Bitmap image) {
            object.image = image;
            return thisObject;
        }

        public T build() {
            if (object.x == -1 && object.y == -1) {
                object.x = Utils.getRandom(0, Const.N) * width;
                object.y = Utils.getRandom(0, Const.M) * height;
            }
            return object;
        }
    }
}
