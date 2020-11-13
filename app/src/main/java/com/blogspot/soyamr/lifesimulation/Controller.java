package com.blogspot.soyamr.lifesimulation;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public interface Controller {

    void update();

    void draw(Canvas canvas);

    SurfaceHolder getHolder();
}
