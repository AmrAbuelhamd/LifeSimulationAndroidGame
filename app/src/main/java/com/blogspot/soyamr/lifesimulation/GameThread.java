package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private static int waitTime;
    private final Controller gameSurface;
    private final SurfaceHolder surfaceHolder;
    Canvas canvas;
    boolean running = false;
    private long lastTime;
    private long delta;

    public GameThread(Controller gameSurface) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = gameSurface.getHolder();
    }

    public static String getWaitTime() {
        return "dlt " + waitTime;
    }

    @Override
    public void run() {
        long startTime = SystemClock.uptimeMillis();
        while (running) {
//            long updateAvg = SystemClock.uptimeMillis();
            gameSurface.update();
//            long updateAvgEnd = SystemClock.uptimeMillis();
//            Log.i("updateTime: ", " -> " + -(updateAvg - updateAvgEnd));
//            updateAvg = SystemClock.uptimeMillis();
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
//            updateAvgEnd = SystemClock.uptimeMillis();
//            Log.i("drawTime: ", " -> " + -(updateAvg - updateAvgEnd));

            long now = SystemClock.uptimeMillis();

            long waitTime = (now - startTime);
            GameThread.waitTime = (int) (waitTime);
            if (waitTime < 100) {
                waitTime = 100 - waitTime;
                try {
                    sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            startTime = SystemClock.uptimeMillis();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}