package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.MalePerson;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.person.Person;

public class Apple extends Plant {
//    public Apple(int x, int y, Model model) {
//        super(x, y, model, Type.APPLE);
//    }

    public static final class Builder extends Plant.Builder<Apple, Apple.Builder> {
        protected Apple createObject() {
            return new Apple();
        }
        protected Apple.Builder thisObject() {
            return this;
        }
    }
    @Override
    public int getMyColor() {
        return -4246004;
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            canvas.drawBitmap(model.gameBitmaps.appleImg, x, y, null);
    }
}
