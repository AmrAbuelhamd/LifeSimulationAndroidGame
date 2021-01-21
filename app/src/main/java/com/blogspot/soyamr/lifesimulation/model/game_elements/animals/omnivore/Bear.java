package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.omnivore;

import android.graphics.Canvas;
import android.view.MenuItem;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Herbivore;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore.Rabbit;

import java.util.List;

public class Bear extends Omnivore{
//    public Bear(int x, int y, Model model, GenderEnum genderEnum) {
//        super(x, y, model, Type.BEAR, genderEnum,
//                List.of(Type.APPLE, Type.DEER));
//    }

    public static final class Builder extends Omnivore.Builder<Bear, Bear.Builder> {
        protected Bear createObject() {
            return new Bear();
        }
        protected Bear.Builder thisObject() {
            return this;
        }
    }
    @Override
    public void addChild() {
//        if (Utils.getRandom(0, 2) == 0)
//            model.addChild(new Bear(x, y, model, GenderEnum.MALE));
//        else
//            model.addChild(new Bear(x, y, model, GenderEnum.FEMALE));
    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.bearImg, x, y, null);
            else
                canvas.drawBitmap(model.gameBitmaps.bearImgF, x, y, null);

    }
    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -9614271;
        else if (hunger > 30)
            return -8825528;
        else
            return -7508381;
    }
}
