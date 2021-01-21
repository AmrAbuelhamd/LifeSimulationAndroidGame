package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public abstract class Plant extends GameObject {
    public Model model;
    int space;
    int xDraw, yDraw;
    Paint textAndRectPaint;

    public Plant() {

        paint.setColor(getMyColor());
        paint.setStyle(Paint.Style.FILL);

        reachedScreenEdge();

        rect.set(this.x, this.y, this.x + width, this.y + height);
        model.putMeHerePlease(this.x, this.y, this);

        textAndRectPaint = new Paint();
        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(300);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(getMyColor());
        xDraw = getX() - 40;
        yDraw = getY() - 20/*- ANIMAL_WOMEN_VISION_RANG * GameObject.height * 1*/;
    }

    @Override
    public void makeMeFamous() {
        model.setFamousAnimal(this);
    }

    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {
        space = (int) (50 / mScaleFactor);
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
        canvas.drawText("Type: " + type, xDraw, yDraw, textAndRectPaint);
    }

    protected static abstract class Builder
            <T extends Plant, B extends Builder<T, B>> extends GameObject.Builder<T, B> {
        public B setCoordinates(int x, int y) {
            if (x == -1 || y == -1) {
                x = Utils.getRandom(0, Const.N) * width;
                y = Utils.getRandom(0, Const.M) * height;
            } else {
                int index = Utils.getRandom(0, moveDirection.length);
                x += moveDirection[index][0] * width;
                y += moveDirection[index][1] * height;
            }
            object.x = x;
            object.y = y;
            return thisObject;
        }

        public B setModel(Model model) {
            object.model = model;
            return thisObject;
        }
    }
}
