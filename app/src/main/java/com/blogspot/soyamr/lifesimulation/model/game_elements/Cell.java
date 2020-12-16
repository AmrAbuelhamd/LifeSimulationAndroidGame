package com.blogspot.soyamr.lifesimulation.model.game_elements;


import android.graphics.Color;
import android.graphics.Paint;

import com.blogspot.soyamr.lifesimulation.Const;
import com.blogspot.soyamr.lifesimulation.model.game_elements.animals.Animal;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class Cell {
    private static final Paint paint;

    static {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.GREEN);
    }

    private final List<GameObject> residences;
    private final String tag = "cell";
    int x, y;

    public Cell(int i, int j) {
        x = j * Const.CELL_WIDTH;
        y = i * Const.CELL_HEIGHT;
        residences = new LinkedList<>();
    }


    void setRandomColor() {
        Random rnd = new Random();
        paint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
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
}
