package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class FamousAnimal extends GameObject {
    Animal animal;
    final Rect rectWomenVision = new Rect();
    final Rect rectFoodVision = new Rect();

    public FamousAnimal(Animal animal) {
        this.animal = animal;


        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
        paint.setAntiAlias(true);
    }


    @Override
    public void draw(Canvas canvas) {
        paint.setStrokeWidth(2);
        paint.setColor(Color.CYAN);
        canvas.drawRect(rectFoodVision, paint);

        if (animal instanceof MaleAnimal) {
            paint.setColor(Color.RED);
            canvas.drawRect(rectWomenVision, paint);
        }

        paint.setColor(Color.YELLOW);
        canvas.drawText("Hunger: " + animal.hunger, x, y - 10, paint);
        canvas.drawText("in relationship: " + animal.inRelation, x, y + 20, paint);
        canvas.drawText("idontwant: " + animal.iDoNotWant, x, y + 50, paint);
        canvas.drawText("one direction: " + animal.mtodth, x, y + 80, paint);
    }

    void update() {
        x = animal.getX();
        y = animal.getY();

        rectFoodVision.set(x - ANIMAL_FOOD_VISION_RANG * width,
                y - ANIMAL_FOOD_VISION_RANG * height,

                x + ANIMAL_FOOD_VISION_RANG * width,
                y + ANIMAL_FOOD_VISION_RANG * height);

        rectWomenVision.set(x - ANIMAL_WOMEN_VISION_RANG * width,
                y - ANIMAL_WOMEN_VISION_RANG * height,

                x + ANIMAL_WOMEN_VISION_RANG * width,
                y + ANIMAL_WOMEN_VISION_RANG * height);

        x = animal.getX() - (ANIMAL_WOMEN_VISION_RANG * height)/4;
        y = animal.getY() - ANIMAL_WOMEN_VISION_RANG * height;
    }

}
