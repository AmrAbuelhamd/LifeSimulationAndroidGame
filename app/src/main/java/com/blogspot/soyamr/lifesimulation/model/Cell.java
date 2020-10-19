package com.blogspot.soyamr.lifesimulation.model;



import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;


public class Cell extends GameObject {

    private static final Paint paint;

    static {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.GREEN);
    }

    public Cell(int i, int j) {
        x = j * width;
        y = i * height;
        rect.set(x, y, x + width, y + height);
    }

    @Override
    Paint getPaint() {
        return paint;
    }

    void setRandomColor() {
        Random rnd = new Random();
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

    }
}
