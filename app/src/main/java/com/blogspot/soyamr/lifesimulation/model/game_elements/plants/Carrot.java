package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Carrot extends Plant {
//    public Carrot(int x, int y, Model model) {
//        super(x, y, model, Type.CARROT);
//    }


    public static final class Builder extends Plant.Builder<Carrot, Carrot.Builder> {
        protected Carrot createObject() {
            return new Carrot();
        }
        protected Carrot.Builder thisObject() {
            return this;
        }
    }
    @Override
    public int getMyColor() {
        return -1683200;
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            canvas.drawBitmap(model.gameBitmaps.carrotImg, x, y, null);
    }
}
