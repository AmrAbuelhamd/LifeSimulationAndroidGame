package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class FamousAnimal extends GameObject {
    Animal animal;
    static final int squareDiameterFood = ANIMAL_FOOD_VISION_RANG / 2;
    static final int squareDiameterWomen = ANIMAL_WOMEN_VISION_RANG / 2;
    final Paint paint;
    final Rect rectWomenVision = new Rect();
    final Rect rectFoodVision = new Rect();

    public FamousAnimal(Animal animal) {
        this.animal = animal;
        paint = new Paint();

        paint.setTextSize(30);
        paint.setAntiAlias(true);
    }


    @Override
    public void draw(Canvas canvas) {

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.CYAN);
        canvas.drawRect(rectFoodVision, paint);

        if (animal instanceof MaleAnimal) {
            paint.setColor(Color.RED);
            canvas.drawRect(rectWomenVision, paint);
        }

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        canvas.drawText("Hunger: " + animal.hunger, x, y, paint);
        canvas.drawText("in relationship: " + animal.inRelation, x, y + 30, paint);
        canvas.drawText("idontwant: " + animal.iDoNotWant, x, y + 60, paint);
    }

    void update() {
        x = animal.getX();
        y = animal.getY();

        rectFoodVision.set(x - squareDiameterFood * width,
                y - squareDiameterFood * height,

                x + squareDiameterFood * width,
                y + squareDiameterFood * height);

        rectWomenVision.set(x - squareDiameterWomen * width,
                y - squareDiameterWomen * height,

                x + squareDiameterWomen * width,
                y + squareDiameterWomen * height);

        x = animal.getX() + squareDiameterFood * width + 15;
        y = animal.getY() - squareDiameterFood * width
        ;
    }

}
