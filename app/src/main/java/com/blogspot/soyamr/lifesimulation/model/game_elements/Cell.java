package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;
import com.blogspot.soyamr.lifesimulation.model.game_elements.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class Cell {
    public final Paint paint;
    private final Rect rect;

    private final List<GameObject> residences;
    private final String tag = "cell";
    int x, y;
    public GroundType groundType;

    public Cell(int i, int j) {
        x = j * Const.CELL_WIDTH;
        y = i * Const.CELL_HEIGHT;
        residences = new ArrayList<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        rect = new Rect();
        rect.set(x, y, x + GameObject.width, y + GameObject.height);

    }


    void setRandomColor() {
        Random rnd = new Random();
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

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
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

    public void clear() {
        residences.removeIf(it -> it instanceof Animal || it instanceof Plant);
    }

    public GroundType getType() {
        return groundType;
    }
}
