package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.GameThread;

public class OnScreenInfo extends GameObject {

    public OnScreenInfo() {
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);

    }

    int size = 50;
    int animalsCount;
    int plantCount;
    int right=50;
    int space;

    public void update(int animalsCount, int plantsCount, Rect clipBoundsCanvas, float mScaleFactor) {
        this.animalsCount = animalsCount;
        this.plantCount = plantsCount;

        paint.setTextSize(size / mScaleFactor);
        if(clipBoundsCanvas!=null) {
            x = clipBoundsCanvas.left + (int) (100 / mScaleFactor);
            y = clipBoundsCanvas.top + (int) (100 / mScaleFactor);
            right = clipBoundsCanvas.right - (int) (200 / mScaleFactor);
            space = (int) (60 / mScaleFactor);
        }
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawText("animals population: " + animalsCount, x, y, paint);
        canvas.drawText("plants population: " + plantCount, x, y + space, paint);
        canvas.drawText(GameThread.getFrameRate(), right, y + space, paint);

    }
}
