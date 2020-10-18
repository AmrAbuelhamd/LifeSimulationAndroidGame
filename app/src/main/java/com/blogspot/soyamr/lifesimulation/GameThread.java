package com.blogspot.soyamr.lifesimulation;


import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameThread {


    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;
    final long waitTime = 50;
    final ScheduledExecutorService executor;
    public GameThread(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = gameSurface.getHolder();

         executor = Executors.newScheduledThreadPool(2);

        Runnable addMorePlants = () -> {
            Plant plant = new Plant();
            gameSurface.plants.put(plant.getKey(), plant);
        };

        Runnable reduceCreatureLife = () -> {
            gameSurface.creatures.forEach(creature -> {
                final boolean isDead = creature.reduceLife();
                if (isDead) {
                    Log.i("one died", "bad!");
                    gameSurface.creatures.remove(creature);
                }
            });
        };
        Runnable updateInfo = () -> {
            Log.i("number of creatures: ", " " + gameSurface.creatures.size());
            Log.i("number of plants: ", " " + gameSurface.plants.size());
            Log.i("----------------", " ------------------------");
        };



        executor.scheduleAtFixedRate(addMorePlants, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(reduceCreatureLife, 0, 10, TimeUnit.SECONDS);//fixme change delay to 10
        executor.scheduleAtFixedRate(updateInfo, 0, 10, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(this::myrun,0, waitTime , TimeUnit.MILLISECONDS);

    }

    public void myrun() {
        if (!surfaceHolder.getSurface().isValid())
            return ;

        // Get Canvas from Holder and lock it.
        Canvas canvas = this.surfaceHolder.lockCanvas();
        this.gameSurface.update();
        this.gameSurface.draw(canvas);
        this.surfaceHolder.unlockCanvasAndPost(canvas);
    }

}