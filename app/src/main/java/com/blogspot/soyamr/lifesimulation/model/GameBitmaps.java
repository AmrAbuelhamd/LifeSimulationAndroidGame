package com.blogspot.soyamr.lifesimulation.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blogspot.soyamr.lifesimulation.R;

public class GameBitmaps {
    Context context;
    public Bitmap granaryImg;

    public GameBitmaps(Context context) {
        this.context = context;
        granaryImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.granary);
        granaryImg = Bitmap.createScaledBitmap(granaryImg, Dimensions.granaryWidth, Dimensions.granaryHeight, false);

    }
}
