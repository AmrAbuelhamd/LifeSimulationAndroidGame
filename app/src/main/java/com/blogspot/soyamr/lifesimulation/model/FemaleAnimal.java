package com.blogspot.soyamr.lifesimulation.model;

import android.graphics.Color;
import android.graphics.Paint;

public class FemaleAnimal extends Animal {
    private static final Paint paint;

    static {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }


    FemaleAnimal(Model model) {
        super(model);
    }

    public FemaleAnimal(int x, int y, Model model) {
        super(x, y, model);
    }


    @Override
    public void update() {
        if (inRelation) {
            waitLoveToArrive();
            return;
        } else if (hunger < SEARCH_FOOD_THRESHOLD) {
            boolean found = needFood();
            if (!found)
                moveRandomly();
        } else {
            moveRandomly();
        }
        super.update();
    }

    void waitLoveToArrive() {
    }

    boolean wannaBeInRelationship() {
        if (!iDoNotWant && !inRelation && hunger > SEARCH_FOOD_THRESHOLD) {
            inRelation = true;
            return true;
        } else
            return false;
    }

    @Override
    Paint getPaint() {
        return paint;
    }

    public void brokeUp() {
        inRelation = false;
        setIdoNotWant();
    }
}
