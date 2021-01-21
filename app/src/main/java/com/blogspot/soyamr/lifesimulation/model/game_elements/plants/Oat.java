package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Oat extends Plant {
//    public Oat(int x, int y, Model model) {
//        super(x, y, model,Type.OAT);
//    }


    public static final class Builder extends Plant.Builder<Oat, Oat.Builder> {
        protected Oat createObject() {
            return new Oat();
        }
        protected Oat.Builder thisObject() {
            return this;
        }
    }

    @Override
    public int getMyColor() {
       return -6190977;
    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            canvas.drawBitmap(model.gameBitmaps.oatImg, x, y, null);
    }
}
