package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.ANIMAL_FOOD_VISION_RANG;
import static com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject.ANIMAL_WOMEN_VISION_RANG;

public class AnimalDataManger {
    final Rect rectWomenVision = new Rect();
    final Rect rectFoodVision = new Rect();
    Animal animal;
    Rect rect = new Rect();
    int x, y;
    Paint womenPaintRect;
    Paint manPaintRect;
    Paint textAndRectPaint;
    Paint paint = new Paint();
    ;
    int size = 50;
    int right = 50;
    int space;

    public AnimalDataManger(Animal animal) {
        this.animal = animal;
        womenPaintRect = new Paint();
        manPaintRect = new Paint();
        textAndRectPaint = new Paint();

        manPaintRect.setStyle(Paint.Style.STROKE);
        manPaintRect.setStrokeWidth(2);


        womenPaintRect.setStyle(Paint.Style.STROKE);
        womenPaintRect.setStrokeWidth(2);
        womenPaintRect.setColor(Color.RED);

        textAndRectPaint.setStyle(Paint.Style.FILL);
        textAndRectPaint.setTextSize(30);
        textAndRectPaint.setAntiAlias(true);
        textAndRectPaint.setColor(animal.getMyColor());

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.YELLOW);
    }

    public void draw(Canvas canvas) {
        if (!animal.myFoodMenu.isEmpty())
            manPaintRect.setColor(animal.myFoodMenu.get(0).getMyColor());
        else manPaintRect.setColor(Color.CYAN);
        canvas.drawRect(rectFoodVision, manPaintRect);

        if (animal.genderEnum == GenderEnum.MALE) {
            if(animal.genderOperator.myLove!=null)
                womenPaintRect.setColor(animal.genderOperator.myLove.getMyColor());
            canvas.drawRect(rectWomenVision, womenPaintRect);
        }

        canvas.drawText("Hunger                 : " + animal.hunger, x, y - 2 * space, textAndRectPaint);
        canvas.drawText("Hunger CTR       : " + animal.ihth, x, y - space, textAndRectPaint);
        canvas.drawText("in relationship  : " + animal.genderOperator.inRelation, x, y, textAndRectPaint);
        canvas.drawText("idontwant           : " + animal.genderOperator.iDoNotWant, x, y + space, textAndRectPaint);
        canvas.drawText("idontwant CTR : " + animal.genderOperator.rsidwv, x, y + 2 * space, textAndRectPaint);
        canvas.drawText("in one direction: " + (animal.mtodth < animal.movingToOneDirectionThreshold)
                , x, y + 3 * space, textAndRectPaint);
        canvas.drawText("my menu size    : " + animal.myFoodMenu.size(), x, y + 4 * space, textAndRectPaint);
        canvas.drawText("myType    : " + animal.type, x, y + 5 * space, textAndRectPaint);
        canvas.drawText("myGender    : " + animal.genderEnum, x, y + 6 * space, textAndRectPaint);
        //put rectangle around current animal
        canvas.drawRect(rect, paint);
        //put rectangle around target
        if (!animal.myFoodMenu.isEmpty()) {
            rect.set(animal.myFoodMenu.get(0).getX() - GameObject.width,
                    animal.myFoodMenu.get(0).getY() - GameObject.height,
                    animal.myFoodMenu.get(0).getX() + GameObject.width * 2,
                    animal.myFoodMenu.get(0).getY() + GameObject.height * 2);
            canvas.drawRect(rect, womenPaintRect);
        }
    }

    public void update(float mScaleFactor) {
        x = animal.getX();
        y = animal.getY();

        //to highlight the selected animal.
        rect.set(x - GameObject.width, y - GameObject.height, x + GameObject.width * 2, y + GameObject.height * 2);

        rectFoodVision.set(x - ANIMAL_FOOD_VISION_RANG * GameObject.width,
                y - ANIMAL_FOOD_VISION_RANG * GameObject.height,
                x + ANIMAL_FOOD_VISION_RANG * GameObject.width,
                y + ANIMAL_FOOD_VISION_RANG * GameObject.height);

        rectWomenVision.set(x - ANIMAL_WOMEN_VISION_RANG * GameObject.width,
                y - ANIMAL_WOMEN_VISION_RANG * GameObject.height,
                x + ANIMAL_WOMEN_VISION_RANG * GameObject.width,
                y + ANIMAL_WOMEN_VISION_RANG * GameObject.height);

        x = animal.getX() - (ANIMAL_WOMEN_VISION_RANG * GameObject.width) + 80;
        y = animal.getY() - ANIMAL_WOMEN_VISION_RANG * GameObject.height * 3;

        textAndRectPaint.setTextSize(size / mScaleFactor);
        space = (int) (50 / mScaleFactor);
    }


}
