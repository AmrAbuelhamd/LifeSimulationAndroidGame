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
        x = getRandom(0, Const.N) * width;
        y = getRandom(0, Const.M) * height;
        rect.set(x, y, x + width, y + height);
    }

    @Override
    Paint getPaint() {
        return paint;
    }

}
