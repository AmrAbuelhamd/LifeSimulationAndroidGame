package com.blogspot.soyamr.lifesimulation.model.game_elements.animals.carnivore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.R;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GenderEnum;
import com.blogspot.soyamr.lifesimulation.model.game_elements.Type;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.helpers.FantasticColors;

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

//    @Override
//    public void draw(Canvas canvas) {
//        canvas.drawBitmap(bitmap, x, y, new Paint());
//        //super.draw(canvas);
//    }
}
