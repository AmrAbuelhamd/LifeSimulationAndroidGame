package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FamousAnimal extends GameObject {
    Animal animal;
    static final int squareDiameter = ANIMAL_SEARCH_RANG / 2;
    final Paint paint;

    public FamousAnimal(Animal animal) {
        this.animal = animal;
        paint = new Paint();

        paint.setColor(Color.YELLOW);
        paint.setTextSize(30);
    }


    @Override
    public void draw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Hunger: " + animal.hunger, x, y, paint);
        canvas.drawText("x: " + animal.x, x, y + 30, paint);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawRect(rect, paint);
    }

    void update() {
        x = animal.getX() + 10;
        y = animal.getY();

        rect.set(x - squareDiameter * width,
                y - squareDiameter * height,

                x + squareDiameter * width,
                y + squareDiameter * height);
    }

    @Override
    Paint getPaint() {
        return paint;
    }
}
