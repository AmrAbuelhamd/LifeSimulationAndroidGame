package com.blogspot.soyamr.lifesimulation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    public Matrix matrix;
    private boolean running;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();

        while (running) {
            Canvas canvas = null;
//            Bitmap bufferBitmap = Bitmap.createBitmap(CONST.SCREEN_WIDTH, CONST.SCREEN_HEIGHT, Bitmap.Config.ARGB_8888);
//            Canvas bufferCanvas = new Canvas(bufferBitmap);
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas) {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
//                    canvas.drawBitmap(bufferBitmap, matrix, null);
//                    canvas.restore();
                }

            } catch (Exception e) {
                // Do nothing.
            } finally {
                if (canvas != null) {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime();
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = (now - startTime) / 1000000;
            if (waitTime < 10) {
                waitTime = 10; // Millisecond.
            }
            System.out.print(" Wait Time=" + waitTime);

            try {
                // Sleep.
                this.sleep(waitTime);
            } catch (InterruptedException e) {

            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}