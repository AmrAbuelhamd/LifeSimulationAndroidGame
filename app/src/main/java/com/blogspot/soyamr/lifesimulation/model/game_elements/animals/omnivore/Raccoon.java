package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;

import java.util.List;

public class Raccoon extends Omnivore {

//    public Raccoon(int x, int y, Model model, GenderEnum genderEnum) {
//        super(x, y, model, Type.RACCOON, genderEnum,
//                List.of(Type.MOUSE, Type.OAT));
//    }

    public static final class Builder extends Omnivore.Builder<Raccoon, Raccoon.Builder> {
        protected Raccoon createObject() {
            return new Raccoon();
        }
        protected Raccoon.Builder thisObject() {
            return this;
        }
    }
    @Override
    public void addChild() {
//        if (Utils.getRandom(0, 2) == 0)
//            model.addChild(new Raccoon(x, y, model, GenderEnum.MALE));
//        else
//            model.addChild(new Raccoon(x, y, model, GenderEnum.FEMALE));
    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.raccoonImg, x, y, null);
            else
                canvas.drawBitmap(model.gameBitmaps.raccoonImgF, x, y, null);
    }
    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -16743537;
        else if (hunger > 30)
            return -16738393;
        else
            return -16732991;
    }
}
