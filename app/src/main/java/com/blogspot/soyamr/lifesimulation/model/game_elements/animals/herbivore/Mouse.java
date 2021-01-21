package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.herbivore;

import android.graphics.Canvas;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore.Fox;

import java.util.List;

public class Mouse extends Herbivore{

//    public Mouse(int x, int y, Model model, GenderEnum genderEnum) {
//        super(x, y, model, Type.MOUSE, genderEnum,
//                List.of(Type.OAT));
//    }
    public static final class Builder extends Herbivore.Builder<Mouse, Mouse.Builder> {
        protected Mouse createObject() {
            return new Mouse();
        }
        protected Mouse.Builder thisObject() {
            return this;
        }
    }
    @Override
    public void addChild() {
//        if (Utils.getRandom(0, 2) == 0)
//            model.addChild(new Mouse(x, y, model, GenderEnum.MALE));
//        else
//            model.addChild(new Mouse(x, y, model, GenderEnum.FEMALE));
    }
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        if (isAlive)
            if (genderEnum == GenderEnum.MALE)
                canvas.drawBitmap(model.gameBitmaps.mouseImg, x, y, null);
            else
                canvas.drawBitmap(model.gameBitmaps.mouseImgF, x, y, null);
    }
    @Override
    public int getMyColor() {
        if (hunger > 60)
            return -4342339;
        else if (hunger > 30)
            return -2039584;
        else
            return -1118482;
    }
}
