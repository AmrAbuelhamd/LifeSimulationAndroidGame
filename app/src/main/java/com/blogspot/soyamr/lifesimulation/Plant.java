package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Plant extends GameObject {
    static final Paint paint;

    static {
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
    }

    Plant() {
        x = getRandom(0, CONST.N) * width;
        y = getRandom(0, CONST.M) * height;
        rect.set(x, y, x + width, y + height);
    }

    @Override
    Paint getPaint() {
        return paint;
    }
}
