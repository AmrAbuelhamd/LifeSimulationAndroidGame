package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public interface Controller {

    void update();

    void myDraw(Canvas canvas);

    SurfaceHolder getHolder();
    void invalidate();
}
