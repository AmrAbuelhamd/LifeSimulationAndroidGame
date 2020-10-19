package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;

public class FamousAnimal {
    Animal animal;

    final static Paint paint;

    static {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(30);
    }

    public FamousAnimal(Animal animal) {
        this.animal = animal;
    }

    public void draw(Canvas canvas) {
        canvas.drawText("Hunger: " + animal.hunger, animal.getX() + 10, animal.getY(), paint);
        canvas.drawText("x: " + animal.x, animal.getX() + 10, animal.getY() + 30, paint);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        final Rect rect = new Rect();
        rect.set(animal.x - (Animal.ANIMAL_SEARCH_RANG / 2) * GameObject.width,
                animal.y - (Animal.ANIMAL_SEARCH_RANG / 2) * GameObject.height,
                animal.x + (Animal.ANIMAL_SEARCH_RANG / 2) * GameObject.width,
                animal.y + (Animal.ANIMAL_SEARCH_RANG / 2) * GameObject.height);

        canvas.drawRect(rect, paint);
    }

}
