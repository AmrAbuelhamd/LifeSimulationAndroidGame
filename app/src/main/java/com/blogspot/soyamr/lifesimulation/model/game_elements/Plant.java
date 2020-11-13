package com.blogspot.soyamr.lifesimulation.model.game_elements;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.Utils;
import com.blogspot.soyamr.lifesimulation.model.Model;

public class Plant extends GameObject {


    public Plant(Model model) {
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);

        x = Utils.getRandom(0, Const.N) * width;
        y = Utils.getRandom(0, Const.M) * height;
        rect.set(x, y, x + width, y + height);
        model.putMeHerePlease(x, y, this);
    }
    public Plant(Plant nearToThisPlant, Model model) {

        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);

        int index = Utils.getRandom(0, moveDirection.length);

        this.x = nearToThisPlant.x + moveDirection[index][0] * width;
        this.y = nearToThisPlant.y + moveDirection[index][1] * height;
        reachedScreenEdge();
        rect.set(x, y, x + width, y + height);
        model.putMeHerePlease(x,y, this);
    }

}
