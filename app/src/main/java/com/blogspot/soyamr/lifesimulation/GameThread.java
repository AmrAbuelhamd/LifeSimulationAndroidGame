package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameThread {

    private final Controller gameSurface;
    private final SurfaceHolder surfaceHolder;
    final long waitTime = 100;
    final ScheduledExecutorService executor;


    public GameThread(Controller gameSurface) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = gameSurface.getHolder();

        executor = Executors.newSingleThreadScheduledExecutor();
        initialize();
        executor.scheduleAtFixedRate(this::run, 0, waitTime, TimeUnit.MILLISECONDS);
    }

    private static String frameRate;
    private long lastTime;
    private long delta;
    private  int frameCount;

    public void initialize() {
        lastTime = System.currentTimeMillis();
        frameRate = "FPS 0";
    }

    public void calculate() {
        long current = System.currentTimeMillis();
        delta += current - lastTime;
        lastTime = current;
        frameCount++;
        if (delta > 1000) {
            delta -= 1000;
            frameRate = String.format("FPS %s", frameCount);
            frameCount = 0;
        }
    }

    public static String getFrameRate() {
        return frameRate;
    }

    public void run() {
        calculate();

        if (!surfaceHolder.getSurface().isValid()) {
            return;
        }

        // Get Canvas from Holder and lock it.
        Canvas canvas = this.surfaceHolder.lockCanvas();
        this.gameSurface.update();
        this.gameSurface.draw(canvas);
        this.surfaceHolder.unlockCanvasAndPost(canvas);
    }

}