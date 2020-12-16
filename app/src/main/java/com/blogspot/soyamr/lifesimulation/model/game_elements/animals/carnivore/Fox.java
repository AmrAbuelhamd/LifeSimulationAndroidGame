package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;

import java.util.List;

public class Fox extends Carnivore {
//    Bitmap bitmap;

    public Fox(int x, int y, Model model, GenderEnum genderEnum) {
        super(x, y, model, Type.FOX, genderEnum,
                List.of(Type.RABBIT));
//        bitmap = BitmapFactory.decodeResource(FantasticColors.context.getResources(), R.drawable.fox3);
//        bitmap = Bitmap.createScaledBitmap(bitmap, width * 2, height * 2, false);
    }

    @Override
    public void addChild() {
        if (Utils.getRandom(0, 2) == 0)
            model.addChild(new Fox(x, y, model, GenderEnum.MALE));
        else
            model.addChild(new Fox(x, y, model, GenderEnum.FEMALE));
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

//    @Override
//    public void draw(Canvas canvas) {
//        canvas.drawBitmap(bitmap, x, y, new Paint());
//        //super.draw(canvas);
//    }
}
