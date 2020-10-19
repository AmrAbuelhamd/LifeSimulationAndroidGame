package com.blogspot.soyamr.lifesimulation;

import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

public class Plant extends GameObject {
    private static final Paint paint;

    static {
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
    }

    Plant() {
        x = Utils.getRandom(0, Const.N) * width;
        y = Utils.getRandom(0, Const.M) * height;
        rect.set(x, y, x + width, y + height);
    }

    Plant(Plant nearToThisPlant) {
        int index = Utils.getRandom(0, moveDirection.length);

        this.x = nearToThisPlant.x + moveDirection[index][0] * width;
        this.y = nearToThisPlant.y + moveDirection[index][1] * height;

        rect.set(x, y, x + width, y + height);
    }

    @Override
    Paint getPaint() {
        return paint;
    }

}
