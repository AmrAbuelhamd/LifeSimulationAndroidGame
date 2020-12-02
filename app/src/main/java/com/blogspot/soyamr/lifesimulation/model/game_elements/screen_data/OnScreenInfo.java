package com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.GameThread;
import com.blogspot.soyamr.lifesimulation.model.game_elements.GameObject;

public class OnScreenInfo extends GameObject {

    public OnScreenInfo() {
        super(null,null);
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);

    }

    int size = 50;
    int total;
    int plantCount;
    int femaleCount;
    int right = 50;
    int space;

    public void update(int animalsCount, int femaleCount, int plantsCount, Rect clipBoundsCanvas, float mScaleFactor) {
        this.total = animalsCount;
        this.plantCount = plantsCount;
        this.femaleCount = femaleCount;

        paint.setTextSize(size / mScaleFactor);
        if (clipBoundsCanvas != null) {
            x = clipBoundsCanvas.left + (int) (100 / mScaleFactor);
            y = clipBoundsCanvas.top + (int) (100 / mScaleFactor);
            right = clipBoundsCanvas.right - (int) (200 / mScaleFactor);
            space = (int) (60 / mScaleFactor);
        }
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawText("total population: " + total, x, y, paint);
//        canvas.drawText("female population: " + femaleCount, x, y + space, paint);
//        canvas.drawText("male population: " + (int) (total - femaleCount), x, y + 1 * space, paint);
        canvas.drawText("plants population: " + plantCount, x, y + 1 * space, paint);
        canvas.drawText(GameThread.getFrameRate(), right, y + space, paint);

    }

    @Override
    public void makeMeFamous() {
    }

    @Override
    public void updateAdditionalInfoLocation(float mScaleFactor) {
    }

    @Override
    public void drawAdditionalInfo(Canvas canvas) {
    }
}
