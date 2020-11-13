package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class Cell extends GameObject {

    private List<GameObject> residences;

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
        residences = new CopyOnWriteArrayList<>();
    }



    void setRandomColor() {
        Random rnd = new Random();
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void putMeHerePlease(GameObject gameObject) {
        this.residences.add(gameObject);
    }

    public void removeMeFromHere(GameObject gameObject) {
        this.residences.remove(gameObject);
    }

    public List<GameObject> getObjectResidingHere() {
        return residences;
    }

    public void update() {
        ListIterator<GameObject> iter = residences.listIterator();
        while (iter.hasNext()) {
            GameObject current = iter.next();
            if (current.x != x || current.y != y) {
                iter.remove();
            }
        }
    }
}
