package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;
import android.graphics.Paint;

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


        textAndRectPaint = new Paint();
        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(300);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(getMyColor());

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
            object.x = x;
            object.y = y;
            return thisObject;
        }

        public B setModel(Model model) {
            object.model = model;
            return thisObject;
        }

        @Override
        public T build() {
            super.build();
            object.model.putMeHerePlease(object.getX(), object.getY(), object);
            object.xDraw = object.getX() - 40;
            object.yDraw = object.getY() - 20/*- ANIMAL_WOMEN_VISION_RANG * GameObject.height * 1*/;
            object.rect.set(object.x, object.y, object.x + width, object.y + height);

            return object;
        }
    }
}
