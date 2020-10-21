package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Utils;

public class Plant extends GameObject {


    Plant() {
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);

        x = Utils.getRandom(0, Utils.Const.N) * width;
        y = Utils.getRandom(0, Utils.Const.M) * height;
        rect.set(x, y, x + width, y + height);
    }

    Plant(Plant nearToThisPlant) {

        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);

        int index = Utils.getRandom(0, moveDirection.length);

        this.x = nearToThisPlant.x + moveDirection[index][0] * width;
        this.y = nearToThisPlant.y + moveDirection[index][1] * height;
        reachedScreenEdge();
        rect.set(x, y, x + width, y + height);
    }

}
