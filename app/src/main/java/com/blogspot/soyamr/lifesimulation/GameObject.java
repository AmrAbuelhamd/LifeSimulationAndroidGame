package com.blogspot.soyamr.lifesimulation;

import android.graphics.Paint;

import java.util.Random;

public interface GameObject {
    int width = CONST.CELL_WIDTH;
    int height = CONST.CELL_HEIGHT;
    Paint paint = new Paint();
    Random rand = new Random();

    int getKey();
}