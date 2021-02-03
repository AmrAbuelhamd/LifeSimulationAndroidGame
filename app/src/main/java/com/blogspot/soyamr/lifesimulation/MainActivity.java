package com.blogspot.soyamr.lifesimulation;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

public class MainActivity extends Activity {
    GameSurface gameSurface;
    boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameSurface view = new GameSurface(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(view);
        //hideSystemUI();
        getScreenSize();

    }

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Const.SCREEN_WIDTH = width;
        Const.SCREEN_HEIGHT = height;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gameSurface != null)
            gameSurface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameSurface != null) {
            gameSurface.resume();
        }
    }
}