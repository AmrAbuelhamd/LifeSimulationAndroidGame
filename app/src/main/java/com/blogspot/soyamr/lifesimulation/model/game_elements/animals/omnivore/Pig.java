package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;

import java.util.List;

public class Pig extends Omnivore {

    public Pig(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.PIG, genderEnum,
                List.of(Type.RABBIT, Type.CARROT));
    }
    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Pig(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Pig(x, y, model, GenderEnum.FEMALE));
    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.pigImg, x, y, null);
            else
                canvas.drawBitmap(model.gameBitmaps.pigImgF, x, y, null);

    }
    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -720809;
        else if (hunger > 30)
            return -49023;
        else
            return -32597;
    }
}
