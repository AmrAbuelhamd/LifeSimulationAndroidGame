package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;

public final class Fox extends Carnivore {
//    Bitmap bitmap;

    public Fox() {
    }

    @Override
    public void addChild() {
//        if (Utils.getRandom(0, 2) == 0)
//            model.addChild(new Fox(x, y, model, GenderEnum.MALE));
//        else
//            model.addChild(new Fox(x, y, model, GenderEnum.FEMALE));
    }

    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -688361;
        else if (hunger > 30)
            return -415707;
        else
            return -278483;
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.foxImg, x, y, null);
            else if (genderEnum == GenderEnum.FEMALE)
                canvas.drawBitmap(model.gameBitmaps.foxImgF, x, y, null);
    }

    public static final class Builder extends Carnivore.Builder<Fox, Builder> {
        protected Fox createObject() {
            return new Fox();
        }
        protected Builder thisObject() {
            return this;
        }
    }
}
