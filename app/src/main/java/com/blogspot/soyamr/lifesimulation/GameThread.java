package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private static int avgFPS = 0;
    private final Controller gameSurface;
    private final SurfaceHolder surfaceHolder;
    Canvas canvas;
    boolean running = false;

    public GameThread(Controller gameSurface) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = gameSurface.getHolder();
    }

    public static String getAvgFPS() {
        return "fps: " + avgFPS;
    }

    @Override
    public void run() {
        long startTime;
        long endTime;
        long targetWaitTime = 100;
        long actualWaitTime;
        long totalTime = 0;
        int frameCount = 0;
        while (running) {
            startTime = SystemClock.uptimeMillis();

            gameSurface.update();
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (canvas != null) {
                        gameSurface.drawScene(canvas);
                    }
                }
            } finally {
                if (canvas != null)
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
            }

            endTime = SystemClock.uptimeMillis();

            actualWaitTime = (endTime - startTime);
            if (actualWaitTime < targetWaitTime) {
                actualWaitTime = targetWaitTime - actualWaitTime;
                try {
                    sleep(actualWaitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                endTime = SystemClock.uptimeMillis();
            }
            totalTime += endTime - startTime;
            ++frameCount;
            if (totalTime > 1000) {
                avgFPS = frameCount;
                totalTime -= 1000;
                frameCount = 0;
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}