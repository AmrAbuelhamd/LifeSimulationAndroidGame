package com.blogspot.soyamr.lifesimulation;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
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

        //printcolors();
    }

    private void printcolors() {
        System.out.println("carrot");
        System.out.println(" " + Color.parseColor("#E65100") + " ");
        System.out.println("oat");
        System.out.println(" " + Color.parseColor("#A1887F") + " ");
        System.out.println("apple");
        System.out.println(" " + Color.parseColor("#BF360C") + " ");

        System.out.println("rabbit");
        System.out.println(" " + Color.parseColor("#F48FB1") + " ");
        System.out.println(" " + Color.parseColor("#F8BBD0") + " ");
        System.out.println(" " + Color.parseColor("#FCE4EC") + " ");

        System.out.println("mouse");
        System.out.println(" " + Color.parseColor("#BDBDBD") + " ");
        System.out.println(" " + Color.parseColor("#E0E0E0") + " ");
        System.out.println(" " + Color.parseColor("#EEEEEE") + " ");

        System.out.println("deer");
        System.out.println(" " + Color.parseColor("#FFCA28") + " ");
        System.out.println(" " + Color.parseColor("#FFD54F") + " ");
        System.out.println(" " + Color.parseColor("#FFE082") + " ");

        System.out.println("fox");
        System.out.println(" " + Color.parseColor("#F57F17") + " ");
        System.out.println(" " + Color.parseColor("#F9A825") + " ");
        System.out.println(" " + Color.parseColor("#FBC02D") + " ");

        System.out.println("wolf");
        System.out.println(" " + Color.parseColor("#607D8B") + " ");
        System.out.println(" " + Color.parseColor("#78909C") + " ");
        System.out.println(" " + Color.parseColor("#90A4AE") + " ");

        System.out.println("loin");
        System.out.println(" " + Color.parseColor("#827717") + " ");
        System.out.println(" " + Color.parseColor("#9E9D24") + " ");
        System.out.println(" " + Color.parseColor("#AFB42B") + " ");

        System.out.println("beer");
        System.out.println(" " + Color.parseColor("#6D4C41") + " ");
        System.out.println(" " + Color.parseColor("#795548") + " ");
        System.out.println(" " + Color.parseColor("#8D6E63") + " ");

        System.out.println("pig");
        System.out.println(" " + Color.parseColor("#F50057") + " ");
        System.out.println(" " + Color.parseColor("#FF4081") + " ");
        System.out.println(" " + Color.parseColor("#FF80AB") + " ");

        System.out.println("raccoon");
        System.out.println(" " + Color.parseColor("#00838F") + " ");
        System.out.println(" " + Color.parseColor("#0097A7") + " ");
        System.out.println(" " + Color.parseColor("#00ACC1") + " ");

        System.out.println("person");
        System.out.println(" " + Color.parseColor("#4DB6AC") + " ");
        System.out.println(" " + Color.parseColor("#80CBC4") + " ");
        System.out.println(" " + Color.parseColor("#B2DFDB") + " ");
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
        Const.SCREEN_WIDTH = width;
        Const.SCREEN_HEIGHT = height;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!firstTime)
            gameSurface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstTime) {
            gameSurface.resume();
            //hideSystemUI();
        }
    }
}