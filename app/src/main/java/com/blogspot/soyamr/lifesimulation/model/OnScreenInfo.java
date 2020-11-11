package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.GameThread;
import com.blogspot.soyamr.lifesimulation.Utils;

public class OnScreenInfo extends GameObject {

    public OnScreenInfo() {
        paint.setTextSize(60);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);

    }

    int animalsCount;
    int plantCount;

    public void update(int animalsCount, int plantsCount) {
        this.animalsCount = animalsCount;
        this.plantCount = plantsCount;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect r = canvas.getClipBounds();

        x = r.left+100;
        y = r.top+100;
        canvas.drawText("animals population: " + animalsCount, x, y, paint);
        canvas.drawText("plants population: " + plantCount, x, y + 60, paint);
        canvas.drawText("fps: " + GameThread.getFrameRate(), r.right-400, y + 60, paint);

    }

}
