package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import java.util.List;

public class Lion extends Carnivore {
    public Lion() {
    }

    @Override
    public void addChild() {
//        if (Utils.getRandom(0, 2) == 0)
//            model.addChild(new Lion(x, y, model, GenderEnum.MALE));
//        else
//            model.addChild(new Lion(x, y, model, GenderEnum.FEMALE));
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive) {
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.lionImg, x, y, null);
            if (genderEnum == GenderEnum.FEMALE)
                canvas.drawBitmap(model.gameBitmaps.lionImgF, x, y, null);
        }
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -8227049;
        else if (hunger > 30)
            return -6382300;
        else
            return -5262293;
    }
    public static final class Builder extends Carnivore.Builder<Lion, Lion.Builder> {
        protected Lion createObject() {
            return new Lion();
        }
        protected Lion.Builder thisObject() {
            return this;
        }
    }
}
