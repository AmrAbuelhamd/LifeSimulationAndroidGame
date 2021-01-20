package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Oat extends Plant {
    public Oat(int x, int y, Model model) {
        super(x, y, model,Type.OAT);
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
