package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class FamousAnimal extends GameObject {
    Animal animal;
    final Rect rectWomenVision = new Rect();
    final Rect rectFoodVision = new Rect();
    Paint womenPaintRect;
    Paint manPaintRect;
    Paint textAndRectPaint;

    public FamousAnimal(Animal animal) {
        this.animal = animal;

        womenPaintRect = new Paint();
        manPaintRect = new Paint();
        textAndRectPaint = new Paint();

        manPaintRect.setStyle(Paint.Style.STROKE);
        manPaintRect.setStrokeWidth(2);
        manPaintRect.setColor(Color.CYAN);

        womenPaintRect.setStyle(Paint.Style.STROKE);
        womenPaintRect.setStrokeWidth(2);
        womenPaintRect.setColor(Color.RED);

        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(30);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(Color.YELLOW);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.YELLOW);

    }


    @Override
    public void draw(Canvas canvas) {

        canvas.drawRect(rectFoodVision, manPaintRect);

        if (animal instanceof MaleAnimal) {
            canvas.drawRect(rectWomenVision, womenPaintRect);
        }


        canvas.drawText("Hunger                 : " + animal.hunger, x, y - 2 * space, textAndRectPaint);
        canvas.drawText("Hunger CTR       : " + animal.ihth, x, y - space, textAndRectPaint);
        canvas.drawText("in relationship  : " + animal.inRelation, x, y, textAndRectPaint);
        canvas.drawText("idontwant           : " + animal.iDoNotWant, x, y + space, textAndRectPaint);
        canvas.drawText("idontwant CTR : " + animal.rsidwv, x, y + 2 * space, textAndRectPaint);
        canvas.drawText("one direction    : " + animal.mtodth, x, y + 3 * space, textAndRectPaint);
        canvas.drawRect(rect, paint);
    }

    int size = 50;
    int right = 50;
    int space;

    void update(float mScaleFactor) {
        x = animal.getX();
        y = animal.getY();

        //to highlight the selected animal.
        rect.set(x - width, y - height, x + width * 2, y + height * 2);

        rectFoodVision.set(x - ANIMAL_FOOD_VISION_RANG * width,
                y - ANIMAL_FOOD_VISION_RANG * height,
                x + ANIMAL_FOOD_VISION_RANG * width,
                y + ANIMAL_FOOD_VISION_RANG * height);

        rectWomenVision.set(x - ANIMAL_WOMEN_VISION_RANG * width,
                y - ANIMAL_WOMEN_VISION_RANG * height,
                x + ANIMAL_WOMEN_VISION_RANG * width,
                y + ANIMAL_WOMEN_VISION_RANG * height);

        x = animal.getX() - (ANIMAL_WOMEN_VISION_RANG * width)+80;
        y = animal.getY() - ANIMAL_WOMEN_VISION_RANG * height;

        textAndRectPaint.setTextSize(size / mScaleFactor);
        space = (int) (50 / mScaleFactor);
    }

}
