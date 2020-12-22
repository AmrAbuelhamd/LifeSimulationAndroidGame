package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameThread {

    private static String frameRate;
    final long waitTime = 100;
    final ScheduledExecutorService executor;
    private final Controller gameSurface;
    private final SurfaceHolder surfaceHolder;
    Canvas canvas;
    private long lastTime;
    private long delta;
    private int frameCount;

    public GameThread(Controller gameSurface) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = gameSurface.getHolder();

        executor = Executors.newSingleThreadScheduledExecutor();
        initialize();
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                GameThread.this.run();
            }
        }, 0, waitTime, TimeUnit.MILLISECONDS);
    }

    public static String getFrameRate() {
        return frameRate;
    }

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

    public void run() {
        calculate();

        if (!surfaceHolder.getSurface().isValid()) {
            return;
        }
        this.gameSurface.update();
        // Get Canvas from Holder and lock it.
        canvas = this.surfaceHolder.lockCanvas();
        this.gameSurface.myDraw(canvas);
//        gameSurface.invalidate();
        this.surfaceHolder.unlockCanvasAndPost(canvas);
        //
    }

}