package com.blogspot.soyamr.lifesimulation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    GameSurface gameSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameSurface view = new GameSurface(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(view);

//        gameSurface = new GameSurface(this);
//        setContentView(gameSurface);
//        hideSystemUI();
//        //getScreenSize();
//
//        Log.i("amro width: "," "+Const.SCREEN_WIDTH);
//        Log.i("amro height: "," "+Const.SCREEN_HEIGHT);
    }


    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        CONST.SCREEN_WIDTH = width;
        CONST.SCREEN_HEIGHT = height;

        Log.i("amro width: "," "+ CONST.SCREEN_WIDTH);
        Log.i("amro height: "," "+ CONST.SCREEN_HEIGHT);
    }

    boolean firstTime = true;
    @Override
    protected void onPause() {
        super.onPause();
        if(!firstTime)
            gameSurface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstTime) {
            gameSurface.resume();
        }
    }
}