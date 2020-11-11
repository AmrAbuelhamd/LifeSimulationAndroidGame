package com.blogspot.soyamr.lifesimulation.model;


import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class Cell extends GameObject {

    private List<GameObject> gameObjects;

    private static final Paint paint;

    static {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.GREEN);
    }

    private String tag = "cell";

    public Cell(int i, int j) {
        x = j * width;
        y = i * height;
        rect.set(x, y, x + width, y + height);
    }

    public Cell() {
        gameObjects = new CopyOnWriteArrayList<>();
    }


    void setRandomColor() {
        Random rnd = new Random();
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    void putMeHerePlease(GameObject gameObject) {
        Log.i(tag, "added " + gameObject);
//        Log.i(tag, iam);
        this.gameObjects.add(gameObject);
    }

    void removeMeFromHere(GameObject gameObject) {
        Log.i(tag, "removing " + gameObject);
//        Log.i(tag, iam);
        this.gameObjects.remove(gameObject);
    }

    List<GameObject> getObjectResidingHere() {
        //Log.i(tag, "getting animal: " + gameObject + " from cell ");
//        Log.i(tag, iam);
        return gameObjects;
    }
}
