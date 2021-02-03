package com.blogspot.soyamr.lifesimulation.model.game_elements.screen_data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.GameThread;

public class OnScreenInfo {
    public final Paint paint = new Paint();
    protected int x;
    protected int y;
    public OnScreenInfo() {
        paint.setTextSize(50);
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

    public void update(int animalsCount, int plantsCount, Rect clipBoundsCanvas, float mScaleFactor) {
        this.total = animalsCount;
        this.plantCount = plantsCount;

        paint.setTextSize(size / mScaleFactor);
        if (clipBoundsCanvas != null) {
            x = clipBoundsCanvas.left + (int) (100 / mScaleFactor);
            y = clipBoundsCanvas.top + (int) (100 / mScaleFactor);
            right = clipBoundsCanvas.right - (int) (200 / mScaleFactor);
            space = (int) (60 / mScaleFactor);
        }
    }

    public void draw(Canvas canvas) {

        canvas.drawText("total population: " + total, x, y, paint);
        canvas.drawText("plants population: " + plantCount, x, y + 1 * space, paint);
        canvas.drawText(GameThread.getAvgFPS(), right, y + space, paint);

    }
}
