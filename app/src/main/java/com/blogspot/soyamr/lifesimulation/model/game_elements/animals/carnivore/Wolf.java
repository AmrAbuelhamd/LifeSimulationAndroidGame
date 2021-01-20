package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import java.util.List;

public class Wolf extends Carnivore {
    public Wolf(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.WOLF, genderEnum,
                List.of(Type.MOUSE));
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Wolf(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Wolf(x, y, model, GenderEnum.FEMALE));
    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.wolfImg, x, y, null);
            else
                canvas.drawBitmap(model.gameBitmaps.wolfImgF, x, y, null);
    }
    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -10453621;
        else if (hunger > 30)
            return -8875876;
        else
            return -7297874;
    }
}
