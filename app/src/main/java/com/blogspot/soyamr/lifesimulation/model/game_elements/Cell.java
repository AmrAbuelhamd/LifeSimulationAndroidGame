package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.blogspot.soyamr.lifesimulation.Const;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class Cell {
//    private static final Paint paint;
//    private static final Rect rect;
    static {
//        paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);
//        paint.setColor(Color.GREEN);
//        rect = new Rect();
    }

    private final List<GameObject> residences;
    private final String tag = "cell";
    int x, y;

    public Cell(int i, int j) {
        x = j * Const.CELL_WIDTH;
        y = i * Const.CELL_HEIGHT;
        residences = new ArrayList<>();
//        rect.set(x, y, x + GameObject.width, y + GameObject.height);

    }


    void setRandomColor() {
        Random rnd = new Random();
//        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public void putMeHerePlease(GameObject gameObject) {
        this.residences.add(gameObject);
//        List<GameObject> toRemove = new LinkedList<>();
//        if (residences.size() > 1) {
//            residences.forEach(eater -> {
//                if (eater instanceof Animal && eater.isAlive) {
//                    residences.forEach(food -> {
//                        if (food.isAlive) {
//                            if (((Animal) eater).wannaEat(food)) {
//                                toRemove.add(food);
//                            }
//                        }
//                    });
//                }
//            });
//        }
//        toRemove.forEach(residences::remove);
    }

    public void removeMeFromHere(GameObject gameObject) {
        this.residences.remove(gameObject);
    }

    public List<GameObject> getObjectResidingHere() {
        return residences;
    }

    public void draw(Canvas canvas) {
//        canvas.drawRect(rect, paint);
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

    public void deleteEveryOne() {
        residences.clear();
    }

    public void clear() {
        residences.clear();
    }
}
