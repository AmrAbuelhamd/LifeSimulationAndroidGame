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

        executor = Executors.newScheduledThreadPool(4);
        executor.scheduleAtFixedRate(gameSurface::addOnePlant, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(gameSurface::increaseAnimalsHunger, 0, 10, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(gameSurface::updateInfo, 0, 5, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(this::run, 0, waitTime, TimeUnit.MILLISECONDS);

    }

    public void run() {
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