package com.blogspot.soyamr.lifesimulation.model.game_elements.plants;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

public class Apple extends Plant {
    public Apple(int x, int y, Model model) {
        super(x, y, model, Type.APPLE);
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
