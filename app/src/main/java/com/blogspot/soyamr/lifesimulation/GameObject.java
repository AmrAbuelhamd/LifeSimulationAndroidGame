package com.blogspot.soyamr.lifesimulation;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public interface GameObject {
    int width = 10;//Const.SCREEN_WIDTH / Const.N;
    int height = 10;//Const.SCREEN_HEIGHT / Const.M;
    Paint paint = new Paint();
    Random rand = new Random();

    String getKey();
}