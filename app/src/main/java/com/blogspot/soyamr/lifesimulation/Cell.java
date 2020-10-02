package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;


public class Cell implements GameObject {
    Rect rect;
    int x, y;
    int key;

    static void defineColors() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GREEN);
    }

    public Cell(int i, int j, int cellNo) {
        x = j * width;
        y = i * height;
        rect = new Rect(x, y, x + width, y + height);
        key = cellNo;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }


    @Override
    public int getKey() {
        return key;
    }

    void setRandomColor() {
        Random rnd = new Random();
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

    }
}
